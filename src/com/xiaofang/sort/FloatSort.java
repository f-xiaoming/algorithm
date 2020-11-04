package com.xiaofang.sort;

import com.xiaofang.utils.SortUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FloatSort {
    public static void main(String[] args) {
        for (int i = 100000; i < 10000000; i = i+500000) {
            System.out.print(i + "：");
            Test(i);
        }
    }

    public static void Test(int n){
        Random r = new Random(1);
        boolean flag = false;
        //System.out.println("请输入要随机生成的浮点数的个数：");
       // int n=0;
        //Scanner sc=new Scanner(System.in);
       // n=sc.nextInt();
       // System.out.println("请输入浮点数的最大值：");
        //float m=0;
        //m=sc.nextFloat();
        float[] arr = new float[n];
        for (int i = 0; i < n; i++) {
            float ran1 = r.nextFloat();
            ran1=ran1*n;
            arr[i] = ran1;
            //System.out.print(ran1+" ");
        }//随机生成n个浮点数

        BufferedWriter bw1= null;
        OutputStreamWriter writer1 = null;

        BufferedWriter bw2= null;
        OutputStreamWriter writer2 = null;
        String str="";
        try {
            File file1 = new File("E:\\Random.txt");
            writer1 = new OutputStreamWriter(new FileOutputStream(file1));
            bw1 = new BufferedWriter(writer1);
            for (int i = 0; i < n; i++) {
                str=String.valueOf(arr[i]);
                bw1.append(str);
                bw1.append("   ");
            }//把随机生成的随机浮点数放在E:\Random.txt中

            long startTime =System.currentTimeMillis();
            //numberSort(arr);
            SortUtils.QuickSort1(arr,0,n-1);
            //Arrays.sort(arr);
            long endTime=System.currentTimeMillis();
            long time=endTime-startTime;
            System.out.println("程序运行的时间为："+time+"ms");//对随机生成的浮点数
            // 进行排序并记录排序时间

            File file2 = new File("E:\\RandomSort.txt");
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
            for (int i = 0; i < n; i++) {
                str=String.valueOf(arr[i]);
                bw2.append(str);
                bw2.append(" ");
            }//把排好序的浮点数输入到E:\RandomSort.txt中

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("程序出错。");

        } finally {
            try {
                bw1.close();
                writer1.close();
                bw2.close();
                writer2.close();
            } catch (IOException e) {
                System.out.println("关闭出错。");
            }

        }
    }
    //冒泡排序
    public static int[] numberSort(int []arr){
        boolean flag=false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (flag == false) {
                        break;
            }
        }
        return arr;
    }

}


