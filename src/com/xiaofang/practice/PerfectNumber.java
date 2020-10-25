package com.xiaofang.practice;
//一个数如果恰好等于它的因子之和，这个数就称为"完数"。找出1000以内的所有完数。
public class PerfectNumber {
    public static void main(String []args){

        for(int i=1;i<=100;i++){
             int factor=0;
             for(int j=1;j<i;j++){
                 if(i%j==0){
                     factor+=j;
                 }
             }
             if(factor==i) {
                 System.out.println(i);
             }
        }
    }

}
