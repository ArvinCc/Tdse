package com.tdse.mx.db;

import com.tdse.mx.dao.OrbUser;
import com.tdse.mx.intf.IOrbbecUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Administrator on 2017/7/17.
 */
public class OrbUserImpl implements IOrbbecUser
{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static OrbUserImpl instance = null;


    public OrbUserImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    public OrbUser findById(long id)
    {
        String statement = "orbbecUserMapper.findById";
        OrbUser orderDetails = session.selectOne(statement,id);
        session.commit();
        return orderDetails;
    }

    public OrbUser findByName(String name)
    {
        String statement = "orbbecUserMapper.findByName";
        OrbUser orderDetails = session.selectOne(statement,name);
        session.commit();
        return orderDetails;
    }

    public void deleteById(long id)
    {
        String statement = "orbbecUserMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void addOrbbecUser(OrbUser order)
    {
        String statement="orbbecUserMapper.addOrbbecUser";
        session.insert(statement,order);
        session.commit();
    }

    public void updateOrbbecUser(OrbUser order)
    {
        String statement="orbbecUserMapper.updateOrbbecUser";
        session.update(statement,order);
        session.commit();
    }
}
