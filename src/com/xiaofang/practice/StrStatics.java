package com.xiaofang.practice;

import java.util.Scanner;

//输入一行字符，统计英文字母、空格、数字、其他字符的个数
public class StrStatics {
    public static void main(String[] args) {
        String str;
        char[] ch;
        int chNum = 0;
        int num = 0;
        int spaceNum = 0;
        int otherNum = 0;
        System.out.println("请输入一行字符：");
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int temp = (int) ch[i];
            if ((temp >= 65 && temp <= 90) || (temp >= 97 && temp <= 122)) {
                chNum++;
            } else if (temp >= 48 && temp <= 57) {
                num++;
            } else if (temp == 32) {
                spaceNum++;
            } else {
               otherNum++;
            }
        }
        System.out.println("字符个数为："+chNum);
        System.out.println("数字个数为："+num);
        System.out.println("空格个数为："+spaceNum);
        System.out.println("其他个数为："+otherNum);
    }
}
