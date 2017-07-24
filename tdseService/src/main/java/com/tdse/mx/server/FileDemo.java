package com.tdse.mx.server;

import java.io.*;

/**
 * Created by Administrator on 2017/7/19.
 */
public class FileDemo
{

    private final String  path="E:\\Arvin";
    volatile private static FileDemo instance = null;

    private FileDemo(){}

    public static FileDemo getInstance() {
        try {
            if(instance != null){//懒汉式

            }else{
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(100);
                synchronized (FileDemo.class) {
                    if(instance == null){//二次检查
                        instance = new FileDemo();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }


    public void Into(String log)
    {
        File file=new File(path);
        String filepath=path+"\\TdseLog.txt";
        try{
        if (!file.isDirectory()) {
            if (file.mkdir()) {
                JudgeFile(filepath,log);
            }
        }else {
            JudgeFile(filepath,log);
        }
        }catch (Exception e){
            System.out.println("????");
        }
    }

    private void JudgeFile(String filepath,String newstr) throws IOException
    {
        File files =new File(filepath);

        if (!files.exists())
        {
            files.createNewFile();
            writeFileContent(filepath,newstr);
        }else {
            writeFileContent(filepath,newstr);
        }

    }



    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }


}
