package com.xiaofang.practice;

import java.util.Scanner;

//取一个整数a从右端开始的4-7位。
public class StringTest3 {
    public static void main(String[] args) {
       String str;
        System.out.println("请输入一个整数");
        Scanner sc=new Scanner(System.in);
        str=sc.nextLine();
       int  n=str.length();
       char []arr=str.toCharArray();
        if(n<7){
            System.out.println("输入的整数位数小于七");
        }
        else{
            System.out.println("从右开始的第四位到第七位"+arr[n-4]+arr[n-5]+arr[n-6]+arr[n-7]);
        }
    }
}
