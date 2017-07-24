package com.tdse.mx.intf;


import com.tdse.mx.dao.OrbVip;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface IOrbbecVip
{
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public OrbVip findById(int id);

    public OrbVip findByType(String type);

    /**
     *
     * @param
     * @return 当前用户的所有订单
     */
    public List<OrbVip> find();

    /**
     * 根据id删除套餐
     * @param id
     */
    public void deleteById(int id);

    /**
     * 添加订套餐
     * @param order
     */
    public void addOrbbecVip(OrbVip order);

    /**
     *  更新套餐信息
     * @param order
     */
    public void updateOrbbecVip(OrbVip order);
}
