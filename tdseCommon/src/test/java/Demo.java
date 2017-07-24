import com.alibaba.fastjson.JSON;
import com.tdse.mx.dao.OrbOrder;
import com.tdse.mx.dao.Product;
import com.tdse.mx.dao.Theme;
import com.tdse.mx.db.OrbOrderImpl;
import com.tdse.mx.db.ProductImpl;
import com.tdse.mx.db.ThemeImpl;
import com.tdse.mx.intf.IOrbbecOrder;
import com.tdse.mx.manager.OrbOrderManager;
import com.tdse.mx.manager.OrbUserManager;
import com.tdse.mx.manager.ProductManager;
import io.netty.util.internal.SystemPropertyUtil;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public class Demo {


    public static void main(String[] args) throws Exception
    {
        //add();
        //find();
       // update();
        //delete();
      //  System.out.println(OrbUserManager.getInstance().getUserVipSurplusTime("{\"downloadNumber\":1,\"username\":\"二狗\",\"state\":\"最热\",\"themeId\":\"1\"}"));
        //System.out.println(OrbUserManager.getInstance().getUserVipTime("{\"downloadNumber\":1,\"username\":\"二狗\",\"state\":\"最热\",\"themeId\":\"1\"}"));

//
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("viptype","A");
//        jsonObject.put("username","ob_wxoZaw0wdHxm8ica1Rgv8pYturB_FA");
//         OrbOrderManager.getInstance().creatOrder(jsonObject.toString());


       //OrbOrderManager.getInstance().dealOrder("{\"count\": 1, \"username\": \"ob_wxoZaw0wdHxm8ica1Rgv8pYturB_FA\", \"orb_order\": \"10000000000000000000000000001443\", \"total_fee\": \"1\", \"goods_id\": \"1\", \"appid\": 20002, \"sign\": \"4468742AA658B01E3A674DF3EFB9369D\", \"app_order\": \"0952cc3fdda74d82a04c8978c1dd311c\", \"unit_price\": 1, \"fee_type\": \"CNY\"}");
        String test =  SystemPropertyUtil.get("user.dir");
        int one = test.lastIndexOf("\\");
        System.out.println(test.replace(test.substring((one+1),test.length()),"DownLoad"));

    }

    public void DelegateOrder()
    {
        List<OrbOrder> o = OrbOrderImpl.getInstance().find();
        for (int i=0;i<o.size();i++)
        {
            OrbOrderImpl.getInstance().deleteById(o.get(i).getOrder_id());
        }
    }


   public static void find()
   {
//       System.out.println(""+ ProductImpl.getInstance().findProductById("阿斯顿"));
//       System.out.println(""+ ProductImpl.getInstance().findProductByName("地铁安全"));
//       System.out.println(""+ ProductImpl.getInstance().findByName("地铁安全"));

       System.out.println(""+ ThemeImpl.getInstance().findById("opop"));
       System.out.println(""+ ThemeImpl.getInstance().findThemeById("opop"));
       System.out.println(""+ ThemeImpl.getInstance().findByName("怕怕主题s"));
       System.out.println(""+ ThemeImpl.getInstance().findThemeByName("怕怕主题s"));
   }


   public static void delete()
   {
       ThemeImpl.getInstance().deleteById("1");
       //ThemeImpl.getInstance().deleteByName("怕怕主题s");
   }

    public static void update()
    {
//        Product product=new Product();
//        product.setId("阿斯顿");
//        product.setDownloadNumber(1);
//        product.setThemeId("1");
//        product.setEdition("1.0.0.0");
//        product.setIntroduce("内容介绍");
//        product.setMoney(123);
//        product.setName("地铁安全ss");
//        product.setState("最热");
//        product.setGrade("没有等级");
//        ProductImpl.getInstance().updateProduct(product);

        Theme theme =new Theme();
        theme.setName("怕怕主题s");
        theme.setMoney(12);
        theme.setIntroduce("不想介绍");
        theme.setId("opop");
        ThemeImpl.getInstance().updateTheme(theme);
    }

   public static void add()
   {
//
//       Theme theme =new Theme();
//       theme.setName("怕怕主题");
//       theme.setMoney(12);
//       theme.setIntroduce("不想介绍");
//       theme.setId("opop");
//       ThemeImpl.getInstance().addTheme(theme);

   }


}
