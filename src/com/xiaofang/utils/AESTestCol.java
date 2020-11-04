package com.xiaofang.utils;

/**
 * @Author xiaowei
 * @Date 2020-11-03 17:13
 */
public class AESTestCol {

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
 private static int MSGS2 [][]={
            {0x32, 0x43, 0xf6, 0xa8},
            {0x88, 0x5a, 0x30, 0x8d},
            {0x31, 0x31, 0x98, 0xa2},
            {0xe0, 0x37, 0x07, 0x34}};

    private static int KEYS2 [][]={
            {0x2b, 0x7e, 0x15, 0x16},
            {0x28, 0xae, 0xd2, 0xa6},
            {0xab, 0xf7, 0x15, 0x88},
            {0x09, 0xcf, 0x4f, 0x3c}};

    private static int KEYS[][] = {
            {0x2b, 0x28, 0xab, 0x09},
            {0x7e, 0xae, 0xf7, 0xcf},
            {0x15, 0xd2, 0x15, 0x4f},
            {0x16, 0xa6, 0x88, 0x3c}};

    public static void main(String[] args) {
        System.out.println(0x9f);
        char c = '1';
        String str = "111";
        System.out.println(Integer.valueOf(str.charAt(1)));


        int encode [][] = encode("","");
        printHex(encode);
        int decode [][] = decodeArr(encode,"");
        printString(decode);
    }

    private static int[][] encode(String msg, String key) {
        int[][] keys = extendKeys(key);
        int[][] msgs = MSGS2;
        int[][] arrs = new int[4][4];

        //1、轮密钥加
        for (int i = 0; i < 4; i++) {
            arrs[i] = EOR(keys[i], msgs[i]);
        }

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

            //2.4、轮密钥加
            for (int j = 0; j < 4; j++) {

                arrs[j] = EOR(keys[i*4+j], arrs[j]);
            }
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
            arrs[j] = EOR(keys[40+j], arrs[j]);
        }
        return arrs;

    }

    private static int[][] decodeArr(int[][] encodeArr, String key) {
        int[][] keys = extendKeys(key);
        int[][] arrs = new int[4][4];

        //轮密钥加
        for (int i = 0; i < 4; i++) {
            arrs[i] = EOR(keys[40+i], encodeArr[i]);
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
                arrs[j] = EOR(keys[i*4+j], arrs[j]);
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
        return arrs;
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
        int aTemp [] = new int[8];
        aTemp [0] = a;
        for (int i = 1; i < 8; i++){
            aTemp[i] = XTIME(aTemp[i - 1]);
        }

        String binary = Integer.toBinaryString(b);
        int length = binary.length();
        int bTemp [] = new int[length];
        for (int i = 0; i < length; i++){
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
     * 密钥扩展
     *
     * @param key
     * @return
     */
    private static int[][] extendKeys(String key) {
        int[][] arrs = KEYS2;

        int[][] keys = new int[44][4];
        for (int i = 0; i < 4; i++) {
            keys[i] = arrs[i];
        }
        for (int j = 4; j < 44; j++) {
            if (j % 4 == 0) {
                // T函数操作 并 异或运算
                keys[j] = T(keys[j - 1], keys[j - 4], j);
            } else {
                //异或运算
                keys[j] = EOR(keys[j - 1], keys[j - 4]);
            }
        }
        return keys;
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
     * T函数操作并异或运算
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private static int[] T(int[] arr1, int[] arr2, int count) {
        int arr[] = new int[4];

        //1、字循环
        arr = remove(arr1, 1, 0);

        //2、字节代换
        arr = replace(arr, 0);

        //3、轮常量异或
        arr[0] = arr[0] ^ Rcon[count / 4 - 1];

        //异或输出
        arr = EOR(arr, arr2);
        return arr;
    }

    private static int[] replace(int[] arr, int flag) {
        int arr2[] = new int[4];
        for (int i = 0; i < 4; i++) {
            //获取高4位值
            int high = arr[i] >> 4;
            //获取低4位值
            int low = arr[i] - (high << 4);
            //S盒置换
            arr2[i] = S[high][low];
        }
        return arr2;
    }

    /**
     * 行位移
     *
     * @param arr
     * @param step
     * @param flag
     * @return
     */
    private static int[] remove(int[] arr, int step, int flag) {
        int arr2[] = new int[4];
        for (int i = 0; i < 4; i++) {
            arr2[i] = arr[(i + step) % 4];
        }
        return arr2;
    }

    private static void printHex(int arr[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString(arr[i][j]) + " ");
            }
            System.out.println();
            if((i+1)%4==0 && i != 0){
                System.out.println();
            }
        }
    }

    private static void printHex44(int arr[][]) {
        for (int i = 0; i < 44; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(Integer.toHexString(arr[i][j]) + " ");
            }
            System.out.println();
            if((i+1)%4==0 && i != 0){
                System.out.println();
            }
        }
    }

    private static void printString(int arr [][]){
        for (int i= 0;i< 4; i++){
            for (int j = 0; j<4; j++){
                char c = (char) arr[i][j];
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

}
