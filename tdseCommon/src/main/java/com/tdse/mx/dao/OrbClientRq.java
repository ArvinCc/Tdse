package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/7/18.
 */
public class OrbClientRq
{

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getApp_order() {
        return app_order;
    }

    public void setApp_order(String app_order) {
        this.app_order = app_order;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    @Override
    public String toString() {
        return "OrbClientRq{" +
                "appid='" + appid + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", unit_price=" + unit_price +
                ", count=" + count +
                ", app_order='" + app_order + '\'' +
                ", callback_url='" + callback_url + '\'' +
                '}';
    }

    public String toJson(){

        return JSON.toJSONString(this);
    }

    /**
     * 应用id
     */
    private String appid;

    /**
     * 商品id
     */
    private String goods_id;

    /**
     * 商品名称
     */
    private String goods_name;

    /**
     * 商品单价
     */
    private int unit_price;

    /**
     * 商品数量
     */
    private int count;

    /**
     * 订单号
     */
    private String app_order;

    /**
     * 回调地址
     */
    private String callback_url;

}
