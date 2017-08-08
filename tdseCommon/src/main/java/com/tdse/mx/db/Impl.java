package com.tdse.mx.db;

import com.tdse.mx.intf.IOperation;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class Impl<T> implements IOperation<T>
{

    public  String getMapperName()
    {
        return null;
    }

    public List<T> find()
    {
        SqlSession session =DbUtils.getInstance().getSession();
        List<T> ts = session.selectList(getMapperName()+".findAll");
        session.commit();
        session.close();
        return ts;
    }

    public List<T> find(T t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        List<T> ts =session.selectList(getMapperName()+".find",t);
        session.commit();
        session.close();
        return ts;
    }

    public void delete()
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.delete(getMapperName()+".deleteAll");
        session.commit();
        session.close();
    }

    public void delete(T t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.delete(getMapperName()+".delete", t);
        session.commit();
        session.close();
    }

    public void add(List<T> t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.insert(getMapperName()+".addList", t);
        session.commit();
        session.close();
    }

    public void add(T t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.insert(getMapperName()+".add", t);
        session.commit();
        session.close();
    }

    public void update(T t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.update(getMapperName()+".update", t);
        session.commit();
        session.close();
    }

    public void update(List<T> t)
    {
        SqlSession session =DbUtils.getInstance().getSession();
        session.update(getMapperName()+".updateList", t);
        session.commit();
        session.close();
    }

}
