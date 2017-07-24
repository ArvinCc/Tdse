package com.tdse.mx.manager;

import com.tdse.mx.dao.Theme;

/** lcc 主题管理类
 * Created by Administrator on 2017/6/12.
 */
public class ThemeManager {

    //使用volatile关键字保其可见性
    volatile private static ThemeManager instance = null;

    private ThemeManager(){}


    public static ThemeManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (ThemeManager.class) {
                    if(instance == null){//二次检查
                        instance = new ThemeManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     *  添加一个主题
     * @param theme 主题类
     * @return 是否成功
     */
    public boolean add(Theme theme)
    {

        return  false;
    }


    /**
     *  根据主题编号删除一个主题
     * @param id 主题编号
     * @return 是否成功
     */
    public boolean remove(long id)
    {

        return false;
    }

    /**
     *  根据主题编号查询主题信息
     * @param id 主题编号
     * @return 主题类(没有查询或者查询失败则会返回为空)
     */
    public Theme query(long id)
    {

        return  null;
    }


    /**
     *  根据主题名字查询主题信息
     * @param name 主题名称
     * @return 主题类(没有查询或者查询失败则会返回为空)
     */
    public Theme query(String name){


        return  null;
    }

    /**
     *  根据主题名字更新指定主题信息
     * @param name 主题名称
     * @return 是否成功
     */
    public boolean update(String name){


        return  false;
    }

    /**
     *  根据主题编号更新指定主题信息
     * @param id 主题编号
     * @return 是否成功
     */
    public boolean update(long id){

        return false;
    }
}
