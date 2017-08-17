package com.tdse.mx.home;

import com.tdse.mx.event.TimingEvent;
import com.tdse.mx.util.Utils;
import io.netty.util.internal.SystemPropertyUtil;

import javax.swing.*;
import java.io.*;

/**
 * Created by Administrator on 2017/7/7.
 */
public class RunState
{
    private File logServiceFile = null;
    private File logErrorFile = null;
    private File logUserFile = null;

    private long logServiceSize = 0; // 上次文件大小
    private long logErrorSize = 0;
    private long logUserSize = 0;


    private JPanel State;
    private JTextArea textInfo;
    private JScrollPane ScrollInfo;
    private JTextArea textUser;
    private JTextArea textError;
    private JScrollPane ScrollError;
    private JScrollPane ScrollUser;
    private JLabel currentTime;
    private JLabel runTime;

    public RunState()
    {
        //测试
        logServiceFile=new File( "./logs/service_log/service_log.log");
        logErrorFile=new File( "./logs/error_log/error_log.log");
        logUserFile=new File( "./logs/user_log/user_log.log");
        UpdateUi();
    }

    public void Init()
    {
        JFrame frame =new JFrame("服务器状态");
        JPanel satePane =new RunState().State;
        frame.setContentPane(satePane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(satePane);//居中
        ScrollInit(ScrollInfo);
        ScrollInit(ScrollError);
        ScrollInit(ScrollUser);
        frame.setVisible(true);
    }


    private void ScrollInit(JScrollPane jScrollPane){

        jScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }


    public void UpdateUi()
    {
        Thread thread =new Thread(new Runnable() {
            public void run() {
                while (true) {
                    logServiceSize=ShowData(logServiceFile,logServiceSize,textInfo);
                    logErrorSize=ShowData(logErrorFile,logErrorSize,textError);
                    logUserSize=ShowData(logUserFile,logUserSize,textUser);
                    currentTime.setText(String.valueOf(Utils.getCurrentTime()));
                    runTime.setText(String.valueOf(TimingEvent.getInstance().getRunTime())+"分钟");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }


    private long ShowData(File file,long size,JTextArea text)
    {
        try {
            RandomAccessFile randomFile = new RandomAccessFile(file, "r");
            randomFile.seek(size);
            String tmp=null;
            byte[] t_utf8=null;
            while ((tmp = randomFile.readLine()) != null)
            {
                t_utf8= tmp.getBytes("ISO-8859-1");
                text.append(new String(t_utf8,"UTF-8")+"\r\n");
            }
            return  randomFile.length();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
