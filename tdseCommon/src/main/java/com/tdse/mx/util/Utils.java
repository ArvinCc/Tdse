package com.tdse.mx.util;

import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell2 on 2017/5/31.
 */
public class Utils {
    /**
     * 取得指定范围随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNum(int min, int max) {
        return RandomUtils.nextInt(min, max+1);
    }

    /**
     * 取得指定范围随机浮点数
     *
     * @param min
     * @param max
     * @return
     */
    public static float getRandomNum(float min, float max) {
        return RandomUtils.nextFloat(min, max);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static int getTimeStamp() {
        return (int)(System.currentTimeMillis() / 1000);
    }

    /**
     *  获取当前时间timestamp用于存储数据库
     * @param
     * @return
     */
    public static Timestamp getCurrentTime()
    {
        try {
            java.util.Date  date=new java.util.Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            String time=formatter.format(date);
            java.util.Date date1 =formatter.parse(time);
            java.sql.Timestamp st = new java.sql.Timestamp(date1.getTime());
            return st;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间增减
     * @param currentTime
     * @param number
     * @return
     */
    public static Timestamp getAddTime(Timestamp currentTime,int number)
    {
        try {
        java.util.Date date=new java.util.Date();
        date =currentTime;
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.MONTH,number);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time=formatter.format(calendar.getTime());
        java.util.Date date1 =formatter.parse(time);
        java.sql.Timestamp st = new java.sql.Timestamp(date1.getTime());

        return st;
        } catch (ParseException e) {
            System.out.println(""+e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  获得32位数的随机数
     * @return
     */
     public static String getUuid()
     {
        return UUID.randomUUID().toString().replace("-", "");
     }



    /**
     * 将字符串转为byte[]
     * @param content
     * @return
     */
     public static byte[] stringBytes(String content){
        return  content.getBytes();
     }


    /**
     *  将int的转换成byte[]
     * @param num
     * @return
     */
    public static byte[] int2Bytes(int num) {
        byte[] byteNum = new byte[4];
        for (int ix = 0; ix < 4; ++ix) {
            int offset = 32 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    /**
     *  将byte[] 转成int
     * @param byteNum
     * @return
     */
    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < 4; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    /**
     * 将long转成byte[]
     * @param num
     * @return
     */
    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    /**
     *  将byte[]转成long
     * @param byteNum
     * @return
     */
    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    /**
     *  byte转正数
     * @param byteNum
     * @return
     */
    public static int oneByte2Int(byte byteNum) {
        //针对正数的int
        return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
    }

    /**
     *  将int转成byte
     * @param num
     * @return
     */
    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);
    }

    /**
     * 封装成信息包
     * @param
     * @param content
     * @return
     */
    public static byte [] msgPkg(String pkgId,byte []  content)
    {
//        byte[] bytesHead ="mxs".getBytes();
//        byte[] bytesId =pkgId.getBytes();
//
//        String str=String.format("%1$-8s", ""+content.length);
//        byte[] bytesLength =str.getBytes();
//        byte[] bytesTail="mxe".getBytes();
//
//        byte[] bytes =new byte[bytesHead.length+bytesId.length+bytesLength.length+content.length+bytesTail.length];
//
//        System.arraycopy(bytesHead,0,bytes,0,bytesHead.length);
//        System.arraycopy(bytesId,0,bytes,bytesHead.length,bytesId.length);
//        System.arraycopy(bytesLength,0,bytes,bytesHead.length+bytesId.length,bytesLength.length);
//        System.arraycopy(content,0,bytes,bytesHead.length+bytesId.length+bytesLength.length,content.length);
//        System.arraycopy(bytesTail,0,bytes,bytesHead.length+bytesId.length+bytesLength.length+content.length,bytesTail.length);

        byte[] bytesId =pkgId.getBytes();

        String str=String.format("%1$-8s", ""+content.length);
        byte[] bytesLength =str.getBytes();
        byte[] bytes =new byte[bytesId.length+bytesLength.length+content.length];

        System.arraycopy(bytesId,0,bytes,0,bytesId.length);
        System.arraycopy(bytesLength,0,bytes,bytesId.length,bytesLength.length);
        System.arraycopy(content,0,bytes,bytesId.length+bytesLength.length,content.length);
        return bytes;
    }

    public static ConcurrentHashMap<String, byte[]> analysisPkg(byte[] msg)
    {

        ConcurrentHashMap<String, byte[]> data =new ConcurrentHashMap<String, byte[]>();

        byte[] pkgId=new byte[6];

        byte[] contentLength =new byte[8];

        System.arraycopy(msg,0,pkgId,0,6);
        System.arraycopy(msg,0,pkgId,0,6);
        return  data;
    }


    /**
     * 随机一个指定位数ID
     * @return
     */
    public static String getRandomId(int length)
    {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        val=val.toLowerCase();
        return val;
    }


    /**
     * 获得随机指定位数,指定个数的list列表,
     * @param length
     * @param num
     * @return
     */
     public static List<String> getRandomIdList(int length,long num){
         List<String> results=new ArrayList<String>();
         for(int j=0;j<num;j++){
             String val = "";
             Random random = new Random();
             for(int i = 0; i < length; i++)
             {
                 String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
                 if("char".equalsIgnoreCase(charOrNum)) // 字符串
                 {
                     int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                     val += (char) (choice + random.nextInt(26));
                 }
                 else if("num".equalsIgnoreCase(charOrNum)) // 数字
                 {
                     val += String.valueOf(random.nextInt(10));
                 }
             }
             val=val.toLowerCase();
             if(results.contains(val)){
                 continue;
             }else{
                 results.add(val);
             }
         }
         return results;
     }


}
