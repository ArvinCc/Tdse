package com.tdse.mx.db;

import com.tdse.mx.intf.IOperation;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class Impl<T> implements IOperation<T>
{
    public String mapperName;

    public List<T> find()
    {
        List<T> ts = DbUtils.getInstance().getSession().selectList(mapperName+".findAll");
        DbUtils.getInstance().getSession().commit();
        return ts;
    }

    public List<T> find(T t)
    {
        List<T> ts = DbUtils.getInstance().getSession().selectList(mapperName+".find",t);
        DbUtils.getInstance().getSession().commit();
        return ts;
    }

    public void delete()
    {
        DbUtils.getInstance().getSession().delete(mapperName+".deleteAll");
        DbUtils.getInstance().getSession().commit();
    }

    public void delete(T t)
    {
        DbUtils.getInstance().getSession().delete(mapperName+".delete", t);
        DbUtils.getInstance().getSession().commit();
    }

    public void add(List<T> t)
    {
        DbUtils.getInstance().getSession().insert(mapperName+".addList", t);
        DbUtils.getInstance().getSession().commit();
    }

    public void add(T t)
    {
        DbUtils.getInstance().getSession().insert(mapperName+".add", t);
        DbUtils.getInstance().getSession().commit();
    }

    public void update(T t)
    {
        DbUtils.getInstance().getSession().update(mapperName+".update", t);
        DbUtils.getInstance().getSession().commit();
    }

    public void update(List<T> t)
    {
        DbUtils.getInstance().getSession().update(mapperName+".updateList", t);
        DbUtils.getInstance().getSession().commit();
    }

}
