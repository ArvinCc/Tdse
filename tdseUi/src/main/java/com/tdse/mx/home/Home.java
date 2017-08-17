package com.tdse.mx.home;

import com.tdse.mx.server.TdseMain;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Home
{
    private JButton Start;
    private JPanel ServerSetting;
    private JPanel Home;

    private JFrame frame;

    public Home()
    {
        Init();
    }

     public  void  Init()
     {
        frame =new JFrame("WelCome");
        frame.setContentPane(ServerSetting);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(ServerSetting);//居中
        frame.pack();
        frame.setVisible(true);
        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TdseMain().Start();
                new RunState().Init();
                frame.dispose();
            }
        });
    }
}
