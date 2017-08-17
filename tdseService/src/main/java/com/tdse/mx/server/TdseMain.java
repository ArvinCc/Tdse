package com.tdse.mx.server;

import com.tdse.mx.event.TimingEvent;
import com.tdse.mx.log.TestLog;

/**
 * Created by Administrator on 2017/6/15.
 */
public class TdseMain
{

     public TdseMain(){}


     public void Start()
     {
         TestLog.getInstance().WriteServiceLog("服务器开始启动~~~~~~~~~~！");
         Thread thread =new Thread(new SocketServer(3300));
         Thread threads =new Thread(new HttpServer(3200));
         thread.start();
         threads.start();
     }


//
//    public static void main(String[] args) throws Exception {
//
//        int port;
//
//        if (args.length > 0)
//        {
//            port = Integer.parseInt(args[0]);
//        } else {
//            port = 3300;
//        }
//
//        Thread thread =new Thread(new SocketServer(port));
//        Thread threads =new Thread(new HttpServer(3200));
//        thread.start();
//        threads.start();
//
//        Thread threadss =new Thread(new TimingEvent());
//        threadss.start();
//
//    }
}
