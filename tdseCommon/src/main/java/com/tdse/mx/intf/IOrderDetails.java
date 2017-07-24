package com.tdse.mx.intf;

import com.tdse.mx.dao.OrderDetails;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface IOrderDetails
{

    /**
     * 判断id是否存在
     * @return
     */
    public boolean findById(long id);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public OrderDetails findOrderDetailsById(long id);

    /**
     * 根据订单id查询
     * @param id
     * @return
     */
    public List<OrderDetails> findByOrderId(long id);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(long id);

    /**
     *  添加订单详情信息
     * @param orderDetails
     */
    public void addOrderDetails(OrderDetails orderDetails);

    /**
     * 更新订单详情信息
     * @param orderDetails
     */
    public void updateOrderDetails(OrderDetails orderDetails);
}
