package com.tdse.mx.manager;

import com.tdse.mx.dao.User;
import com.tdse.mx.db.UserImpl;
import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.Utils;
import org.json.JSONObject;

/** 用户管理
 * Created by dell2 on 2017/5/31.
 */
public class UserManager {

    //使用volatile关键字保其可见性
    volatile private static UserManager instance = null;

    private UserManager(){}

    public static UserManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (UserManager.class) {
                    if(instance == null){//二次检查
                        instance = new UserManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     *  添加的新的用户
     * @param msg 信息
     * @return 是否成功
     */
   public JSONObject signUp(String msg)
   {
       JSONObject ms = JsonUtils.getValue(msg);
       User user=new User();
       user.setName(ms.getString("name"));
       user.setPassword(ms.getString("password"));
       user.setPhoneNumber(ms.getString("phoneNumber"));

       if (UserImpl.getInstance().finByName(user.getName())){
           return  JsonUtils.setResultData("false","注册失败!账户已存在!");
       }

       if (UserImpl.getInstance().findByPhone(Long.valueOf(user.getPhoneNumber()).longValue()))
       {
           return  JsonUtils.setResultData("false","注册失败!电话已经被注册");
       }

       user.setRegisterTime(Utils.getCurrentTime());

       UserImpl.getInstance().addUser(user);
       return  JsonUtils.setResultData("true","恭喜"+user.getName()+"注册成功!");
   }

   public JSONObject signIn(String msg)
   {
       JSONObject ms = JsonUtils.getValue(msg);

       User user=UserImpl.getInstance().finUserByName(ms.getString("name"));
       if(user==null)
       {
           user=UserImpl.getInstance().findUserByPhone(Long.valueOf(ms.getString("phoneNumber")).longValue());
           if (user==null){
               return JsonUtils.setResultData("false","登陆失败!账号用户名错误!");
           }
       }

       JSONObject jsonObject =new JSONObject();
       jsonObject.put("name",user.getName());
       jsonObject.put("password",user.getPassword());
       jsonObject.put("phoneNumber",user.getPhoneNumber());
       jsonObject.put("signUpTime",user.getRegisterTime());

       return JsonUtils.setResultData("true",jsonObject);
   }



}
