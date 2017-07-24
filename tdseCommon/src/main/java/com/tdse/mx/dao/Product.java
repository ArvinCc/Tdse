package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

/**
 * lcc 产品
 * Created by dell2 on 2017/5/31.
 */
public class Product
{

    public String toJson(){

        return JSON.toJSONString(this);
    }

    public String getId() {
        return product_id;
    }

    public void setId(String id) {
        this.product_id = id;
    }


    public String getName() {
        return product_name;
    }

    public void setName(String name) {
        this.product_name = name;
    }

    public double getMoney() {
        return product_money;
    }

    public void setMoney(double money) {
        this.product_money = money;
    }

    public String getIntroduce() {
        return product_introduce;
    }

    public void setIntroduce(String introduce) {
        this.product_introduce = introduce;
    }

    public String getGrade() {
        return product_grade;
    }

    public void setGrade(String grade) {
        this.product_grade = grade;
    }

    public long getDownloadNumber() {
        return product_download_number;
    }

    public void setDownloadNumber(long downloadNumber) {
        this.product_download_number = downloadNumber;
    }

    public String getEdition() {
        return product_edition;
    }

    public void setEdition(String edition) {
        this.product_edition = edition;
    }

    public String getState() {
        return product_state;
    }

    public void setState(String state) {
        this.product_state = state;
    }

    /**
     *产品编号
     */
    private String product_id;

    public String getProduct_theme_name() {
        return product_theme_name;
    }

    public void setProduct_theme_name(String product_theme_name) {
        this.product_theme_name = product_theme_name;
    }

    public String getProduct_pkg_name() {
        return product_pkg_name;
    }

    public void setProduct_pkg_name(String product_pkg_name) {
        this.product_pkg_name = product_pkg_name;
    }
    /**
     *主题编号
     */
    private String product_theme_name;
    /**
     *产品名称
     */
    private String product_name;
    /**
     *产品价格
     */
    private double product_money;
    /**
     *产品介绍
     */
    private String product_introduce;
    /**
     *产品等级
     */
    private String product_grade;
    /**
     *产品下载次数
     */
    private long product_download_number;
    /**
     *产品版本
     */
    private String product_edition;
    /**
     *产品状态
     */
    private String product_state;
    /**
     * 产品包名
     */
    private String product_pkg_name;

}
