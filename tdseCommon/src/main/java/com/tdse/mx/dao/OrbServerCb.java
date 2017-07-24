package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/7/20.
 */
public class OrbServerCb
{
    /**
     * 应用ID
     */
    private int appid;

    /**
     * CNY
     */
    private String fee_type;

    /**
     * 商品ID
     */
    private String goods_id;

    /**
     * 商品单价,单位分
     */
    private int unit_price;

    /**
     * 购买数量
     */
    private int count;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getOrb_order() {
        return orb_order;
    }

    public void setOrb_order(String orb_order) {
        this.orb_order = orb_order;
    }

    public String getApp_order() {
        return app_order;
    }

    public void setApp_order(String app_order) {
        this.app_order = app_order;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OrbServerCb{" +
                "appid=" + appid +
                ", fee_type='" + fee_type + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", unit_price=" + unit_price +
                ", count=" + count +
                ", total_fee=" + total_fee +
                ", orb_order='" + orb_order + '\'' +
                ", app_order='" + app_order + '\'' +
                ", username='" + username + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String toJson(){

        return JSON.toJSONString(this);
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 实际支付金额,单位分
     */
    private int total_fee;

    /**
     * SDK订单号
     */
    private String orb_order;

    /**
     * 游戏服务器订单号
     */
    private String app_order;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 游戏服务器签名
     */
    private String sign;

}
