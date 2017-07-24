package com.tdse.mx.db;

import com.tdse.mx.dao.Order;
import com.tdse.mx.dao.Theme;
import com.tdse.mx.intf.IOrder;
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
public class OrderImpl implements IOrder {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static OrderImpl instance = null;

    public OrderImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OrderImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrderImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrderImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public Order findOrderById(long id) {
        String statement = "orderMapper.findOrderById";
        Order order = session.selectOne(statement,id);
        session.commit();
        return order;
    }

    public List<Order> findOrderByUserId(long id) {
        String statement = "orderMapper.findOrderByUserId";
        List<Order> orders = session.selectList(statement,id);
        session.commit();
        return orders;
    }

    public void deleteById(long id) {
        String statement = "orderMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void addOrder(Order order) {
        String statement = "orderMapper.addOrder";
        session.insert(statement, order);
        session.commit();
    }

    public void updateOrder(Order order) {
        String statement = "orderMapper.updateOrder";
        session.update(statement,order);
        session.commit();
    }
}
