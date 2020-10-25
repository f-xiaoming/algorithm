package com.xiaofang.sort;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//生成随机数，并排序。文件输入输出。
public class NumberSort {
    public static void main(String[] args) {
        Random r = new Random(1);
        boolean flag = false;
        System.out.println("请输入要随机生成的数字的个数：");
        int n=0;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int ran1 = r.nextInt(n);
            arr[i] = ran1;
            //System.out.print(ran1+" ");
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
            for (int i = 0; i < n; i++) {
                str=String.valueOf(arr[i]);
                bw1.append(str);
                bw1.append(" ");
            }
            long startTime =System.currentTimeMillis();
            //numberSort(arr);
            //SortUtils.QuickSort(arr,0,n-1);
            Arrays.sort(arr);
            long endTime=System.currentTimeMillis();
            long time=endTime-startTime;
            System.out.println("程序运行的时间为："+time+"ms");
            File file2 = new File("E:\\RandomSort.txt");
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
            for (int i = 0; i < n; i++) {
                str=String.valueOf(arr[i]);
                bw2.append(str);
                bw2.append(" ");
            }

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
