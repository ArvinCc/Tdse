package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

/**
 * lcc 主题
 * Created by Administrator on 2017/6/9.
 */
public class Theme {

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + theme_id +
                ", name='" + theme_name + '\'' +
                ", money=" + theme_money +
                ", introduce='" + theme_introduce + '\'' +
                '}';
    }
    public String toJson(){

        return JSON.toJSONString(this);
    }

    public String getId() {
        return theme_id;
    }

    public void setId(String id) {
        this.theme_id = id;
    }

    public String getName() {
        return theme_name;
    }

    public void setName(String name) {
        this.theme_name = name;
    }

    public double getMoney() {
        return theme_money;
    }

    public void setMoney(double money) {
        this.theme_money = money;
    }

    public String getIntroduce() {
        return theme_introduce;
    }

    public void setIntroduce(String introduce) {
        this.theme_introduce = introduce;
    }


    /**
     * 主题编号
     */
    private String theme_id;
    /**
     * 主题名字
     */
    private String theme_name;
    /**
     * 主题价格
     */
    private double theme_money;
    /**
     * 主题介绍
     */
    private String  theme_introduce;
}
