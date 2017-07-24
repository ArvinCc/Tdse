package com.tdse.mx.event;

import com.tdse.mx.manager.OrbOrderManager;
import com.tdse.mx.manager.OrbUserManager;
import com.tdse.mx.server.FileDemo;
import com.tdse.mx.util.Utils;

/**
 * Created by Administrator on 2017/7/17.
 */
public class HttpEventManager extends Event
{
    volatile private static HttpEventManager instance = null;

    private HttpEventManager(){}

    public static HttpEventManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (HttpEventManager.class) {
                    if(instance == null){//二次检查
                        instance = new HttpEventManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public byte[] getHandler(String msg)
    {
        return  getDistribute(msg);
    }

    public byte[] postHandler(String msg)
    {


        return  postDistribute(msg);
    }


    public void postOrder(String msg)
    {
        FileDemo.getInstance().Into("Http服务器 Post内容:"+msg+"时间:"+ Utils.getCurrentTime());
        OrbOrderManager.getInstance().dealOrder(msg);
    }

}

