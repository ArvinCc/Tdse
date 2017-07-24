package com.tdse.mx.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class JsonUtils
{
    /**
     * 获得信息指定内容
     * @param msg 信息
     * @param key 标签
     * @return
     */
    public static  Object getValue(String msg,String key){

        Object json = new JSONTokener(msg).nextValue();

        if(json instanceof JSONArray)
        {
            JSONArray jsonArray =new JSONArray(msg);
            Iterator<Object> it = jsonArray.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                if(ob.has(key)&&ob.get(key)!=null){
                    return ob.get(key);
                }
            }
        }else if (json instanceof JSONObject){
            JSONObject jsonObject =new JSONObject(msg);
            if(jsonObject.has(key)&&jsonObject.get(key)!=null){
                return jsonObject.get(key);
            }
        }
        return  null;
    }


    /**
     *  提取信息
     * @param msg
     * @return
     */
    public static JSONObject getValue(String msg)
    {
        Object json = new JSONTokener(msg).nextValue();

        if(json instanceof JSONArray) {
            JSONArray jsonArray = new JSONArray(msg);
            return jsonArray.getJSONObject(1);
        }
            return  null;
    }

    /**
     * 封装结果集
     * @param result
     * @param resultMsg
     * @return
     */
    public static JSONObject setResultData(String result,Object resultMsg){

        JSONObject jsonObject =new JSONObject();
        jsonObject.put("result",result);
        jsonObject.put("resultMsg",resultMsg);
        return  jsonObject;
    }
}
