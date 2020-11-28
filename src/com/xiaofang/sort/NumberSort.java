package com.xiaofang.sort;

import com.xiaofang.utils.SortUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//生成随机数，并排序。文件输入输出。
public class NumberSort {
    public static void main(String[] args) {
        for (int i = 10000000; i <1000000000; i =i+50000000) {
            System.out.print(i + "：");
            numberSort(i);
        }
    }
    public static void numberSort(int n) {
        Random r = new Random(1);
        boolean flag = false;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int ran1 = r.nextInt(n);
            arr[i] = ran1;
        }

        BufferedWriter bw1= null;
        OutputStreamWriter writer1 = null;
        BufferedWriter bw2= null;
        OutputStreamWriter writer2 = null;
        String str="";
        try {
            File file1 = new File("E:\\Random.txt");
            writer1 = new OutputStreamWriter(new FileOutputStream(file1));
            bw1 = new BufferedWriter(writer1);
            long startTime =System.currentTimeMillis();
            SortUtils.QuickSort(arr,0,n-1);
            long endTime=System.currentTimeMillis();
            long time=endTime-startTime;
            System.out.println("程序运行的时间为："+time+"ms");
            File file2 = new File("E:\\RandomSort.txt");
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
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
 }
