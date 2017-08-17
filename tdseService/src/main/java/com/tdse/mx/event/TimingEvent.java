package com.tdse.mx.event;

import com.tdse.mx.log.TestLog;
import com.tdse.mx.manager.OrbOrderManager;
import com.tdse.mx.util.Utils;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/8/8.
 */
public class TimingEvent
{

    public Timestamp getServiceStartTime() {
        return serviceStartTime;
    }

    public Timestamp getClearTime() {
        return clearTime;
    }

    public long getRunTime() {
        return runTime;
    }

    private Timestamp serviceStartTime;
    private Timestamp clearTime;
    private long runTime;

    volatile private static TimingEvent instance = null;

    private TimingEvent()
    {
        serviceStartTime = Utils.getCurrentTime();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                runTask();
            }
        });
        thread.start();
    }

    public static TimingEvent getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (TimingEvent.class) {
                    if(instance == null){//二次检查
                        instance = new TimingEvent();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    private void runTask()
    {
        while (true)
        {
           // System.out.println("当前时间"+Utils.getCurrentTime().toString());
            runTime=(Utils.getCurrentTime().getTime()-serviceStartTime.getTime())/(1000*60);
            clearTask();
            //System.out.println("清理时间"+clearTime.toString());
            //System.out.println("已经运行"+runTime+"分钟");
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void clearTask()
    {
     if (clearTime==null||(Utils.getCurrentTime().getTime()-clearTime.getTime())/(1000*60)>360)
        {
            TestLog.getInstance().WriteErrorLog("开始清理垃圾订单!!!!");
            OrbOrderManager.getInstance().clearOrder();
            clearTime =Utils.getCurrentTime();
            TestLog.getInstance().WriteErrorLog("清理垃圾订单完毕!!!!");
        }
    }

}
