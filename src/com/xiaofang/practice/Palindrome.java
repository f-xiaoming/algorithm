package com.xiaofang.practice;
import java.util.Scanner;
//判断一个5位数是否是一个回文数字。
public class Palindrome {
    public static void main(String[] args) {
        String str=null;
        System.out.println("请输入一个5位数：");
        Scanner sc=new Scanner(System.in);
        str=sc.nextLine();
        if(str.charAt(0)!=str.charAt(4)||str.charAt(1)!=str.charAt(3)){
            System.out.println("这个5位数不是个回文数。");
        }
        else{
            System.out.println("这个5位数是个回文数。");
        }
    }
}
