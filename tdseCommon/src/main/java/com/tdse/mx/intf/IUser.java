package com.tdse.mx.intf;

import com.tdse.mx.dao.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface IUser {

    /**
     * 查找所有用户
     * @return
     */
    public List<User> findUser();

    /**
     *  根据id查询
     * @param id
     * @return
     */
    public User findUserById(long id);

    /**
     *  根据id查询是否已经存在
     * @param id
     * @return
     */
    public boolean findById(long id);
    /**
     *  根据电话号码查询
     * @param phoneNumber
     * @return
     */
    public User findUserByPhone(long phoneNumber);

    /**
     * 根据电话号码查询是否已经存在
     * @param phoneNumber
     * @return
     */
    public boolean findByPhone(long phoneNumber);
    /**
     *  根据名字查询
     * @param name
     * @return
     */
    public User finUserByName(String  name);

    /**
     *  根据名字查询是否已经存在
     * @param name
     * @return
     */
    public boolean finByName(String name);

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     */
    public void deleteById(long id); //根据id删除用户信息

    /**
     *  根据电话号码删除用户信息
     * @param phoneNumber
     */
    public void deleteByPhone(long phoneNumber);

    /**
     * 根据名字删除用户信息
     * @param name
     */
    public void deleteByName(String name);

    /**
     *  更新用户信息
     * @param user
     */
    public void updateUser(User user);

}
