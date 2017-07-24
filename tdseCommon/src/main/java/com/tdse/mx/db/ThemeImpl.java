package com.tdse.mx.db;

import com.tdse.mx.dao.Theme;
import com.tdse.mx.intf.ITheme;
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
public class ThemeImpl implements ITheme{

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    //使用volatile关键字保其可见性
    volatile private static ThemeImpl instance = null;

    private ThemeImpl() {
        String resource = "mybatisconf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ThemeImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (ThemeImpl.class) {
                    if(instance == null){//二次检查
                        instance = new ThemeImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public List<Theme> findTheme() {
        String statement = "theMapper.find";
        List<Theme> themes = session.selectList(statement);
        session.commit();
        return themes;
    }

    public Theme findThemeById(String id) {
        String statement = "theMapper.findThemeById";
        Theme themes = session.selectOne(statement,id);
        session.commit();
        return themes;
    }

    public boolean findById(String id) {
        String statement = "theMapper.findById";
        if (session.selectOne(statement, id)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public Theme findThemeByName(String name) {
        String statement = "theMapper.findThemeByName";
        Theme themes = session.selectOne(statement,name);
        session.commit();
        return themes;
    }

    public boolean findByName(String name)
    {
        String statement = "theMapper.findByName";
        if (session.selectOne(statement, name)!=null){
            session.commit();
            return true;
        }
        session.commit();
        return false;
    }

    public void deleteById(String id) {
        String statement = "theMapper.deleteById";
        session.delete(statement, id);
        session.commit();
    }

    public void deleteByName(String name) {
        String statement = "theMapper.deleteByName";
        session.delete(statement, name);
        session.commit();
    }

    public void addTheme(Theme theme) {
        String statement = "theMapper.addTheme";
        session.insert(statement, theme);
        session.commit();
    }

    public void updateTheme(Theme theme) {
        String statement = "theMapper.updateTheme";
        session.update(statement,theme);
        session.commit();
    }
}
