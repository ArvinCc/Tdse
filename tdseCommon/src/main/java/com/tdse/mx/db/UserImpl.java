package com.tdse.mx.db;

import com.tdse.mx.dao.User;
import com.tdse.mx.intf.IOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class UserImpl extends Impl<User>
{

    private final String mapperName="userMapper";

    //使用volatile关键字保其可见性
    volatile private static UserImpl instance = null;

    private UserImpl() {}

    public static UserImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (UserImpl.class) {
                    if(instance == null){//二次检查
                        instance = new UserImpl();
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
