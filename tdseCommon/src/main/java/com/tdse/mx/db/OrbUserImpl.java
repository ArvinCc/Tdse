package com.tdse.mx.db;

import com.tdse.mx.dao.OrbUser;

/**
 * Created by Administrator on 2017/7/17.
 */
public class OrbUserImpl extends Impl<OrbUser>
{
    private final String mapperName="orbbecUserMapper";

    //使用volatile关键字保其可见性
    volatile private static OrbUserImpl instance = null;

    public OrbUserImpl() {}

    public static OrbUserImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbUserImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrbUserImpl();
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
