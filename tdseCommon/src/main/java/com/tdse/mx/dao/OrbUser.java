package com.tdse.mx.dao;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/7/13.
 */
public class OrbUser
{

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public Timestamp getUser_vip_time() {
        return user_vip_time;
    }

    public void setUser_vip_time(Timestamp user_vip_time) {
        this.user_vip_time = user_vip_time;
    }

    public Timestamp getUser_signup_time() {
        return user_signup_time;
    }

    public void setUser_signup_time(Timestamp user_signup_time) {
        this.user_signup_time = user_signup_time;
    }

    @Override
    public String toString() {
        return "OrbUser{" +
                "user_name='" + user_name + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_vip_time=" + user_vip_time +
                ", user_signup_time=" + user_signup_time +
                '}';
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    private long user_id;

    private String user_name;

    private String user_sex;

    private Timestamp user_vip_time;

    private Timestamp user_signup_time;

}
