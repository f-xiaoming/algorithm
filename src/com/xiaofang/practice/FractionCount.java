package com.xiaofang.practice;

public class FractionCount {
    public static void main(String []args){
        double top=2,bottom=1;
        double count=0;
        for(int i=1;i<=20;i++){
            double fraction=top/bottom;
            double temp;
            count=count+fraction;
            temp=bottom;
            bottom=top;
            top=temp+top;
        }
        System.out.println("分式的总和是"+count);
    }
}
