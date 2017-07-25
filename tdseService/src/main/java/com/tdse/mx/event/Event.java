package com.tdse.mx.event;

import com.alibaba.fastjson.JSON;
import com.tdse.mx.manager.*;
import com.tdse.mx.server.FileDemo;
import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.Utils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public class Event
{
    /**
     * GET类型
     */
    public   enum GetType
    {

        GET_SIGN_IN,
        GET_SIGN_UP,
        GET_DATA_THEME,
        GET_DATA_USER,
        GET_DATA_PRODUCT,
        GET_DATA_ORDER,
        GET_DATA_ORDER_DETAILS,
        GET_FILE_DOWNLOAD,
        GET_DATA_ORBBEC_SIGN,
        GET_DATA_ORBBEC_VIP,
        GET_DATA_ORBBEC_USER_VIP_TIME,
        GET_DATA_ORBBEC_ORDER;
        public static  GetType GetType(String getType){
            return valueOf(getType.toUpperCase());
        }
    }

    /**
     * POST类型
     */
    public enum PostType
    {
        POST_DATA_THEME,
        POST_DATA_USER,
        POST_DATA_PRODUCT,
        POST_DATA_ORDER,
        POST_DATA_ORDER_DETAILS,
        POST_FILE_UPLOAD;
        public static  PostType GetType(String posType){
            return valueOf(posType.toUpperCase());
        }
    }






    /**
     * GET请求分发
     * @param msg
     */
    public  byte[] getDistribute(String msg)
    {
        try{
            GetType getType =GetType.GetType(JsonUtils.getValue(msg,"msgType").toString());

            switch (getType){
                case GET_SIGN_IN:
                    return reResult("REQUEST_GET","GET_SIGN_IN", UserManager.getInstance().signIn(msg)).getBytes();
                case GET_SIGN_UP:
                    return reResult("REQUEST_GET","GET_SIGN_UP",UserManager.getInstance().signUp(msg)).getBytes();
                case GET_DATA_USER:
                    System.out.println("客户端请求数据：GET_DATA_USER");
                    break;
                case GET_DATA_ORDER:
                    System.out.println("客户端请求数据：GET_DATA_ORDER");
                    break;
                case GET_DATA_THEME:
                    System.out.println("客户端请求数据：GET_DATA_THEME");
                    break;
                case GET_DATA_PRODUCT:
                    String producName = JsonUtils.getValue(msg,"producname").toString();
                    if (producName.equals(""))
                    {
                     return JSON.toJSONString(ProductManager.getInstance().query()).getBytes();
                    }else {
                     return JSON.toJSONString(ProductManager.getInstance().queryName(producName)).getBytes();
                    }
                case GET_FILE_DOWNLOAD:
                    String state = JsonUtils.getValue(msg,"state").toString();
                    String name =JsonUtils.getValue(msg,"name").toString();
                    int number=Integer.parseInt(JsonUtils.getValue(msg,"number").toString());
                    byte[] bytes=null;
                    JSONObject jsonObject= ProductManager.getInstance().getUrl(name);
                    if (jsonObject.getString("result").trim().equals("true"));
                {
                    String filePath=jsonObject.get("resultMsg").toString();
                    System.out.println("大苏打||"+filePath);
                    if (state.trim().equals("on"))
                    {
                        bytes= reResult("REQUEST_GET","GET_DATA_PRODUCT",ProductManager.getInstance().getFileDataByte(filePath)).getBytes();
                    }else if (state.trim().equals("in"))
                    {
                        bytes=ProductManager.getInstance().getByte(filePath,number);
                        System.out.println("当前进度:"+number);
                    }
                }
                return bytes;
                case GET_DATA_ORDER_DETAILS:
                    System.out.println("客户端请求数据：GET_DATA_ORDER_DETAILS");
                    break;
                case GET_DATA_ORBBEC_SIGN:
                    return OrbOrderManager.getInstance().getSign(msg).getBytes();
                case GET_DATA_ORBBEC_ORDER:
                    return OrbOrderManager.getInstance().creatOrder(msg).getBytes();
                case GET_DATA_ORBBEC_VIP:
                    return OrbVipManager.getInstance().getVipData(msg).getBytes();
                case GET_DATA_ORBBEC_USER_VIP_TIME:
                    String states = JsonUtils.getValue(msg,"state").toString();
                    String usernames = JsonUtils.getValue(msg,"username").toString();
                    if(states.trim().equals("all")){
                        return OrbUserManager.getInstance().getUserVipTime(usernames).getBytes();
                    }else  if(states.trim().equals("second"))
                    {
                        return OrbUserManager.getInstance().getUserVipSurplusTime(usernames).getBytes();
                    }
                    break;
            }
        }catch (Exception e){
            System.out.println("GET请求错误"+e);
        }


        return  "msgType error!".getBytes();
    }

    public  byte[] postDistribute(String msg)
    {
        try{
            PostType postType =PostType.GetType(JsonUtils.getValue(msg,"msgType").toString());

            switch (postType)
            {
                case POST_DATA_USER:
                    System.out.println("客户端传递数据：POST_DATA_USER");
                    break;
                case POST_DATA_ORDER:
                    System.out.println("客户端传递数据：POST_DATA_ORDER");
                    break;
                case POST_DATA_THEME:
                    System.out.println("客户端传递数据：POST_DATA_THEME");
                    break;
                case POST_FILE_UPLOAD:
                    System.out.println("客户端传递数据：POST_FILE_UPLOAD");
                    break;
                case POST_DATA_PRODUCT:
                    System.out.println("客户端传递数据：POST_DATA_PRODUCT");
                    break;
                case POST_DATA_ORDER_DETAILS:
                    System.out.println("客户端传递数据：POST_DATA_ORDER_DETAILS");
                    break;
            }
        }catch (Exception e){
            System.out.println("GET请求错误"+e);
        }

        return  "msgType error!".getBytes();
    }

    public   String reResult(String relabel,String msglabel,JSONObject resultContent)
    {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("resultType",msglabel);
        jsonObject.put("result",relabel);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject);
        jsonObjects.add(resultContent);

        System.out.println("最终结果"+jsonObjects.toString());
        FileDemo.getInstance().Into("最终结果:"+jsonObjects.toString()+"时间:"+ Utils.getCurrentTime());
        return  jsonObjects.toString();
    }
}
