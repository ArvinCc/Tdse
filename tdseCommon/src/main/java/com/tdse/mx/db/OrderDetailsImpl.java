package com.tdse.mx.db;

import com.tdse.mx.dao.OrderDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class OrderDetailsImpl extends Impl<OrderDetails>
{
    private final String mapperName="orderDetailsMapper";
    //使用volatile关键字保其可见性
    volatile private static OrderDetailsImpl instance = null;

    public OrderDetailsImpl() {}

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
    @Override
    public String getMapperName()
    {
        return mapperName;
    }
}
