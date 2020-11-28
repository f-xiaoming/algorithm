package com.xiaofang.utils;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.print("123");
        System.out.print((char)0x00);
        System.out.print("123");

        char [][]str={{'a','b','c'},{'d','e','f'}};
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<str.length;i++){
            for (int j= 0;j<str[i].length;j++){
                sb.append(str[i][j]);
            }
        }
        System.out.println(sb.toString());

    }
    public static int doubleX(int  x) {
        return ((x<<1)&0xff^(((x&0x80)==128)?0x1b:0x00));
    }
    public static int multiply(int a,int b){
        int []temp=new int[8];
        temp[0]=a;
        int tempmultiply=0x00;
        for(int i=1;i<8;i++){
            temp[i]=doubleX(temp[i-1]);
        }
        tempmultiply=(b&0x01)*a;
        for(int i=1;i<=7;i++){
            tempmultiply^=(((b>>i)&0x01)*temp[i]);
        }
        return tempmultiply;
    }
}
