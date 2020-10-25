package com.xiaofang.practice;

import java.util.Scanner;

//有n个人围成一圈，顺序排号。
// 从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
public class ArrayTest4 {
    public static void main(String[] args) {
        int n,count=0,temp=0;
        System.out.println("请输入有几个人：");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int []arr=new int[n];
        for(int i=1;i<=n;i++){
            arr[i-1]=i;
        }
       for(int j=0;;j=(j+1)%n){
            if (arr[j]!=0){
                temp++;
            }
            if (temp==3){
                arr[j]=0;
                temp=0;
                count++;
            }
            if (count==n-1){
                break;
            }
       }
       for (int i=0;i<arr.length;i++){
           if(arr[i]!=0){
           System.out.println("留下的是原来的第"+arr[i]+"wei.");}
       }
    }
}
