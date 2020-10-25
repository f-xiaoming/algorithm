package com.xiaofang.practice;

import java.util.Scanner;

public class StringTest1 {
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        String str="aBCDE";
        String str1="a11,b11,c11,d11,e11";
        char []ch=str.toCharArray();
        System.out.println(ch[2]);
        System.out.println(str.codePointAt(3));
        System.out.println(str.codePointBefore(3));
        System.out.println(str.codePointCount(1,4));
        System.out.println(str.compareTo("b"));
        System.out.println(str.compareToIgnoreCase("ABCDE"));
        System.out.println(str.concat("123"));
        System.out.println(str.contains("aBD"));
        System.out.println(str.contentEquals("aBCDE"));
        StringBuffer sb=new StringBuffer("aB");
        sb.append("CDE");
        System.out.println(str.contentEquals(sb));
        System.out.println("***********************************");
        System.out.println(str.hashCode());
        String[] split = str1.split(",", 1);//c+A+v
        for(String s:split){
            System.out.println(s);
        }


    }
}
