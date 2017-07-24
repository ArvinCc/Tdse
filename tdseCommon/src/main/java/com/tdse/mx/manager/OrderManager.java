package com.tdse.mx.manager;

import com.tdse.mx.dao.Order;

/** lcc 订单管理类
 * Created by dell2 on 2017/5/31.
 */
public class OrderManager {

    //使用volatile关键字保其可见性
    volatile private static OrderManager instance = null;

    private OrderManager(){}

    public static OrderManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (OrderManager.class) {
                    if(instance == null){//二次检查
                        instance = new OrderManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * id字段
     */
    private final String id="order_id";
    /**
     *下单时间字段名
     */
    private final String time="order_establish_time";
    /**
     *下单用户id字段名
     */
    private final  String userId="order_user_id";


    /**
     *  下单
     * @param order 订单类
     * @return 是否成功
     */
    public boolean add(Order order) throws Exception{


        return  false;
    }

    /**
     *  根据订单编号删除订单
     * @param id 订单编号
     * @return 是否成功
     */
    public boolean remove(long id){


        return false;
    }

    /**
     *  根据订单编号查询订单信息
     * @param id 订单编号
     * @return 是否成功
     */
    public Order query(long id){


        return  null;
    }


    /**
     * 更新订单信息
     * @param order 订单类
     * @return 是否成功
     */
    public boolean update(Order order){




        return false;
    }

}
