package com.xiaofang.practice;

import java.util.Scanner;

//正整数n分解质因子。
public class IntDecompose {
    public static void main(String[] args) {
        int n, k = 2;
        System.out.println("请输入n的值：");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.print(n + "=");
        while (true) {
            if (n % k == 0) {
                n = n / k;
                System.out.print(k);
                k = 2;
                if (n != 1) {
                    System.out.print("*");
                } else break;
            } else {
                k++;
            }
        }
    }
}
