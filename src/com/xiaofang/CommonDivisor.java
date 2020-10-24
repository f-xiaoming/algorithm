package com.xiaofang;

import java.util.Scanner;

//求两个数的最大公约数和最小公倍数。
public class CommonDivisor {
    public static void main(String[] args) {
        int m, n, maxCD, minCM;
        System.out.println("请输入m和n两个整数：");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        m = sc1.nextInt();
        n = sc2.nextInt();
        maxCD = maxComDiv(m, n);
        minCM = (m * n) / maxCD;
        System.out.println(m + "和" + n + "的最大公约数为:" + maxCD + " 最小公倍数为:" + minCM);
    }

    private static int maxComDiv(int m, int n) {
        int v;
        while (n != 0) {
            v = m % n;
            m = n;
            n = v;
        }
        return m;
    }

}
