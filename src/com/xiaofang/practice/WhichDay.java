package com.xiaofang.practice;

import java.util.Scanner;

//请输入星期几的第一个字母来
// 判断一下是星期几，(monday Tuesday Wednesday Thursday Friday Saturday Sunday)
// 如果第一个字母一样，则继续判断第二个字母。
public class WhichDay {
    public static void main(String[] args) {
        String ch;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个字母（是某一个星期的开头字母）：");
            ch=sc.nextLine();
            switch(ch){
                case "m":
                    System.out.println("是星期一");
                    break;
                case "w":
                    System.out.println("是星期三");
                    break;
                case "f":
                    System.out.println("是星期五");
                    break;
                case "t":
                    System.out.println("请输入该星期的第二个字母：");
                    ch=sc.nextLine();
                    if(ch=="h"){
                        System.out.println("是星期二");
                    }
                    else{
                        System.out.println("是星期四");
                    }
                case "s":
                    System.out.println("请输入该星期的第二个字母:");
                    ch=sc.nextLine();
                    if(ch=="a"){
                        System.out.println("是星期六");
                    }
                    else{
                        System.out.println("是星期日");
                    }
        }
    }
}
