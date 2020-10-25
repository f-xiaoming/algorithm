package com.xiaofang.practice;

import java.util.Scanner;

//输入三个整数x,y,z，请把这三个数由小到大输出。
public class Sort {
    public static void main(String []args){
        int x,y,z,temp;
        System.out.println("请输入三个整数：");
        Scanner sc=new Scanner(System.in);
        x=sc.nextInt();
        y=sc.nextInt();
        z=sc.nextInt();
        if(x>y){
               temp=x;
               x=y;
               y=temp;
           }
        if(y>z){
            temp=y;
            y=z;
            z=temp;
        }
        if(x>y){
            temp=x;
            x=y;
            y=temp;
        }
        System.out.println("按从小到大的顺序输出：");
        System.out.println(x+" "+y+" "+z);

    }
}
