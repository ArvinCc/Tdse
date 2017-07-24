package com.tdse.mx.manager;

import com.alibaba.fastjson.JSON;
import com.tdse.mx.dao.OrbVip;
import com.tdse.mx.db.OrbVipImpl;
import com.tdse.mx.util.JsonUtils;
import org.json.JSONObject;

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
        JSONObject ms = JsonUtils.getValue(msg);
        String viptype=null;
        int viptime=0;
        int vipprice=0;
        try {
            viptype=ms.getString("viptype");
            viptime=ms.getInt("viptime");
            vipprice=ms.getInt("vipprice");

        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Creat Fail!").toString();
        }

        OrbVip orbbecVip =new OrbVip();
        orbbecVip.setVip_type(viptype);
        orbbecVip.setVip_time(viptime);
        orbbecVip.setVip_price(vipprice);
        OrbVipImpl.getInstance().addOrbbecVip(orbbecVip);

        return  JsonUtils.setResultData("true","Creat Success!").toString();
    }

    /**
     * 更新vip信息
     * @param msg
     * @return
     */
    public String updateVipData(String msg)
    {
        JSONObject ms = JsonUtils.getValue(msg);
        String viptype=null;
        int viptime=0;
        int vipprice=0;
        try {
            viptype=ms.getString("viptype");
            viptime=ms.getInt("viptime");
            vipprice=ms.getInt("vipprice");

        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Update Fail!").toString();
        }
        OrbVip orbbecVip= OrbVipImpl.getInstance().findByType("");
        if (orbbecVip!=null)
        {
            OrbVip orbbecVips =new OrbVip();
            orbbecVips.setVip_id(orbbecVip.getVip_id());
            orbbecVips.setVip_type(viptype);
            orbbecVips.setVip_time(viptime);
            orbbecVips.setVip_price(vipprice);
            OrbVipImpl.getInstance().updateOrbbecVip(orbbecVips);
            return  JsonUtils.setResultData("true","Update Success!").toString();
        }

        return  JsonUtils.setResultData("false","Update Fail!").toString();
    }

    /**
     * 删除vip信息
     * @param msg
     * @return
     */
    public String deleteVipData(String msg)
    {
        JSONObject ms = JsonUtils.getValue(msg);
        String viptype=null;
        try {
            viptype=ms.getString("viptype");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Delete Fail!").toString();
        }

        OrbVip orbbecVip= OrbVipImpl.getInstance().findByType("");
        if (orbbecVip!=null)
        {
            OrbVipImpl.getInstance().deleteById(orbbecVip.getVip_id());
            return  JsonUtils.setResultData("true","Delete Success!").toString();
        }
        return  JsonUtils.setResultData("false","Delete Fail!").toString();
    }


    /**
     * 通过类型获得想要的套餐类型
     * @param msg
     * @return
     */
    public String getVipData(String msg)
    {
        JSONObject ms = JsonUtils.getValue(msg);
        String viptype=null;
        try {
            viptype=ms.getString("viptype");
        }catch (Exception e)
        {
            return  JsonUtils.setResultData("false","Get Fail!").toString();
        }

        if (viptype.equals(""))
        {
            return JSON.toJSONString(OrbVipImpl.getInstance().find());
        }else {
            return JSON.toJSONString(OrbVipImpl.getInstance().findByType(viptype));
        }
    }
}
