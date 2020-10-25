package com.xiaofang.practice;
//求1+2!+3!+...+20!的和
public class FactorialCount {
    public static void main(String []args){
        int count=0;
        for(int i=1;i<=4;i++){
            int fac=1;
            for(int j=1;j<=i;j++){
                fac=fac*j;
            }
            count=count+fac;
        }
        System.out.println("该式子的和为"+count);
    }
}
