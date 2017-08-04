package com.tdse.mx.db;

import com.tdse.mx.dao.Order;
import com.tdse.mx.intf.IOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class OrderImpl extends Impl<Order>
{
    //使用volatile关键字保其可见性
    volatile private static OrderImpl instance = null;

    public OrderImpl()
    {
      mapperName="orderMapper";
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

}
