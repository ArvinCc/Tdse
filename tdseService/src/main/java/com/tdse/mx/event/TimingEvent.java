package com.tdse.mx.event;

import com.tdse.mx.manager.OrbOrderManager;
import com.tdse.mx.util.Utils;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/8/8.
 */
public class TimingEvent extends Thread
{

    public Timestamp serviceStartTime;
    public Timestamp clearTime;
    public long runTime;

    public TimingEvent()
    {
        serviceStartTime = Utils.getCurrentTime();
    }

    @Override
    public void run()
    {
        System.out.println("初始时间"+serviceStartTime);

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
            OrbOrderManager.getInstance().clearOrder();
            clearTime =Utils.getCurrentTime();
        }
    }

}
