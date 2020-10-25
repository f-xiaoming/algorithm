package com.xiaofang.practice;

public class MonkeyEatPeach {
    public static void main(String []args){
        int n=1;
        for(int i=9;i>=1;i--){
            n=(n+1)*2;
        }

        System.out.println("小猴子共摘了"+n+"个桃子。");
    }
}
