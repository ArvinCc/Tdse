package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;

/**
 * lcc 用户
 * Created by dell2 on 2017/5/31.
 */
public class User {

    /**
     * mysql用户id
     */
    private long user_id;
    /**
     *  用户账户名
     */
    private String user_name;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_phone_number='" + user_phone_number + '\'' +
                ", user_signup_time=" + user_signup_time +
                '}';
    }

    public String toJson(){

        return JSON.toJSONString(this);
    }


   public long getId(){
        return  user_id;
   }

    public String getName() {
        return user_name;
    }

    public String getPassword() {
        return user_password;
    }

    public String getPhoneNumber() {
        return user_phone_number;
    }

    public Timestamp getRegisterTime(){

       return  user_signup_time;
    }

    public void setId(long user_id){
           this.user_id=user_id;
    }

    public void setName(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String user_password) {
        this.user_password = user_password;
    }

    public void setPhoneNumber(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public void setRegisterTime(Timestamp user_signup_time){

      this.user_signup_time= user_signup_time;
    }

    /**
     * 用户密码
     */
    private String user_password;
    /**
     * 用户电话
     */
    private String user_phone_number;
    /**
     * 用户注册时间
     */
    private Timestamp user_signup_time;

}
