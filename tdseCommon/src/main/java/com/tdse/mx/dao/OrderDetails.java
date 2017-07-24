package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;

/**
 * lcc 订单详情
 * Created by Administrator on 2017/6/9.
 */
public class OrderDetails
{
    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + details_id +
                ", name='" + details_name + '\'' +
                ", money=" + details_money +
                ", startTime=" + details_start_time +
                ", endTime=" + details_end_time +
                ", orderId=" + details_order_id +
                ", userId=" + details_user_id +
                '}';
    }
    public String toJson(){

        return JSON.toJSONString(this);
    }


    public long getUserId() {
        return details_user_id;
    }

    public void setUserId(long userId) {
        this.details_user_id = userId;
    }
    public long getId() {
        return details_id;
    }

    public void setId(long id) {
        this.details_id = id;
    }

    public String getName() {
        return details_name;
    }

    public void setName(String name) {
        this.details_name = name;
    }

    public double getMoney() {
        return details_money;
    }

    public void setMoney(double money) {
        this.details_money = money;
    }

    public Timestamp getStartTime() {
        return details_start_time;
    }

    public void setStartTime(Timestamp startTime) {
        this.details_start_time = startTime;
    }

    public Timestamp getEndTime() {
        return details_end_time;
    }

    public void setEndTime(Timestamp endTime) {
        this.details_end_time = endTime;
    }

    public long getOrderId() {
        return details_order_id;
    }

    public void setOrderId(long orderId) {
        this.details_order_id = orderId;
    }

    /**
     * 详情id
     */
    private long details_id;
    /**
     * 产品名称
     */
    private String details_name;
    /**
     * 产品价格
     */
    private double details_money;
    /**
     * 使用的开始时间
     */
    private Timestamp details_start_time;
    /**
     * 使用的结束时间
     */
    private Timestamp details_end_time;
    /**
     * 订单id
     */
    private long details_order_id;

    /**
     * 用户id
     */
    private long details_user_id;
}
