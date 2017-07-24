package com.tdse.mx.db;

import com.tdse.mx.dao.User;
import com.tdse.mx.intf.IUser;
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
public class UserImpl implements IUser{

    private SqlSessionFactory sessionFactory;
    private SqlSession session;


    //使用volatile关键字保其可见性
    volatile private static UserImpl instance = null;

    private UserImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (UserImpl.class) {
                    if(instance == null){//二次检查
                        instance = new UserImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public List<User> findUser() {
        String statement = "userMapper.findUser";
        List<User> users = session.selectList(statement);
        session.commit();
        return users;
    }

    public User findUserById(long id) {
        String statement = "userMapper.findUserById";
        User user = (User)session.selectOne(statement, id);
        session.commit();
        return user;
    }

    public boolean findById(long id) {
        String statement = "userMapper.findById";
       if (session.selectOne(statement, id)!=null){
           session.commit();
           return true;
       }
       session.commit();
       return false;
    }

    public User findUserByPhone(long phoneNumber) {
        String statement = "userMapper.findUserByPhone";
        User user = (User)session.selectOne(statement, phoneNumber);
        session.commit();
        return user;
    }

    public boolean findByPhone(long phoneNumber) {
        String statement = "userMapper.findByPhone";
        if (session.selectOne(statement, phoneNumber)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public User finUserByName(String name) {
        String statement = "userMapper.findUserByName";
        User user = (User)session.selectOne(statement, name);
        session.commit();
        return user;
    }

    public boolean finByName(String name) {
        String statement = "userMapper.findByName";
        if (session.selectOne(statement, name)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public void addUser(User user) {
        String statement = "userMapper.addUser";
        session.insert(statement, user);
        session.commit();
    }

    public void deleteById(long id) {
        String statement = "userMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void deleteByPhone(long phoneNumber) {
        String statement = "userMapper.deleteByPhone";
        session.delete(statement, phoneNumber);
        session.commit();
    }

    public void deleteByName(String name) {
        String statement = "userMapper.deleteByName";
        session.delete(statement, name);
        session.commit();
    }


    public void updateUser(User user) {
        String statement = "userMapper.updateUser";
        session.update(statement, user);
        session.commit();
    }
}
