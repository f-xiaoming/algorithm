package com.xiaofang;

//输出101-200之间的素数
public class PrimeNumber {
    public static void main(String[] args) {
        System.out.println("101-200之间的素数有：");
        for (int i = 101; i <= 200; i++) {
            boolean flag;
            flag = isPrime(i);
            if (flag) {
                System.out.print(i + " ");
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
