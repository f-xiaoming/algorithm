package com.xiaofang;

import java.util.Scanner;

public class YangHui2 {
    public static void main(String[] args) {
        int n;
        System.out.println("请输入要打印几行杨辉三角：");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        yangHui(n);
    }
    public static void yangHui(int n){
       int [][] a=new int[n][n];
       for(int i=0;i<a.length;i++){
           int countSpace=(n-1-i)*2;
           for(int j=0;j<countSpace;j++){
               System.out.print(" ");
           }
           for(int j=0;j<=i;j++){
               if(j==0||j==i){
                   a[i][j]=1;
               }
               else{
                   a[i][j]=a[i-1][j-1]+a[i-1][j];
               }
               System.out.print(a[i][j]);
               System.out.print("   ");
           }
           System.out.println();
       }
    }
}
