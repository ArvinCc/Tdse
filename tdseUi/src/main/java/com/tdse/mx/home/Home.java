package com.tdse.mx.home;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Home implements  ActionListener
{
    private JPanel Home;
    private JButton Start;
    private JPanel ServerSetting;
    private JTextField PortValue;
    private JLabel Port;

    private JFrame frame;


     public  void  Init()
     {
        frame =new JFrame("服务器");
        JPanel homePane =new Home().Home;
        frame.setContentPane(homePane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(400, 300);

         Start =this.Start;
         frame.add(Start);

        frame.setLocationRelativeTo(homePane);//居中
        frame.setVisible(true);



    }


    public  void Starts()
    {

        frame.dispose();
        new RunState().Init();
    }


    public void actionPerformed(ActionEvent e) {
System.out.println("asd"+e.getID());
    }


}
