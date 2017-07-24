package com.tdse.mx.intf;

import com.tdse.mx.dao.Theme;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public interface ITheme {

    /**
     * 查询所有主题
     * @return
     */
    public List<Theme> findTheme();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Theme findThemeById(String id);

    /**
     * 判断ID是否存在
     * @return
     */
    public boolean findById(String id);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public Theme findThemeByName(String name);

    /**
     * 判断名字是否存在
     * @param name
     * @return
     */
    public boolean findByName(String name);

    /**
     * 根据id删除
     * @param id
     */
    public  void deleteById(String id);

    /**
     * 根据名字删除
     * @param name
     */
    public void deleteByName(String name);

    /**
     * 添加新的主题
     * @param theme
     */
    public void addTheme(Theme theme);

    /**
     *  更新主题信息
     * @param theme
     */
    public void updateTheme(Theme theme);

}
