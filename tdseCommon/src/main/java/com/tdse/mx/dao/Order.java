package com.tdse.mx.dao;


import com.alibaba.fastjson.JSON;

/**
 * lcc 订单
 * Created by dell2 on 2017/5/31.
 *
 */
public class Order
{
   @Override
   public String toString() {
      return "Order{" +
              "order_id=" + order_id +
              ", order_establish_time='" + order_establish_time + '\'' +
              ", order_user_id=" + order_user_id +
              '}';
   }

   public String toJson(){

      return JSON.toJSONString(this);
   }


   public long getId(){
      return order_id;
   }

   public String getUserId(){
      return order_establish_time;
   }

   public long getDetermineTime(){
      return order_user_id;
   }

   public void setId(long id){
      this.order_id =id;
   }

   public void setDetermineTime(String determineTime){
      this.order_establish_time= determineTime;
   }

   public void setUserId(long userId){
      this.order_user_id=userId;
   }
   /**
    *订单编号
    */
   private long order_id;
   /**
    *订单成立时间
    */
   private String order_establish_time;
   /**
    *下单用户id
    */
   private long order_user_id;
}
