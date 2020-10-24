package com.xiaofang;

import java.io.*;
import java.util.Scanner;

public class ArrayTest2 {
    public static void main(String[] args) {
        int []a=new int[10];
        int max=a[0],min=a[0],maxp=0,minp=0;
        System.out.println("请输入十个数字：");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<a.length;i++){
            a[i]=sc.nextInt();
        }
        for(int i:a){
            System.out.print(i+" ");
        }
        for(int i=0;i<a.length;i++){
            if(a[i]>=max){
                max=a[i];
                maxp=i;
            }
            if (a[i]<=min) {
                min=a[i];
                minp=i;
            }
            }
           max=a[0];
        a[0]=a[maxp];
        a[maxp]=max;
        min=a[a.length-1];
        a[a.length-1]=a[minp];
        a[minp]=min;
        for(int i:a){
            System.out.print(i+" ");
        }

        }
       /* InputStreamReader reader=null;
        BufferedReader br=null;
        try{
            File file=new File("E:\\Test.txt");
            reader=new InputStreamReader(new FileInputStream(file));
            br=new BufferedReader(reader);
            String line;
            line=br.readLine();
            while(line!=null){
            }
        }catch(Exception e){
            System.out.println("程序出错。");
        }
    }*/


}
