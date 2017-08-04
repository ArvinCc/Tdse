package com.tdse.mx.intf;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public interface IOperation <T>
{
    List<T> find();

    List<T> find(T t);

    void delete();

    void delete(T t);

    void add(List<T> t);

    void add(T t);

    void update(T t);

    void update(List<T> t);
}
