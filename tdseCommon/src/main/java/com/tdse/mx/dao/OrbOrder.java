package com.tdse.mx.dao;

import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/7/13.
 */
public class OrbOrder
{

   public String getOrder_id() {
      return order_id;
   }

   public void setOrder_id(String order_id) {
      this.order_id = order_id;
   }

   public String getOrder_user_name() {
      return order_user_name;
   }

   public void setOrder_user_name(String order_user_name) {
      this.order_user_name = order_user_name;
   }

   public Timestamp getOrder_establish_time() {
      return order_establish_time;
   }

   public void setOrder_establish_time(Timestamp order_establish_time) {
      this.order_establish_time = order_establish_time;
   }

   public Timestamp getOrder_deal_time() {
      return order_deal_time;
   }

   public void setOrder_deal_time(Timestamp order_deal_time) {
      this.order_deal_time = order_deal_time;
   }

   public int getOrder_appid() {
      return order_appid;
   }

   public void setOrder_appid(int order_appid) {
      this.order_appid = order_appid;
   }

   public String getOrder_goods_id() {
      return order_goods_id;
   }

   public void setOrder_goods_id(String order_goods_id) {
      this.order_goods_id = order_goods_id;
   }

   public String getOrder_fee_type() {
      return order_fee_type;
   }

   public void setOrder_fee_type(String order_fee_type) {
      this.order_fee_type = order_fee_type;
   }

   public int getOrder_uint_price() {
      return order_uint_price;
   }

   public void setOrder_uint_price(int order_uint_price) {
      this.order_uint_price = order_uint_price;
   }

   public int getOrder_count() {
      return order_count;
   }

   public void setOrder_count(int order_count) {
      this.order_count = order_count;
   }

   public int getOrder_total_fee() {
      return order_total_fee;
   }

   public void setOrder_total_fee(int order_total_fee) {
      this.order_total_fee = order_total_fee;
   }

   public String getOrder_orb_order() {
      return order_orb_order;
   }

   public void setOrder_orb_order(String order_orb_order) {
      this.order_orb_order = order_orb_order;
   }

   public String getOrder_sign() {
      return order_sign;
   }

   public void setOrder_sign(String order_sign) {
      this.order_sign = order_sign;
   }


   public String toJson(){

      return JSON.toJSONString(this);
   }
   /**
    * 订单编号
    */
   private String order_id;
   /**
    * 下单用户名
    */
   private String order_user_name;

   public String getOrder_payment_user_name() {
      return order_payment_user_name;
   }

   @Override
   public String toString() {
      return "OrbOrder{" +
              "order_id='" + order_id + '\'' +
              ", order_user_name='" + order_user_name + '\'' +
              ", order_payment_user_name='" + order_payment_user_name + '\'' +
              ", order_establish_time=" + order_establish_time +
              ", order_deal_time=" + order_deal_time +
              ", order_appid=" + order_appid +
              ", order_goods_id=" + order_goods_id +
              ", order_fee_type='" + order_fee_type + '\'' +
              ", order_uint_price=" + order_uint_price +
              ", order_count=" + order_count +
              ", order_total_fee=" + order_total_fee +
              ", order_orb_order='" + order_orb_order + '\'' +
              ", order_sign='" + order_sign + '\'' +
              '}';
   }

   public void setOrder_payment_user_name(String order_payment_user_name) {
      this.order_payment_user_name = order_payment_user_name;
   }

   /**
    * 付款用户名
    */
   private String order_payment_user_name;
   /**
    * 下单时间
    */
   private Timestamp order_establish_time;
   /**
    * 成交时间
    */
   private Timestamp order_deal_time;
   /**
    * 购买的应用id
    */
   private int order_appid;

   /**
    * 商品id
    */
   private String order_goods_id;
   /**
    * 未知CNY
    */
   private String order_fee_type;

   /**
    * 商品单价,单位分
    */
   private int order_uint_price;
   /**
    * 购买的数量
    */
   private int order_count;
   /**
    * 实际支付金额,单位分
    */
   private int order_total_fee;
   /**
    * SDK订单号
    */
   private String order_orb_order;
   /**
    * 订单从成立的时候服务器签名
    */
   private String order_sign;



}
