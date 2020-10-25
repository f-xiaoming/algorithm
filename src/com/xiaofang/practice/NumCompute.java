package com.xiaofang.practice;
//一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
public class NumCompute {
    public static void  main(String []args){
        int n;
        for(int i=0;i<1000;i++){
            n=100+i;
            for(int j=10;j<=Math.sqrt(n);j++){
                if(Math.sqrt(n)==j){ //判断n是否是一个整数的平方
                    n=n+168;
                    for(int k=16;k<=Math.sqrt(n);k++){
                        if(Math.sqrt(n)==k){
                            System.out.println(i);
                        }
                        /*else if(k>Math.sqrt(n)){
                            break;
                        }*/
                    }
                }
               /* else if(j>Math.sqrt(n)){
                    break;
                }*/
            }

        }
    }
}
