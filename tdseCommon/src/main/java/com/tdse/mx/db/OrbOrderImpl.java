package com.tdse.mx.db;

import com.tdse.mx.dao.OrbOrder;
import com.tdse.mx.intf.IOrbbecOrder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public class OrbOrderImpl implements IOrbbecOrder
{

    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static OrbOrderImpl instance = null;


    public OrbOrderImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OrbOrderImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbOrderImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrbOrderImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public OrbOrder findById(String id)
    {
        String statement = "orbbecOrderMapper.findById";
        OrbOrder orderDetails = session.selectOne(statement,id);
        session.commit();
        return orderDetails;
    }

    public List<OrbOrder> findByUserName(String name)
    {
        String statement = "orbbecOrderMapper.findByUserName";
        List<OrbOrder> orderDetails = session.selectList(statement,name);
        session.commit();
        return orderDetails;
    }

    public List<OrbOrder> find()
    {
        String statement = "orbbecOrderMapper.find";
        List<OrbOrder> orderDetails = session.selectList(statement);
        session.commit();
        return orderDetails;
    }

    public void deleteById(String id)
    {
        String statement = "orbbecOrderMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void addOrbbecOrder(OrbOrder order)
    {
        String statement="orbbecOrderMapper.addOrbbecOrder";
        session.insert(statement,order);
        session.commit();
    }

    public void updateOrbbecOrder(OrbOrder order)
    {
        String statement="orbbecOrderMapper.updateOrbbecOrder";
        session.update(statement,order);
        session.commit();
    }
}
