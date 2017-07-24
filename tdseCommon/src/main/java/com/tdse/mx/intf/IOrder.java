package com.tdse.mx.intf;

import com.tdse.mx.dao.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface IOrder {


    /**
     * 根据订单编号查询
     * @param id
     * @return
     */
    public Order findOrderById(long id);

    /**
     * 根据用户id查询
     * @param id
     * @return 当前用户的所有订单
     */
    public List<Order> findOrderByUserId(long id);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(long id);

    /**
     * 添加订单
     * @param order
     */
    public void addOrder(Order order);

    /**
     *  更新订单信息
     * @param order
     */
    public void updateOrder(Order order);

}
