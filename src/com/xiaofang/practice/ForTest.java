package com.xiaofang.practice;

import java.util.Scanner;

//编写一个函数，输入n为偶数时，
// 调用函数求1/2+1/4+...+1/n,当输入n为奇数时，调用函数1/1+1/3+...+1/n
public class ForTest {
    public static void main(String[] args) {
        System.out.println("请输入n的值：");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        double count=0;
        if(n%2==0){
            for(double i=2;i<=n;i=i+2){
                count=count+1/i;
            }
        }
        else{
            for(double i=1;i<=n;i=i+2){
                count=count+1/i;
            }
        }
        System.out.println(count);
    }
}
