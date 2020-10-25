package com.xiaofang.utils;

import java.io.*;
import java.util.List;

public class FileUtils {


    /**
     * 读取文件信息
     * @param filePath 文件路径
     * @return List
     */
    public List<String> readFile (String filePath){
        InputStreamReader reader=null;
        BufferedReader br=null;
        try{
            File file=new File(filePath);
            reader = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(reader);
            String line;
            line=br.readLine();
            while(line!=null){
                System.out.println(line);
                line=br.readLine();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("程序出错。");
        }finally {
               try{
                   br.close();
                   reader.close();
               }catch(Exception e){
                   System.out.println(e.getMessage());
                   System.out.println("程序出错。");
               }
        }
        return null;
    }


    /**
     * 写入文件信息
     * @param contents 写入的内容列表
     * @param filePath 文件路径
     * @return 成功/失败
     */
    public String writeFile (List<String> contents, String filePath){
        OutputStreamWriter writer=null;
        BufferedWriter bw=null;
        try{
            File file=new File(filePath);
            writer = new OutputStreamWriter(new FileOutputStream(file));
            bw = new BufferedWriter(writer);
           for(int i=0;i<contents.size();i++){
               bw.append(contents.get(i));
               bw.newLine();
           }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("程序出错。");
        }finally {
            try{
                bw.close();
                writer.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("程序出错。");
            }
        }
        return null;
    }

}
