package com.tdse.mx.db;

import com.tdse.mx.dao.OrbOrder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public class OrbOrderImpl extends Impl<OrbOrder>
{
    private final String mapperName="orbbecOrderMapper";

    //使用volatile关键字保其可见性
    volatile private static OrbOrderImpl instance = null;

    public OrbOrderImpl() {}

    public static OrbOrderImpl getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrbOrderImpl.class) {
                    if(instance == null){//二次检查
                        instance = new OrbOrderImpl();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public String getMapperName()
    {
        return mapperName;
    }


    public void findPaging()
    {
        SqlSession session =DbUtils.getInstance().getSession();
        List<OrbOrder> ts = session.selectList(getMapperName()+".findAllPaging");
        session.commit();
        session.close();
    }
}
