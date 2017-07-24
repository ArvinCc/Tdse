package com.tdse.mx.db;

import com.tdse.mx.dao.OrbVip;
import com.tdse.mx.intf.IOrbbecVip;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public class OrbVipImpl implements IOrbbecVip
{

    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static OrbVipImpl instance = null;


    public OrbVipImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    public OrbVip findById(int id) {
        String statement = "orbbecVipMapper.findById";
        OrbVip orderDetails = session.selectOne(statement,id);
        session.commit();
        return orderDetails;
    }

    public OrbVip findByType(String type) {
        String statement = "orbbecVipMapper.findByType";
        OrbVip orderDetails = session.selectOne(statement,type);
        session.commit();
        return orderDetails;
    }

    public List<OrbVip> find() {
        String statement = "orbbecVipMapper.find";
        List<OrbVip> orderDetails = session.selectList(statement);
        session.commit();
        return orderDetails;
    }

    public void deleteById(int id)
    {
        String statement = "orbbecVipMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void addOrbbecVip(OrbVip order)
    {
        String statement="orbbecVipMapper.addOrbbecVip";
        session.insert(statement,order);
        session.commit();
    }

    public void updateOrbbecVip(OrbVip order)
    {
        String statement="orbbecVipMapper.updateOrbbecVip";
        session.update(statement,order);
        session.commit();
    }
}
