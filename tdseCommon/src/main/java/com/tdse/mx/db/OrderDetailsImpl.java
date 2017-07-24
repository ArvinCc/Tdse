package com.tdse.mx.db;

import com.tdse.mx.dao.OrderDetails;
import com.tdse.mx.intf.IOrderDetails;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class OrderDetailsImpl implements IOrderDetails
{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static OrderDetailsImpl instance = null;

    public OrderDetailsImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OrderDetailsImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrderDetailsImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrderDetailsImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public boolean findById(long id) {
        String statement = "orderDetailsMapper.findById";
        if (session.selectOne(statement, id)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public OrderDetails findOrderDetailsById(long id)
    {
        String statement = "orderDetailsMapper.findOrderDetailsById";
        OrderDetails orderDetails = session.selectOne(statement,id);
        session.commit();
        return orderDetails;
    }

    public List<OrderDetails> findByOrderId(long id)
    {
        String statement = "orderDetailsMapper.findByOrderId";
        List<OrderDetails> orderDetails = session.selectList(statement);
        session.commit();
        return orderDetails;
    }

    public void deleteById(long id)
    {
        String statement = "orderDetailsMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void addOrderDetails(OrderDetails orderDetails)
    {
        String statement = "orderDetailsMapper.addOrderDetails";
        session.insert(statement, orderDetails);
        session.commit();
    }

    public void updateOrderDetails(OrderDetails orderDetails)
    {
        String statement = "orderDetailsMapper.updateOrderDetails";
        session.update(statement,orderDetails);
        session.commit();
    }
}
