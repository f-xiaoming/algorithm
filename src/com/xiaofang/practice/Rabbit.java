package com.xiaofang.practice;

import java.util.Scanner;

public class Rabbit {
    public static void main(String[] agrs) {
        System.out.println("请输入n的值：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int num;
            num = ny_rabbit(i);
            System.out.println("第" + i + "年的兔子总数是：" + num);
        }
    }

    private static int ny_rabbit(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return ny_rabbit(n - 1) + ny_rabbit(n - 2);
    }
}
