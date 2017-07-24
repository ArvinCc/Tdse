package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/7/17.
 */
public class OrbVip
{
    public String getVip_type() {
        return vip_type;
    }

    public void setVip_type(String vip_type) {
        this.vip_type = vip_type;
    }

    public int getVip_time() {
        return vip_time;
    }

    public void setVip_time(int vip_time) {
        this.vip_time = vip_time;
    }

    public int getVip_price() {
        return vip_price;
    }

    public void setVip_price(int vip_price) {
        this.vip_price = vip_price;
    }

    public int getVip_id() {
        return vip_id;
    }

    public void setVip_id(int vip_id) {
        this.vip_id = vip_id;
    }


    @Override
    public String toString() {
        return "OrbVip{" +
                "vip_id=" + vip_id +
                ", vip_type='" + vip_type + '\'' +
                ", vip_time=" + vip_time +
                ", vip_price=" + vip_price +
                '}';
    }

    public String toJson(){

        return JSON.toJSONString(this);
    }

    /**
     * 套餐id
     */
    private int vip_id;

    /**
     * vip类型
     */
    private String vip_type;

    /**
     * vip时间
     */
    private int vip_time;

    /**
     * vip价格
     */
    private int vip_price;

}
