package com.xiaofang;

import java.util.Scanner;

public class Grade {
    public static void main(String []args){
        int n;
        System.out.println("请输入成绩n的值");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        switch(n/10){
            case 10:
            case 9:
                System.out.println(n+"分为A成绩");
                break;
            case 8:
            case 7:
            case 6:
                System.out.println(n+"分为B成绩");
                break;
            default :
                System.out.println(n+"分为C成绩");
                break;
        }
    }
}
