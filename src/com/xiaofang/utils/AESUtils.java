package com.xiaofang.utils;

import java.util.Base64;
import java.util.Random;

/**
 * @Author xiaowei
 * @Date 2020-11-02 9:02
 */
public class AESUtils {

    /**
     * 轮常量
     */
    private static final int Rcon[] = {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36};

    /**
     * 列混合要用到的矩阵
     */
    private static final int colM[][] = {
            {2, 3, 1, 1},
            {1, 2, 3, 1},
            {1, 1, 2, 3},
            {3, 1, 1, 2}};
    /**
     * 逆列混合用到的矩阵
     */
    private static int deColM[][] = {
            {0xe, 0xb, 0xd, 0x9},
            {0x9, 0xe, 0xb, 0xd},
            {0xd, 0x9, 0xe, 0xb},
            {0xb, 0xd, 0x9, 0xe}};

    /**
     * S盒
     */
    private static final int S[][] = {
            {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
            {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
            {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
            {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
            {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
            {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
            {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
            {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
            {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
            {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
            {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
            {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
            {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
            {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
            {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
            {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};

    /**
     * 逆S盒
     */
    private static final int S2[][] = {
            {0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
            {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
            {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
            {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
            {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
            {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
            {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
            {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
            {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
            {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
            {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
            {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
            {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
            {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
            {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
            {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d}};

    /**
     * 测试所用明文
     */
    private static int MSGS [][]={
            {0x32, 0x88, 0x31, 0xe0},
            {0x43, 0x5a, 0x31, 0x37},
            {0xf6, 0x30, 0x98, 0x07},
            {0xa8, 0x8d, 0xa2, 0x34}};

    /**
     * 测试所用密钥
     */
    private static int KEYS [][]={
            {0x2b, 0x28, 0xab, 0x09},
            {0x7e, 0xae, 0xf7, 0xcf},
            {0x15, 0xd2, 0x15, 0x4f},
            {0x16, 0xa6, 0x88, 0x3c}};

    public static void main(String[] args) {

        String key = "abcdefghijklmnop";
        String msg = "abcdefghi123mnop";

        String encodeMsg = encode(msg,key);
        System.out.println("加密密文：");
        System.out.println(encodeMsg);

        String decodeMsg = decodeArr(encodeMsg, key);
        System.out.println("解密明文：");
        System.out.println(decodeMsg);


    }


    /**
     * 加密
     * @param msg 明文
     * @param key 密钥
     * @return 密文（Base64加密）
     */
    private static String encode(String msg, String key) {
        int[][] keys = extendKeys3(key);
        int[][] msgs = initialMsg(msg);
        int[][] arrs = new int[4][4];

        //1、轮密钥加
        for (int i = 0; i < 4; i++) {
            arrs[i] = EOR(keys[i], msgs[i]);
        }
        /*System.out.println("第一次轮密钥加：");
        printHex(arrs);*/

        //前9次轮询加密
        for (int i= 1; i<=9; i++){
            //2、轮询加密
            //2.1、字节代换
            for (int j = 0; j < 4; j++) {
                arrs[j] = replace(arrs[j], 0);
            }

            //2.2、行位移
            for (int j = 0; j < 4; j++) {
                arrs[j] = remove(arrs[j], j, 0);
            }

            //2.3、列混淆
             arrs =confusion(arrs, 0);
            /*if (i == 1){
                System.out.println("第一次列混淆：");
                printHex(arrs);
            }*/

            //2.4、轮密钥加
            for (int j = 0; j < 4; j++) {
                int tempArr [] = new int[4];
                for (int m =0; m<4; m++){
                    tempArr[m]= keys[j][i*4+m];
                }
                arrs[j] = EOR(tempArr, arrs[j]);
            }
            /*if (i == 1){
                System.out.println("第二次列轮密钥加：");
                printHex(arrs);
            }*/
        }
        //第10次轮询加密
        //3.1、字节代换
        for (int j = 0; j < 4; j++) {
            arrs[j] = replace(arrs[j], 0);
        }

        //3.2、行位移
        for (int j = 0; j < 4; j++) {
            arrs[j] = remove(arrs[j], j, 0);
        }

        //3.3、轮密钥加
        for (int j = 0; j < 4; j++) {
            int tempArr [] = new int[4];
            for (int m =0; m<4; m++){
                tempArr[m]= keys[j][40+m];
            }
            arrs[j] = EOR(tempArr, arrs[j]);
        }
        //将加密结果使用Base64加密（友好展示，无乱码）
        return base64Encode(arrs);
    }

    /**
     * 解密
     * @param encodeMsg 密文
     * @param key 密钥
     * @return 明文
     */
    private static String decodeArr(String encodeMsg, String key) {
        int[][] keys = extendKeys3(key);
        int[][] encodeArr = encodeMsgToArr(encodeMsg);
        int[][] arrs = new int[4][4];

        //轮密钥加
        for (int i = 0; i < 4; i++) {
            int tempArr [] = new int[4];
            for (int m =0; m<4; m++){
                tempArr[m]= keys[i][40+m];
            }
            arrs[i] = EOR(tempArr, encodeArr[i]);
        }

        //前9次轮询解密
        for (int i= 9; i>=1; i--){
            //2、轮询解密

            //2.1、逆行位移
            for (int j = 0; j < 4; j++) {
                arrs[j] = remove(arrs[j], j, 1);
            }

            //2.2、逆字节代换
            for (int j = 0; j < 4; j++) {
                arrs[j] = replace(arrs[j], 1);
            }

            //2.3、轮密钥加
            for (int j = 0; j < 4; j++) {
                int tempArr [] = new int[4];
                for (int m =0; m<4; m++){
                    tempArr[m]= keys[j][i*4+m];
                }
                arrs[j] = EOR(tempArr, arrs[j]);
            }

            //2.4、逆列混淆
            confusion(arrs, 1);

        }
        //第10次轮询解密
        //3.1、逆行位移
        for (int j = 0; j < 4; j++) {
            arrs[j] = remove(arrs[j], j, 1);
        }

        //3.2、逆字节代换
        for (int j = 0; j < 4; j++) {
            arrs[j] = replace(arrs[j], 1);
        }

        //3.3、轮密钥加
        for (int j = 0; j < 4; j++) {
            arrs[j] = EOR(keys[j], arrs[j]);
        }
        return printString(arrs);
    }


    /**
     * 字节替换
     * @param arr
     * @param flag
     * @return
     */
    private static int[] replace(int[] arr, int flag) {
        int arr2[] = new int[4];
        for (int i = 0; i < 4; i++) {
            //获取高4位值
            int high = arr[i] >> 4;
            //获取低4位值
            int low = arr[i] - (high << 4);
            //S盒置换
            if (flag == 0) {
                arr2[i] = S[high][low];
            } else {
                arr2[i] = S2[high][low];
            }
        }
        return arr2;
    }

    /**
     * 行位移
     * @param arr
     * @param step
     * @param flag
     * @return
     */
    private static int[] remove(int[] arr, int step, int flag) {
        int arr2[] = new int[4];
        if (flag == 0) {
            for (int i = 0; i < 4; i++) {
                arr2[i] = arr[(i + step) % 4];
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                arr2[i] = arr[(4 + i - step) % 4];
            }
        }
        return arr2;
    }


    /**
     * 列混淆
     * @param arrs
     * @param flag
     * @return
     */
    private static int[][] confusion(int[][] arrs, int flag) {
        int temp [][] = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                temp[i][j] = arrs[i][j];
            }
        }
        for (int j = 0; j < 4; j++) {
            if (flag == 0) {
                arrs[0][j] = GFMultiply(colM[0][0], temp[0][j]) ^ GFMultiply(colM[0][1], temp[1][j]) ^ GFMultiply(colM[0][2], temp[2][j]) ^ GFMultiply(colM[0][3], temp[3][j]);
                arrs[1][j] = GFMultiply(colM[1][0], temp[0][j]) ^ GFMultiply(colM[1][1], temp[1][j]) ^ GFMultiply(colM[1][2], temp[2][j]) ^ GFMultiply(colM[1][3], temp[3][j]);
                arrs[2][j] = GFMultiply(colM[2][0], temp[0][j]) ^ GFMultiply(colM[2][1], temp[1][j]) ^ GFMultiply(colM[2][2], temp[2][j]) ^ GFMultiply(colM[2][3], temp[3][j]);
                arrs[3][j] = GFMultiply(colM[3][0], temp[0][j]) ^ GFMultiply(colM[3][1], temp[1][j]) ^ GFMultiply(colM[3][2], temp[2][j]) ^ GFMultiply(colM[3][3], temp[3][j]);
            } else {
                arrs[0][j] = GFMultiply(deColM[0][0], temp[0][j]) ^ GFMultiply(deColM[0][1], temp[1][j]) ^ GFMultiply(deColM[0][2], temp[2][j]) ^ GFMultiply(deColM[0][3], temp[3][j]);
                arrs[1][j] = GFMultiply(deColM[1][0], temp[0][j]) ^ GFMultiply(deColM[1][1], temp[1][j]) ^ GFMultiply(deColM[1][2], temp[2][j]) ^ GFMultiply(deColM[1][3], temp[3][j]);
                arrs[2][j] = GFMultiply(deColM[2][0], temp[0][j]) ^ GFMultiply(deColM[2][1], temp[1][j]) ^ GFMultiply(deColM[2][2], temp[2][j]) ^ GFMultiply(deColM[2][3], temp[3][j]);
                arrs[3][j] = GFMultiply(deColM[3][0], temp[0][j]) ^ GFMultiply(deColM[3][1], temp[1][j]) ^ GFMultiply(deColM[3][2], temp[2][j]) ^ GFMultiply(deColM[3][3], temp[3][j]);
            }
        }

        return arrs;
    }

    /**
     * GF(2^8)有限域乘法
     * 原理请参考：https://blog.csdn.net/yezhen910328/article/details/52934916
     * @param a 乘数a
     * @param b 被乘数b
     * @return
     */
    private static int GFMultiply(int a, int b)
    {
        int R [] ={0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80};
        //存储乘数a与R[]相乘的结果
        int aTemp [] = new int[8];
        aTemp [0] = a;
        for (int i = 1; i < 8; i++){
            aTemp[i] = XTIME(aTemp[i - 1]);
        }

        String binary = Integer.toBinaryString(b);
        int length = binary.length();
        //存储被乘数b的二进制表示
        int bTemp [] = new int[length];
        for (int i = 0; i < length; i++){
            //charAr()返回char类型，转为int时需减48
            bTemp[i] = binary.charAt(length - 1 - i) -48;
        }

        int multiply = 0x00;
        for (int i = 0; i < length; i++){
            if (bTemp[i] == 1){
                multiply ^= aTemp[i];
            }
        }
        return multiply;
    }

    private static int XTIME(int x) {
        int a = (x << 1) ^ ((x >= 128) ? 0x1b : 0x00);
        String str = Integer.toHexString(a & 0xff);
        return a & 0xff;
    }

    /**
     * 密钥生成中的T函数
     * @param arr 需变换的密钥
     * @param count 变换轮数
     * @return
     */
    private static int [] T1(int[]arr, int count) {
        int arr2 [] = new int[4];

        //a.字循环：将1个字中的4个字节循环左移1个字节。即将输入字[b0, b1, b2, b3]变换成[b1,b2,b3,b0]
        for (int i = 0; i < 4; i++) {
            arr2[i] = arr[(i + 1) % 4];
        }

        //b.字节代换：对字循环的结果使用S盒进行字节代换
        for (int i = 0; i < 4; i++) {
            //获取高4位值
            int high = arr2[i] >> 4;
            //获取低4位值
            int low = arr2[i] - (high << 4);
            arr2[i] = S[high][low];
        }

        //c.轮常量异或：将前两步的结果同轮常量Rcon[j]进行异或，其中j表示轮数
        arr2[0] = arr2[0] ^ Rcon[count/4 -1];
        return arr2;
    }

    /**
     * 密钥扩展-生成44列子密钥
     * @param key 密钥字符串
     * @return
     */
    private static int[][] extendKeys3(String key) {
        int initKey [][] = initialMsg(key);

        int [][] arrs = new int[4][44];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                arrs[i][j] = initKey[i][j];
            }
        }
        for (int j = 4; j < 44; j++){
            if (j%4 == 0){
                int temp [] = new int [4];
                for (int m = 0; m < 4; m++) {
                    temp[m] = arrs[m][j-1];
                }
                int []temp2 = T1(temp, j);
                for (int m = 0; m < 4; m++) {
                    arrs[m][j]= arrs[m][j - 4] ^ temp2[m];
                }
            }else{
                for (int i = 0; i<4; i++){
                    arrs[i][j] = arrs[i][j-1] ^ arrs[i][j-4];
                }
            }
        }
        return arrs;
    }

    /**
     * 格式化密钥/明文
     *
     * @return
     */
    public static int[][] initialMsg(String key) {
        int[][] arr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.valueOf(key.charAt(i * 4 + j));
            }
        }
        return arr;
    }

    /**
     * 数组异或操作
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private static int[] EOR(int[] arr1, int[] arr2) {
        int arr[] = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = arr1[i] ^ arr2[i];
        }
        return arr;
    }

    /**
     * 十九进制打印密钥
     * @param arr
     */
    private static void printHex44(int arr [][]){
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<44; j++){
                System.out.print(Integer.toHexString(arr[i][j]) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 生成随机字符串
     *
     * @return
     */
    private static String randomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWSYZ0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int a = random.nextInt(str.length());
            sb.append(str.charAt(a));
        }
        return sb.toString();
    }

    /**
     * 打印结果
     * @param arr
     */
    private static void print(int arr [][]){
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<4; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 结果转为字符串打印
     * @param arr
     * @return
     */
    private static String printString(int arr [][]){
        StringBuffer sb = new StringBuffer();
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<4; j++){
                sb.append((char) arr[j][i]);
            }
        }
        return sb.toString();
    }

    /**
     * 转为十六进制打印
     * @param arr
     */
    private static void printHex(int arr [][]){
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<4; j++){
                System.out.print(Integer.toHexString(arr[i][j]) + " ");
            }
            System.out.println();
        }
    }

    /**
     * 将加密数组结果转为字符串并经Base64加密
     * @param arrs
     * @return
     */
    private static String base64Encode(int arrs[][]){
        StringBuffer sb = new StringBuffer();
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<4; j++){
                sb.append((char) arrs[i][j]);
            }
        }
        String str = Base64.getEncoder().encodeToString(sb.toString().getBytes());
        return str;
    }

    /**
     * 将Base64加密串解密并转为数组
     * @param encodeMsg
     * @return
     */
    public static int[][] encodeMsgToArr(String encodeMsg) {
        String str = new String(Base64.getDecoder().decode(encodeMsg));
        int[][] arr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.valueOf(str.charAt(i * 4 + j));
            }
        }
        return arr;
    }


}
