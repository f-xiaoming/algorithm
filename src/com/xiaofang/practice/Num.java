package com.xiaofang.practice;

import java.util.Scanner;

//求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。
public class Num {
    public static void main(String []args){
        int a,n,sum1=0,sum2=0;
        System.out.println("请输入a和n的值：");
        Scanner sc=new Scanner(System.in);
        a=sc.nextInt();
        n=sc.nextInt();
        for(int i=1;i<n;i++){
           int temp=a;
            for(int j=0;j<i;j++){
                  temp*=10;
                }
                sum1+=temp;
            sum2=sum2+sum1;
        }
        sum2=sum2+a*n;
        System.out.println(sum2);
       /* String str = "";
        int count = 0;
        for (int i = 0; i < n; i++){
            str+=a;
            count += Integer.valueOf(str);
        }
        System.out.println(count);
        for(int i=)
        */
    }
}
