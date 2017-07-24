package com.tdse.mx.intf;

import com.tdse.mx.dao.Product;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface IProduct
{

    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findProduct();

    /**
     *  根据id查询
     * @param id
     * @return
     */
    public Product findProductById(String id);

    /**
     * 判断id是否存在
     * @param id
     * @return
     */
    public boolean findById(String id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public Product findProductByName(String name);

    /**
     * 判断名称是否存在
     * @param name
     * @return
     */
    public boolean findByName(String name);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(String id);

    /**
     * 根据名字删除
     * @param name
     */
    public void deleteByName(String name);

    /**
     * 添加新的产品
     * @param product
     */
    public void addProduct(Product product);

    /**
     * 更新产品
     * @param product
     */
    public void updateProduct(Product product);

}
