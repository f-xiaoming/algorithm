package com.xiaofang.socket;

public class test {
    public static void main(String[] args) {
       byte a = (byte) 0xc0;
       byte b = (byte)0x0c;
       int c = (a&0x3f) | (b&0xff);

        System.out.println(b);
       System.out.println(a);
        System.out.println(c);

    }
}
