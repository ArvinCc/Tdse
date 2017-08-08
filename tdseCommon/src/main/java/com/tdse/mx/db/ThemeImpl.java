package com.tdse.mx.db;

import com.tdse.mx.dao.Theme;
import com.tdse.mx.intf.IOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class ThemeImpl extends Impl<Theme>
{
    private final String mapperName="theMapper";
    //使用volatile关键字保其可见性
    volatile private static ThemeImpl instance = null;

    private ThemeImpl() {}

    public static ThemeImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (ThemeImpl.class) {
                    if(instance == null){//二次检查
                        instance = new ThemeImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }
    @Override
    public String getMapperName()
    {
        return mapperName;
    }

}
