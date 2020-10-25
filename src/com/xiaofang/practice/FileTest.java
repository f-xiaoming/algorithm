package com.xiaofang.practice;

import java.io.*;

public class FileTest {
    public static void main(String[] args) {
        InputStreamReader reader=null;
        BufferedReader br=null;
        BufferedWriter bw=null;
        OutputStreamWriter writer=null;
        try{
            File file=new File("E:\\test.txt");
            reader=new InputStreamReader(new FileInputStream(file));
            br=new BufferedReader(reader);//br字符流
            String line;
            line=br.readLine();
            while(line!=null){
                System.out.println(line);//
                line=br.readLine();
            }
            writer=new OutputStreamWriter(new FileOutputStream(file));
            bw=new BufferedWriter(writer);
            bw.append("你是个猪。");
            bw.newLine();
            bw.append("你是个猪。");
            bw.newLine();
            bw.append("你是个猪。");
            bw.newLine();
            bw.append("你是个猪。");
            bw.newLine();
            bw.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("程序出错。");
        }finally {
            try {
                br.close();
                reader.close();
                bw.close();
                writer.close();
            } catch (IOException e) {
                System.out.println("关闭出错。");
            }
        }
    }
}
