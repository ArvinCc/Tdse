package com.tdse.mx.home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2017/7/7.
 */
public class RunState
{

    private JButton button1;
    private JTextField textField1;
    private JPanel State;

    public void Init()
    {
        JFrame frame =new JFrame("服务器状态");
        JPanel satePane =new RunState().State;
        frame.setContentPane(satePane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(satePane);//居中
        frame.setVisible(true);
    }


}
