package com.tdse.mx.manager;

import com.alibaba.fastjson.JSON;
import com.tdse.mx.dao.OrbVip;
import com.tdse.mx.db.OrbVipImpl;
import com.tdse.mx.util.JsonUtils;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class OrbVipManager
{
    //使用volatile关键字保其可见性
    volatile private static OrbVipManager instance = null;

    private OrbVipManager(){}

    public static OrbVipManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbVipManager.class) {
                    if(instance == null){//二次检查
                        instance = new OrbVipManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    /**
     * 创建套餐
     */
    public String creatVipData(String msg)
    {
        String viptype=null;
        int viptime=0;
        int vipprice=0;
        try {
            viptype=JsonUtils.getValue(msg,"viptype").toString();
            viptime=Integer.parseInt(JsonUtils.getValue(msg,"viptime").toString());
            vipprice=Integer.parseInt(JsonUtils.getValue(msg,"vipprice").toString());

        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Creat Fail!").toString();
        }

        OrbVip orbbecVip =new OrbVip();
        orbbecVip.setVip_type(viptype);
        orbbecVip.setVip_time(viptime);
        orbbecVip.setVip_price(vipprice);
        OrbVipImpl.getInstance().add(orbbecVip);

        return  JsonUtils.setResultData("true","Creat Success!").toString();
    }

    /**
     * 更新vip信息
     * @param msg
     * @return
     */
    public String updateVipData(String msg)
    {
//        String viptype=null;
//        int viptime=0;
//        int vipprice=0;
//        try {
//            viptype=JsonUtils.getValue(msg,"viptype").toString();
//            viptime=Integer.parseInt(JsonUtils.getValue(msg,"viptime").toString());
//            vipprice=Integer.parseInt(JsonUtils.getValue(msg,"vipprice").toString());
//        }catch (Exception e)
//        {
//            return  JsonUtils.setResultData("false","Update Fail!").toString();
//        }
//
//        OrbVip orbbecVip= OrbVipImpl.getInstance().findByType("");
//
//        if (orbbecVip!=null)
//        {
//            OrbVip orbbecVips =new OrbVip();
//            orbbecVips.setVip_id(orbbecVip.getVip_id());
//            orbbecVips.setVip_type(viptype);
//            orbbecVips.setVip_time(viptime);
//            orbbecVips.setVip_price(vipprice);
//            OrbVipImpl.getInstance().update(orbbecVips);
//            return  JsonUtils.setResultData("true","Update Success!").toString();

        //}

        return  JsonUtils.setResultData("false","Update Fail!").toString();
    }

    /**
     * 删除vip信息
     * @param msg
     * @return
     */
    public String deleteVipData(String msg)
    {
//        String viptype=null;
//        try {
//            viptype=JsonUtils.getValue(msg,"viptype").toString();
//        }catch (Exception e)
//        {
//            return  JsonUtils.setResultData("false","Delete Fail!").toString();
//        }
//
//        OrbVip orbbecVip= OrbVipImpl.getInstance().findByType("");
//        if (orbbecVip!=null)
//        {
//            OrbVipImpl.getInstance().deleteById(orbbecVip.getVip_id());
//            return  JsonUtils.setResultData("true","Delete Success!").toString();
//        }
        return  JsonUtils.setResultData("false","Delete Fail!").toString();
    }


    /**
     * 通过类型获得想要的套餐类型
     * @param msg
     * @return
     */
    public String getVipData(String msg)
    {
        String viptype=null;
        try {
            viptype=JsonUtils.getValue(msg,"viptype").toString();
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Get Fail!").toString();
        }

        if (viptype.equals(""))
        {
            System.out.println("VIP  ALL"+OrbVipImpl.getInstance().find().toString());
            return JSON.toJSONString(OrbVipImpl.getInstance().find());
        }else {

           OrbVip o =new OrbVip();
           o.setVip_type(viptype);
           List<OrbVip> oo= OrbVipImpl.getInstance().find(o);

           if(oo.size()>0){
               System.out.println("oo.get(0)"+oo.get(0).toString());
               return JSON.toJSONString(oo.get(0));
           }else{
               System.out.println("null");
               return JSON.toJSONString(null);
           }
        }
    }
}
