package com.tdse.mx.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Administrator on 2017/8/9.
 */
public class TestLog
{
   private Logger user_log = LoggerFactory.getLogger("user_log");
   private Logger service_log = LoggerFactory.getLogger("service_log");
   private Logger error_log = LoggerFactory.getLogger("error_log");

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    //使用volatile关键字保其可见性
    volatile private static TestLog instance = null;

    private TestLog(){}

    public static TestLog getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (TestLog.class) {
                    if(instance == null){//二次检查
                        instance = new TestLog();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public void WriteUserLog (String content)
    {
       user_log.info(dateFormat.format(new Date()) + "\t"
               +content);
    }

    public void  WriteServiceLog(String content)
    {
        service_log.info(dateFormat.format(new Date()) + "\t"
                +content);
    }

    public void WriteErrorLog(String content)
    {
        error_log.info(dateFormat.format(new Date()) + "\t"
                +content);
    }

}
