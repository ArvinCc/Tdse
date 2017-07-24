package com.tdse.mx.intf;

import com.tdse.mx.dao.OrbOrder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public interface IOrbbecOrder
{
    /**
     * 根据订单编号查询
     * @param id
     * @return
     */
    public OrbOrder findById(String id);


    /**
     * 查询所有订单
     * @return
     */
    public List<OrbOrder> find();


    /**
     * 根据用户名字查询
     * @param name
     * @return 当前用户的所有订单
     */
    public List<OrbOrder> findByUserName(String name);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(String id);

    /**
     * 添加订单
     * @param order
     */
    public void addOrbbecOrder(OrbOrder order);

    /**
     *  更新订单信息
     * @param order
     */
    public void updateOrbbecOrder(OrbOrder order);


}
