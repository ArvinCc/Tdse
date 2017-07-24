package com.tdse.mx.manager;

import com.alibaba.fastjson.JSON;
import com.tdse.mx.dao.*;
import com.tdse.mx.db.OrbOrderImpl;
import com.tdse.mx.db.OrbVipImpl;
import com.tdse.mx.db.OrbUserImpl;
import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.MD5;
import com.tdse.mx.util.Utils;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/20.
 */
public class OrbOrderManager
{
    private final String appid="20002";
    private final String appkey="1916cfc07c6f5aee3526298bbd9481b8";
    private final String secretkey="14c7e18495ec5a8689c9246d1c4e70eb";
    private final String spacer="&";
    private final String callBackUrl="http://123.207.52.85:3200/";

    //使用volatile关键字保其可见性
    volatile private static OrbOrderManager instance = null;

    private OrbOrderManager(){}

    public static OrbOrderManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbOrderManager.class) {
                    if(instance == null){//二次检查
                        instance = new OrbOrderManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public String creatOrder(String msg)
    {
        String username=null;
        String viptype=null;
        try {

            viptype= JsonUtils.getValue(msg,"viptype").toString();
            username= JsonUtils.getValue(msg,"username").toString();
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Creat Fail!").toString();
        }

        if (viptype!=null&&username!=null)
        {
            OrbVip orbbecVip = OrbVipImpl.getInstance().findByType(viptype);
            if (orbbecVip!=null)
            {
                OrbUser orbbecUser = OrbUserImpl.getInstance().findByName(username);

                if (orbbecUser==null)
                {
                    OrbUser orbUser1 =new OrbUser();
                    orbUser1.setUser_signup_time(Utils.getCurrentTime());
                    orbUser1.setUser_name(username);
                    OrbUserImpl.getInstance().addOrbbecUser(orbUser1);
                }

                OrbOrder order =new OrbOrder();
                order.setOrder_user_name(username);
                order.setOrder_id(""+Utils.getUuid());
                order.setOrder_establish_time(Utils.getCurrentTime());
                OrbOrderImpl.getInstance().addOrbbecOrder(order);

                OrbClientRq rq=new OrbClientRq();
                rq.setApp_order(order.getOrder_id());
                rq.setAppid(appid);
                rq.setCallback_url(callBackUrl);
                rq.setCount(1);
                rq.setGoods_id(""+orbbecVip.getVip_id());
                rq.setGoods_name(orbbecVip.getVip_type());
                rq.setUnit_price(orbbecVip.getVip_price());

                return  JSON.toJSONString(rq);
            }
        }

        return  JsonUtils.setResultData("false","Creat Fail!").toString();
    }



    public void dealOrder(String msg)
    {
        OrbServerCb cb=null;
        try {
            cb= JSON.parseObject(msg,OrbServerCb.class);

        }catch (Exception e)
        {
            System.out.println("订单交易异常错误:"+e);
        }
        if(cb!=null)
        {
            OrbOrder oldOrder= OrbOrderImpl.getInstance().findById(cb.getApp_order());

            if (oldOrder.getOrder_deal_time()!=null)
            {
                System.out.println("订单已经交易完成,无法重复添加!");
                return;
            }


            if (oldOrder!=null&&oldOrder.getOrder_id().equals(cb.getApp_order()))
            {
                oldOrder.setOrder_payment_user_name(cb.getUsername());
                oldOrder.setOrder_establish_time(oldOrder.getOrder_establish_time());
                oldOrder.setOrder_deal_time(Utils.getCurrentTime());
                oldOrder.setOrder_appid(cb.getAppid());
                oldOrder.setOrder_fee_type(cb.getFee_type());
                oldOrder.setOrder_goods_id(cb.getGoods_id());
                oldOrder.setOrder_uint_price(cb.getUnit_price());
                oldOrder.setOrder_count(cb.getCount());
                oldOrder.setOrder_total_fee(cb.getTotal_fee());
                oldOrder.setOrder_orb_order(cb.getOrb_order());
                oldOrder.setOrder_sign(cb.getSign());
                OrbOrderImpl.getInstance().updateOrbbecOrder(oldOrder);
                OrbUserManager.getInstance().setUserNewVipTime(oldOrder.getOrder_user_name(),Integer.parseInt(cb.getGoods_id()));
                 System.out.println("交易成功");
            }
            else {
                System.out.println("交易不成功！原始订单查找不到");
            }
        }
        else {
            System.out.println("交易不成功！解析错误");
        }
    }
    /**
     * 获得服务器签名
     * @param msg
     * @return
     */
    public String getSign(String msg){

        JSONObject ms = JsonUtils.getValue(msg);
        String username=null;
        String token=null;
        try {
            username=ms.getString("username");
            token=ms.getString("token");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","发生未知错误").toString();
        }

        if(username.trim().equals(""))
        {
            return  JsonUtils.setResultData("false","name为空!").toString();
        }

        if(token.trim().equals(""))
        {
            return JsonUtils.setResultData("false","token为空!").toString();
        }

        JSONObject result=new JSONObject();
        String nonce_str= Utils.getUuid();
        result.put("result","true");
        result.put("appid",appid);
        result.put("appkey",appkey);
        result.put("nonce_str",nonce_str);
        result.put("sign",getAutograph(
                username
                ,token
                ,nonce_str
        ));
        return result.toString();
    }


    /**
     * 通过用户名称来查找订单信息
     * @param msg
     * @return
     */
    public String getOrderByName(String msg)
    {
        JSONObject ms = JsonUtils.getValue(msg);
        String username=null;
        try {
            username=ms.getString("username");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Find Fail!").toString();
        }

        return   JSON.toJSONString(OrbOrderImpl.getInstance().findByUserName(username));
    }

    /**
     * 通过订单编号查找订单信息
     * @param msg
     * @return
     */
    public String getOrderById(String msg)
    {
        JSONObject ms = JsonUtils.getValue(msg);
        String orderid=null;
        try {
            orderid=ms.getString("app_order");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Find Fail!").toString();
        }

        return   JSON.toJSONString(OrbOrderImpl.getInstance().findById(orderid));
    }

    private String getAutograph(String username,String token,String nonce_str){

        return MD5.encode(
                "appid="+appid+spacer
                        +"appkey="+appkey+spacer
                        +"nonce_str="+nonce_str+spacer
                        +"token="+token+spacer
                        +"username="+username+spacer
                        +"secret="+secretkey
        ).toUpperCase();
    }

}
