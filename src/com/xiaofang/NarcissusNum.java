package com.xiaofang;

public class NarcissusNum {
    public static void main(String[] args) {
        System.out.println("水仙花数为：");
        for (int j = 100; j <= 999; j++) {
            int i, t, h;
            h = j / 100;
            t = (j /10) % 10;
            i = (j % 10) ;
            if (h * h * h + t * t * t + i * i * i == j) {
                System.out.print(j + " ");
            }
        }
    }
}
