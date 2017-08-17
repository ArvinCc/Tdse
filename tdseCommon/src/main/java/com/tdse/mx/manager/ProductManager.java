package com.tdse.mx.manager;

import com.tdse.mx.dao.Product;
import com.tdse.mx.db.ProductImpl;
import com.tdse.mx.util.JsonUtils;
import io.netty.util.internal.SystemPropertyUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/** lcc 产品管理类
 * Created by dell2 on 2017/5/31.
 */
public class ProductManager
{
     private String productWarehousePath;


    //使用volatile关键字保其可见性
    volatile private static ProductManager instance = null;

    private ProductManager()
    {
        productWarehousePath= SystemPropertyUtil.get("user.dir")+"/DownLoad";
    }

    public static ProductManager getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (ProductManager.class) {
                    if(instance == null){//二次检查
                        instance = new ProductManager();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }
    /**
     *   添加一个新产品
     * @param product 产品类
     * @return 是否成功添加
     */
    public boolean add(Product product)
    {
        return  false;
    }

    /**
     *  根据产品编号删除产品所有信息
     * @param id 产品编号
     * @return 是否成功
     */
    public boolean removeId(String id)
    {
        return  false;
    }

    /**
     *  根据产品名字删除产品所有信息
     * @param name 产品名字
     * @return 是否成功
     */
    public boolean removeName(String name){

        return  false;
    }
    /**
     *  查询产品所有信息
     * @param
     * @return
     */
    public List<Product> query()
    {
        return  ProductImpl.getInstance().find();
    }

    /**
     *  根据产品编号查询产品所有信息
     * @param id 产品编号
     * @return
     */
    public Product queryId(String id)
    {
        return  null;
    }

    /**
     *  根据产品名字查询产品所有信息
     * @param name 产品名字
     * @return
     */
    public Product queryName(String name)
    {
        Product product =new Product();
        product.setName(name);

        return  ProductImpl.getInstance().find(product).get(0);
    }

    /**
     *  更新产品信息
     * @param product 产品类
     * @return 是否成功
     */
    public boolean update(Product product)
    {

        return  false;
    }

    public JSONObject getUrl(String name){
        try {
             File demo =new File(productWarehousePath);
            if (!demo.isDirectory())
            {
                if (demo.mkdir()){
                    return JsonUtils.setResultData("false","没有这个路径!路径新建成功!");
                }else {
                    return JsonUtils.setResultData("false","没有这个路径!路径新建失败!");
                }
            }else {
                if(name.indexOf("\\")!=-1)
                {
                    return resultUil(name, "\\");
                }else if (name.indexOf("\\\\")!=-1)
                {
                    return resultUil(name, "\\\\");
                }
                else if (name.indexOf("/")!=-1)
                {
                    return resultUil(name, "/");
                }else {
                    File[] temlist =demo.listFiles();

                    for (int i=0;i<temlist.length;i++)
                    {
                        if (temlist[i].getName().trim().equals(name))
                        {
                            return JsonUtils.setResultData("true",temlist[i].getAbsolutePath());
                        }
                    }
                }
            }
        }catch (Exception e){
            return JsonUtils.setResultData("false","很遗憾获取失败了,错误是："+e);
        }
        return JsonUtils.setResultData("true","未知");
    }


    private JSONObject resultUil(String target,String sign)
    {
        String[] p = target.split(sign);
        StringBuffer buffer = new StringBuffer();
        buffer.append(productWarehousePath);
        for (int i = 0; i < p.length - 1; i++)
        {
                buffer.append("/" +p[i] );
        }
        File newFile = new File(buffer.toString());

        File[] temlist = newFile.listFiles();

        for (int i = 0; i < temlist.length; i++) {

            if (temlist[i].getName().trim().equals(p[p.length - 1])) {
                return JsonUtils.setResultData("true", temlist[i].getAbsolutePath());
            }
        }

        return  null;
    }

    //byte[] bytes = new byte[8192];
    //byte[] bytes = new byte[1024];

    public byte[] getByte(String path,int number) throws IOException {

        File file = new File(path);
        RandomAccessFile r=null;
        byte[] bytes=null;
                try {
                    r =new RandomAccessFile(file,"r");
                    r.skipBytes(number);
                    long i=file.length()-number;
                    System.out.println("number"+number+"||file.length()"+file.length()+"||i"+i);
                    if (i>=8192){
                        bytes = new byte[8192];
                    }else {
                        bytes = new byte[Integer.parseInt(""+i)];
                    }
                    r.read(bytes);
                    return  bytes;
                }catch (Exception e){
                    e.printStackTrace();
                }finally
                {
                   r.close();
                }
                return  null;
    }


    public JSONObject getFileDataByte(String path)
    {
        File file = new File(path);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("name",file.getName());
        jsonObject.put("id","oooppp");
        jsonObject.put("size",file.length());
        return  jsonObject;
    }
}
