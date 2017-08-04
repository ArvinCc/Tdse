package com.tdse.mx.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Administrator on 2017/7/28.
 */
public class DbUtils
{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static DbUtils instance = null;

    public DbUtils() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DbUtils getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (DbUtils.class) {
                    if(instance == null){//二次检查
                        instance = new DbUtils();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public SqlSessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public SqlSession getSession()
    {
        return session;
    }


}
