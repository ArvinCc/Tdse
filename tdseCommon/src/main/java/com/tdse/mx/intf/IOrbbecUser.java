package com.tdse.mx.intf;

import com.tdse.mx.dao.OrbUser;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface IOrbbecUser {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public OrbUser findById(long id);


    /**
     * 根据名字查找
     * @param
     * @return
     */
    public OrbUser findByName(String name);

    /**
     * 根据id删除用户
     * @param id
     */
    public void deleteById(long id);

    /**
     * 添加新用户
     * @param order
     */
    public void addOrbbecUser(OrbUser order);

    /**
     *  更新用户信息
     * @param order
     */
    public void updateOrbbecUser(OrbUser order);
}
