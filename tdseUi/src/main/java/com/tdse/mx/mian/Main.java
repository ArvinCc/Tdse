package com.tdse.mx.mian;

import com.tdse.mx.home.Home;
import com.tdse.mx.log.TestLog;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Main
{

    public static void main (String[] args)
    {
        try
        {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        new Home();
    }
}
