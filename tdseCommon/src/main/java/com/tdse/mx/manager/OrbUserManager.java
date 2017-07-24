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
        JSONObject ms = JsonUtils.getValue(msg);
        String username=null;
        try {
            username=ms.getString("username");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Find Fail!").toString();
        }

        return   JSON.toJSONString(OrbUserImpl.getInstance().findByName(username));
    }


    /**
     * 获得vip时间
     * @param username
     * @return
     */
    public String getUserVipTime(String username)
    {
         OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);
         if (orbUser!=null) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("result", true);
             jsonObject.put("viptime", "" + orbUser.getUser_vip_time());
             return jsonObject.toString();
         }
        return  JsonUtils.setResultData("false","Find Fail!").toString();
    }

    public String getUserVipSurplusTime(String username)
    {

            OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);
            if (orbUser!=null)
            {
                JSONObject jsonObject =new JSONObject();
                jsonObject.put("result",true);
                jsonObject.put("viptime",""+(orbUser.getUser_vip_time().getTime() - Utils.getCurrentTime().getTime())/(1000*60));
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
            OrbUser orbUser = OrbUserImpl.getInstance().findByName(username);

            if(orbUser !=null)
            {
                Timestamp timestamp = orbUser.getUser_vip_time();
                OrbVip orbbecVip = OrbVipImpl.getInstance().findById(vipId);
                if(timestamp!=null&&orbbecVip!=null)
                {
                  Timestamp timestamp1 = Utils.getAddTime(timestamp,orbbecVip.getVip_time());
                  orbUser.setUser_vip_time(timestamp1);
                  OrbUserImpl.getInstance().updateOrbbecUser(orbUser);
                }else {
                    Timestamp timestamp1 = Utils.getAddTime(Utils.getCurrentTime(),orbbecVip.getVip_time());
                    orbUser.setUser_vip_time(timestamp1);
                    OrbUserImpl.getInstance().updateOrbbecUser(orbUser);
                }
            }
        }catch (Exception e)
        {
          System.out.println("vip add fail!"+e);
        }
    }

}
