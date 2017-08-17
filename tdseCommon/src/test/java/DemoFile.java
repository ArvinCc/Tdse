import com.tdse.mx.util.JsonUtils;
import com.tdse.mx.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.json.JSONObject;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/28.
 */
public class DemoFile
{

    public static void main(String[] args)throws  Exception{

        //System.out.println("测试"+ bytes2Int(int2Bytes(1234)));

//        FileInputStream fis = null;
//        FileChannel channel = null;
//        File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
//        fis = new FileInputStream(file);
//
//        System.out.println("文件名字"+file.getName());
//        System.out.println("文件长度"+file.length());
//        byte[] bytes = new byte[1024];
//
//
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        File file1 = null;
//        String filePath = "E:\\";
//        String fileName =null;
//        try {
//         int i=0;
//            while (fis.read(bytes) != -1)
//            {
//                 if (fileName==null)
//                 {
//                     fileName="测试游戏.unitypackage";
//
//                     File dir = new File(filePath);
//                     if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
//                         dir.mkdirs();
//                     }
//                     file1 = new File(filePath+"\\"+fileName);
//                     fos = new FileOutputStream(file1);
//                     bos = new BufferedOutputStream(fos);
//                     bos.write(bytes);
//                 }else {
//                     bos.write(bytes);
//                 }
//                 head(bytes);
//                 //mm(head(bytes));
//
//                //i+=bytes.length;
//                //System.out.println("长度"+i);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            fis.close();
//        }

        //CopyFile();


//        File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
//        System.out.println("大小"+file.length());



    }

    static int number =0;

public static void CopyFile(){
    File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
    RandomAccessFile r = null;
    long pp=file.length();

    BufferedOutputStream bos = null;
    FileOutputStream fos = null;
    File dir=null;
    try {
            while (number<pp)
            {
                r = new RandomAccessFile(file,"rw");
                r.skipBytes(number);
                byte[] bytes = new byte[1024];
                r.read(bytes);
                long i =file.length();
                number+=bytes.length;
                dir = new File("E:\\测试游戏.unitypackage");
                if(!dir.exists()){//判断文件目录是否存在
                    dir.createNewFile();
                    fos = new FileOutputStream(dir);
                    bos = new BufferedOutputStream(fos);
                    bos.write(bytes);
                }else {
                    bos.write(bytes);
                }
            }
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (bos != null) {
            try {
                bos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}


     public static byte[] head(byte[] data)
     {
         String name ="测试游戏.unitypackage";
         int dataLength =data.length;

         byte [] bytes= new byte[0];
         try {
             bytes = name.getBytes("UTF-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         byte [] bytes1=Utils.int2Bytes(dataLength);

         System.out.println("名字长度:"+bytes.length+"||数据长度:"+bytes1.length+"||数据真正的长度:"+dataLength);

         byte[] bytes2 =new byte[bytes.length+bytes1.length+data.length];
         System.arraycopy(bytes,0,bytes2,0,bytes.length);
         System.arraycopy(bytes1,0,bytes2,25,bytes1.length);
         System.arraycopy(data,0,bytes2,29,data.length);

         return  bytes2;
     }


     public static void mm(byte[] data)
     {
         byte[] namebytes =new byte[25];
         byte[] lengthbytes =new byte[4];

         System.arraycopy(data,0,namebytes,0,25);
         System.arraycopy(data,25,lengthbytes,0,4);

         String name = null;
         try {
             name = new String(namebytes,"UTF-8");
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         System.out.println("数据长度||"+data.length);
         System.out.println("名字||"+name);
         System.out.println("长度||"+ Utils.bytes2Int(lengthbytes));
         byte[] d=new byte[data.length-lengthbytes.length-namebytes.length];
         System.arraycopy(data,29,d,0,d.length);
         System.out.println("数据||"+d);
     }

    public static void CreatFile(byte[] bfile, String filePath,String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }


    //拆分文件
    public static void cut() {
        File file = new File("E:\\Arvin\\JAVA_PROJECT\\ProductWarehouse\\八大主题\\测试安全\\测试游戏.unitypackage");
        int num = 10;//分割文件的数量

        long lon = file.length() / 10L + 1L;//使文件字节数+1，保证取到所有的字节
        try {
            RandomAccessFile raf1 = new RandomAccessFile(file, "r");

            byte[] bytes = new byte[1024];//值设置越小，则各个文件的字节数越接近平均值，但效率会降低，这里折中，取1024
            int len = -1;
            for (int i = 0; i < 10; i++) {
                String name = "E:\\测试游戏" + i + ".mx";
                File file2 = new File(name);
                RandomAccessFile raf2 = new RandomAccessFile(file2, "rw");

                while ((len = raf1.read(bytes)) != -1){//读到文件末尾时，len返回-1，结束循环
                    raf2.write(bytes, 0, len);
                    if (raf2.length() > lon)//当生成的新文件字节数大于lon时，结束循环
                        break;
                }
                raf2.close();
            }
            raf1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //合并文件
    public static void merge() {
        File file = new File("E:\\Arvin\\JAVA_PROJECT\\测试游戏.unitypackage");
        try {
            RandomAccessFile target = new RandomAccessFile(file, "rw");
            for (int i = 0; i < 10; i++) {
                File file2 = new File( "E:\\测试游戏" + i + ".mx");
                RandomAccessFile src = new RandomAccessFile(file2, "r");
                byte[] bytes = new byte[1024];//每次读取字节数
                int len = -1;
                while ((len = src.read(bytes)) != -1) {
                    target.write(bytes, 0, len);//循环赋值
                }
                src.close();
            }
            target.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
