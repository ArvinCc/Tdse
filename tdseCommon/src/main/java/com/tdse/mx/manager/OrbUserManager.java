package com.tdse.mx.manager;

import com.alibaba.fastjson.JSON;
import com.tdse.mx.dao.OrbUser;
import com.tdse.mx.dao.OrbVip;
import com.tdse.mx.db.OrbUserImpl;
import com.tdse.mx.db.OrbVipImpl;
import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.Utils;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Administrator on 2017/7/13.
 */
public class OrbUserManager
{
    //使用volatile关键字保其可见性
    volatile private static OrbUserManager instance = null;

    private OrbUserManager(){}

    public static OrbUserManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbUserManager.class) {
                    if(instance == null){//二次检查
                        instance = new OrbUserManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 通过用户名称获得用户信息
     * @param msg
     * @return
     */
    public String getUserData(String msg)
    {
        String username=null;
        try {
            username=  JsonUtils.getValue(msg,"username").toString();
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Find Fail!").toString();
        }

        OrbUser o=new OrbUser();
        o.setUser_name(username);
        List<OrbUser> orders =OrbUserImpl.getInstance().find(o);

        if(orders.size()>0){
            return   JSON.toJSONString(orders.get(0));
        }else {
            return   JSON.toJSONString(null);
        }
    }

    /**
     * 获得vip时间
     * @param username
     * @return
     */
    public String getUserVipTime(String username)
    {
        OrbUser o=new OrbUser();
        o.setUser_name(username);
        List<OrbUser> orders =OrbUserImpl.getInstance().find(o);
        // OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);

         if (orders.size()>0) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("result", true);
             jsonObject.put("viptime", "" + orders.get(0).getUser_vip_time());
             return jsonObject.toString();
         }
        return  JsonUtils.setResultData("false","Find Fail!").toString();
    }

    public String getUserVipSurplusTime(String username)
    {
        OrbUser o=new OrbUser();
        o.setUser_name(username);
        List<OrbUser> orders =OrbUserImpl.getInstance().find(o);
           // OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);
            if (orders.size()>0)
            {
                JSONObject jsonObject =new JSONObject();
                jsonObject.put("result",true);
                jsonObject.put("viptime",""+(orders.get(0).getUser_vip_time().getTime() - Utils.getCurrentTime().getTime())/(1000*60));
                return  jsonObject.toString();
            }
        return  JsonUtils.setResultData("false","Find Fail!").toString();
    }

    /**
     * 用户加vip时间
     * @param username
     * @param vipId
     */
    public void setUserNewVipTime(String username,int vipId)
    {
        try{
            OrbUser o=new OrbUser();
            o.setUser_name(username);
            List<OrbUser> orders =OrbUserImpl.getInstance().find(o);

           // OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);

            if(orders.size()>0)
            {
                Timestamp timestamp = orders.get(0).getUser_vip_time();

                 OrbVip oo=new OrbVip();
                 oo.setVip_id(vipId);
                 List<OrbVip> orbbecVip = OrbVipImpl.getInstance().find(oo);

                if(timestamp!=null&&orbbecVip.size()>0)
                {
                  Timestamp timestamp1 = Utils.getAddTime(timestamp,orbbecVip.get(0).getVip_time());
                    orders.get(0).setUser_vip_time(timestamp1);
                  OrbUserImpl.getInstance().update(orders.get(0));
                }else {
                    Timestamp timestamp1 = Utils.getAddTime(Utils.getCurrentTime(),orbbecVip.get(0).getVip_time());
                    orders.get(0).setUser_vip_time(timestamp1);
                    OrbUserImpl.getInstance().update(orders.get(0));
                }
            }
        }catch (Exception e)
        {
          System.out.println("vip add fail!"+e);
        }
    }

}
