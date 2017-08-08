package com.tdse.mx.db;

import com.tdse.mx.dao.OrbVip;

/**
 * Created by Administrator on 2017/7/17.
 */
public class OrbVipImpl extends Impl<OrbVip>
{
    private final String mapperName="orbbecVipMapper";
    //使用volatile关键字保其可见性
    volatile private static OrbVipImpl instance = null;


    public OrbVipImpl() {}

    public static OrbVipImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbVipImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrbVipImpl();
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
