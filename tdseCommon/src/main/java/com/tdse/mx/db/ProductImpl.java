package com.tdse.mx.db;

import com.tdse.mx.dao.Product;
import com.tdse.mx.dao.Theme;
import com.tdse.mx.intf.IProduct;
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
public class ProductImpl implements IProduct
{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;

    //使用volatile关键字保其可见性
    volatile private static ProductImpl instance = null;

    public ProductImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductImpl getInstance() {
        try {
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

    public List<Product> findProduct() {
        String statement = "productMapper.findProduct";
        List<Product> products = session.selectList(statement);
        session.commit();
        return products;
    }

    public Product findProductById(String id) {
        String statement = "productMapper.findProductById";
        Product products = session.selectOne(statement,id);
        session.commit();
        return products;
    }

    public boolean findById(String id) {
        String statement = "productMapper.findById";
        if (session.selectOne(statement, id)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public Product findProductByName(String name) {
        String statement = "productMapper.findProductByName";
        Product products = session.selectOne(statement,name);
        session.commit();
        return products;
    }

    public boolean findByName(String name) {
        String statement = "productMapper.findByName";
        if (session.selectOne(statement, name)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public void deleteById(String id) {
        String statement = "productMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void deleteByName(String name) {
        String statement = "productMapper.deleteByName";
        session.delete(statement, name);
        session.commit();
    }

    public void addProduct(Product product) {
        String statement = "productMapper.addProduct";
        session.insert(statement, product);
        session.commit();
    }

    public void updateProduct(Product product) {
        String statement = "productMapper.updateProduct";
        session.update(statement,product);
        session.commit();
    }
}
