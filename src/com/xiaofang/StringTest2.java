package com.xiaofang;

import java.util.Scanner;

//给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
public class StringTest2 {
    public static void main(String []args){
        String str;
        Scanner sc=new Scanner(System.in);
        str=sc.nextLine();
        System.out.println(str.length());
        for(int i=str.length()-1;i>=0;i--){
            System.out.print(str.charAt(i));
        }
    }
}
