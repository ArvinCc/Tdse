package com.tdse.mx.db;

import com.tdse.mx.dao.Product;
import com.tdse.mx.intf.IOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class ProductImpl extends Impl<Product>
{
    private final String mapperName="productMapper";

    //使用volatile关键字保其可见性
    volatile private static ProductImpl instance = null;

    public ProductImpl() {}

    public static ProductImpl getInstance()
    {
        try
        {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (ProductImpl.class) {
                    if(instance == null){//二次检查
                        instance = new ProductImpl();
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
