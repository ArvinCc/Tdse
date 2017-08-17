package com.tdse.mx.event;

import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.Utils;

import java.io.UnsupportedEncodingException;


/**
 * Created by Administrator on 2017/6/16.
 */
public class SocketEventManager extends Event
{
    //使用volatile关键字保其可见性
    volatile private static SocketEventManager instance = null;

    private SocketEventManager(){}

    public static SocketEventManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (SocketEventManager.class) {
                    if(instance == null){//二次检查
                        instance = new SocketEventManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public  byte[] requestHandler(byte[] req) throws Exception
    {
        String id=null;
        String c = null;
        String msgContent=null;
        try {
            byte[] pkgId=new byte[6];
            byte[] contentLength =new byte[8];
            System.arraycopy(req,0,pkgId,0,6);
            System.arraycopy(req,6,contentLength,0,8);
            c = new String(contentLength,"UTF-8");
            int length= Integer.parseInt(c.trim());
            byte[] content =new byte[length];
            System.arraycopy(req,pkgId.length+contentLength.length,content,0,content.length);
            id =new String(pkgId,"UTF-8");
            msgContent =new String(content,"UTF-8");
            //FileDemo.getInstance().Into("客户端传过来的数据：数据包ID是"+id+"||内容是："+msgContent);
            System.out.println("客户端传过来的数据：数据包ID是"+id+"||内容是："+msgContent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

       String s = JsonUtils.getValue(msgContent,"requestType").toString();
        if (s!=null){
           return  Utils.msgPkg(id,requestDistribute(msgContent,s));
        }

        return Utils.msgPkg(id,Utils.stringBytes("requestType error!"));
    }

    /**
     *  请求分发
     * @param msg
     */
    private  byte[] requestDistribute(String msg,String requset)throws Exception
    {
        RequestType requestType =RequestType.GetType(requset);
        switch (requestType)
        {
            case REQUEST_GET:
                return   getDistribute(msg);
            case REQUEST_POST:
                return   postDistribute(msg);
        }
        return "requestType error!".getBytes();
    }

    /**
     * 请求类型
     */
    private enum RequestType
    {
        REQUEST_GET,
        REQUEST_POST;

        public static  RequestType GetType(String  requestType){
            return valueOf(requestType.toUpperCase());
        }
    }

}
