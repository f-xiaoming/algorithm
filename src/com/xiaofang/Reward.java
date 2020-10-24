package com.xiaofang;

import java.util.Scanner;

//利润(I)低于或等于10万元时，奖金可提10%；
// 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可可提成7.5%；
// 20万到40万之间时，高于20万元的部分，可提成5%；
// 40万到60万之间时高于40万元的部分，可提成3%；
// 60万到100万之间时，高于60万元的部分，可提成1.5%，
// 高于100万元时，超过100万元的部分按1%提成，从键盘输入当月利润I，求应发放奖金总数？
public class Reward {
    public static void main(String[] args) {
        double i;//利润
        double temp = 0, r = 0;//奖金
        System.out.println("请输入当月的利润（单位为万元）：");
        Scanner sc = new Scanner(System.in);
        i = sc.nextDouble();
        if (i >= 100) {
            temp = (i - 100) * 0.01;
            r = r + temp;
            i = 100;
        }
        if (i >= 60) {
            temp = (i - 60) * 0.015;
            r = r + temp;
            i = 60;
        }
        if (i >= 40) {
            temp = (i - 40) * 0.03;
            r = r + temp;
            i = 40;
        }
        if (i >= 20) {
            temp = (i - 20) * 0.05;
            r = r + temp;
            i = 20;
        }
        if (i >= 10) {
            temp = (i - 10) * 0.075;
            r = r + temp;
            i = 10;
        }
        if (i <= 10) {
            temp = i * 0.1;
            r = r + temp;
        }
        System.out.println("当月的提成为：" + r + "万元");
    }
}
