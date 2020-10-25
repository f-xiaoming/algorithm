package com.xiaofang.practice;

import java.util.Scanner;

//有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数
public class ArrayTest3 {
    public static void main(String[] args) {
       int m,n;
        System.out.println("请输入数组的长度n和后移的位数");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        array(n,m);
    }
    public static void array(int n,int m){//怎么返回数组类型
        int []arr=new int[n];
        int []b=new int[m];
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入"+n+"个整数：");
        for(int i=0;i<arr.length;i++){
            arr[i]=sc.nextInt();
        }
        for(int i=arr.length-m,j=0;i<arr.length;i++,j++){
            b[j]=arr[i];
        }
        for(int i=arr.length-1;i>=m;i--){
            arr[i]=arr[i-3];
        }
        for(int i=0;i<m;i++){
            arr[i]=b[i];
        }
        System.out.println("数组后移"+m+"位后的数组为：");
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}

