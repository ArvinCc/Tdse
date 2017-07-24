package com.tdse.mx.server;

/**
 * Created by Administrator on 2017/6/15.
 */
public class TdseMain
{
    public static void main(String[] args) throws Exception {

        int port;

        if (args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        } else {
            port = 3300;
        }

        Thread thread =new Thread(new SocketServer(port));
        Thread threads =new Thread(new HttpServer(3200));
        thread.start();
        threads.start();
    }
}
