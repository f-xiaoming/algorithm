package com.xiaofang.utils;

import java.util.Base64;
import java.util.Scanner;

public class AES {
    /**
     * 列混和矩阵
     */
    private final static int [][]columnM={
            {2,3,1,1},
            {1,2,3,1},
            {1,1,2,3},
            {3,1,1,2}};
    /**
     * 逆列混和矩阵
     */
    private final static int [][]columnM1={
            {0x0e,0x0b,0x0d,0x09},
            {0x09,0x0e,0x0b,0x0d},
            {0x0d,0x09,0x0e,0x0b},
            {0x0b,0x0d,0x09,0x0e}};
    /**
     * 密钥扩展矩阵
     */
    private final static char[] rCon={0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36};

    /**
     * S盒
     */
    private static final int[][] S1={
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
    private static final int[][] S2={
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
            {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d} };


    public static void main(String[] args) {
        System.out.println("请输入明文：");
        String msg="";//要加密的字符串。
        Scanner sc=new Scanner(System.in);
        msg=sc.nextLine();

        System.out.println("请输入密钥：");
        String key="";//要加密的字符串。
        Scanner sc1=new Scanner(System.in);
        key=sc1.nextLine();

        String encodeMsg = encode(msg, key);
        System.out.println("加密后密文： ");
        System.out.println(encodeMsg);
        System.out.println("解密后的明文： ");
        String decodeMsg=decode(encodeMsg,key);
        System.out.println(decodeMsg);
    }

    /**
     * 完成一个字符串的加密
     * @param str 明文（任意长度）
     * @param key 密钥 （任意长度，不超过128比特）
     * @return 密文（Base64加密）
     */
    private static String encode(String str, String key){
        StringBuilder sb = new StringBuilder();//加密后的信息
        char [][]chars;
        int l=(str.length()/16)+1;
        int i=0;
        //分位l组加密
        for (int j = 0; j <str.length(); j = j + 16) {
            if (i != l - 1) {
                chars = enCode(str.substring(j, j + 16), key);

            } else {
                chars = enCode(str.substring(j), key);
            }
            //加密后的串，追加到sb
            for (char[] aChar : chars) {
                for (int y = 0; y < chars[i].length; y++) {
                    sb.append(aChar[y]);
                }
            }
            i++;
        }//endfor
        //返回 sb（base64 加密）
        return Base64.getEncoder().encodeToString(sb.toString().getBytes());
    }

    /**
     * 对分组（不超过128比特）进行加密
     * @param str  明文分组
     * @param keys 密钥
     * @return 分组加密后的值
     */
    private static char[][] enCode(String str, String keys){
        char [][] chars=new char[4][4];//chars2是明文，用二维字符数组存储。
        char[][] key;//key是密钥，除了初始密钥，还有10轮的子密钥。
        int s=0;
        //把输入的str字符串，赋值给二维数组，不够128比特的填充0
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (s< str.length()) {
                    chars[j][i] = str.charAt(s++);//把输入的明文的值用二维字符数组存储。
                } else {
                    chars[j][i] =0x00;//不够128比特1的补充0
                }
            }
        }
        key=productSubKey(keys);
        //明文和初始密钥做异或
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars[i][j]= (char) (chars[i][j]^key[i][j]);
            }
        }
        //进行10论加密，调用10次roundEnCode(),最后一次不执行列混合。
        char[][] chars1 =new char[4][4];//用于存储每一轮的子密钥
        for(int i=1;i<=10;i++){
            for(int m=0;m<4;m++) {
                int k=0;
                for (int j = i * 4; k < 4; j++) {
                    chars1[m][k++] = key[m][j];//把每一轮的子密钥赋值给chars1,chars1是一个二维数组，共128位
                }
            }
            chars=round(chars,chars1,i,0);//chars是要加密的串，chars1为每轮加密的密钥
        }
        return chars;
    }
    /**
     * 解密函数
     * @param str1 要解密的字符串（任意长度）
     * @param key  密钥（不超过128位）
     * @return 解密后的明文
     */
    private static String decode(String str1, String key){
        StringBuilder decodeMsg= new StringBuilder();
        String str=new String(Base64.getDecoder().decode(str1));
        char [][]chars=new char[4][4];
        for(int i=0;i<str.length();i=i+16){
            int m=0;
            String str2=str.substring(i,i+16);
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    chars[j][k]=str2.charAt(m++);
                }
            }
            decodeMsg.append(deCode(chars, key));
        }
        return decodeMsg.toString();
    }

    /**
     *完成一组 128比特的10轮解密
     * @param chars 一组密文
     * @param keys 密钥
     * @return 解密后的一组明文
     */
    private static String deCode(char[][] chars, String keys){
        int flag=1;//0加密，1解密。
        char[][] key;//chars是密钥，除了初始密钥，还有10轮的子密钥。
        key=productSubKey(keys);
        //密文初始化
        for(int i=0;i<4;i++){
            int k=0;
            for(int j=40;j<44;j++){
                int s=k++;
                chars[i][s]= (char) (chars[i][s]^key[i][j]);//chars是密文
            }
        }
        //进行10论解密，调用10次round(),最后一次不执行逆列混合。
        char[][] chars1 =new char[4][4];//用于存储每一轮的子密钥
        for(int i=9;i>=0;i--){
            for(int m=0;m<4;m++) {
                int k=0;
                for (int j = i * 4; k <4; j++) {
                    chars1[m][k++] = key[m][j];//把每一轮的子密钥赋值给chars1,chars1是一个二维数组，共128位
                }
            }
           chars=round1(chars,chars1,10-i,flag);//chars是要加密的串，chars1为每轮加密的密钥
        }
     StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(chars[j][i] != 0){
                    sb.append(chars[j][i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     *一轮加密
     * @param chars2 要加密的128比特4*4的分组
     * @param chars3 对应的轮密钥
     * @param k 第几轮
     * @param flag 标志是加密还是解密
     * @return
     */
    private static char[][] round(char[][] chars2,char [][] chars3,int k,int flag){
        //对明文进行s盒代换
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                    chars2[i][j] = S(chars2[i][j],flag);
            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<i;j++){
                    chars2[i] = leftCir(chars2[i]);
            }
        }
        //列混合
        if(k!=10&&flag==0) {
            chars2=columnMix(chars2,flag);
        }
        //轮密钥加
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars2[i][j]= (char) (chars2[i][j]^chars3[i][j]);
            }
        }
        return chars2;
    }

    /**
     * 一轮解密
     * @param chars2 要解密的128位4*4的二维数组
     * @param chars3 对应的轮密钥
     * @param k 第几轮
     * @param flag 标志是加密还是解密
     * @return
     */
    private static char[][] round1(char[][] chars2,char [][] chars3,int k,int flag){
        //循环移位，chars[i]循环右移i个字节
        for(int i=0;i<4;i++){
            for(int j=0;j<i;j++){
                chars2[i] = rightCir(chars2[i]);
            }
        }
        //对密文进行逆s盒代换
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                chars2[i][j] = S(chars2[i][j],flag);
            }
        }
        //轮密钥加
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars2[i][j]= (char) (chars2[i][j]^chars3[i][j]);
            }
        }
        //逆列混合
        if(k!=10&&flag==1){
            chars2=columnMix(chars2,flag);
        }
        return chars2;
    }
    /**
     * 列混和
     * @param chars 128比特的4*4的二维数组
     * @param flag flag标志是列混和或逆列混和
     * @return
     */
    private static char[][] columnMix(char[][] chars,int flag){
        char[][] chars1 =new char[4][4];

        int m,n,o,p;
        if(flag==0){
        for(int i=0;i<4;i++){//列混合
;           for(int j=0;j<4;j++) {
                m = multiply(columnM[i][0], chars[0][j]);
                n = multiply(columnM[i][1], chars[1][j]);
                o = multiply(columnM[i][2], chars[2][j]);
                p = multiply(columnM[i][3], chars[3][j]);
                chars1[i][j] = (char) (m ^ n ^ o ^ p);
              }
           }
        }
        else{//逆列混和
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++) {
                    m = multiply(columnM1[i][0], chars[0][j]);
                    n = multiply(columnM1[i][1], chars[1][j]);
                    o = multiply(columnM1[i][2], chars[2][j]);
                    p = multiply(columnM1[i][3], chars[3][j]);
                    chars1[i][j] = (char) (m ^ n ^ o ^ p);
                }
            }
        }
        return chars1;
    }
    private static int doubleX(int  x) {

        return ((x<<1)&0xff^(((x&0x80)==128)?0x1b:0x00));
    }

    /**
     * 该方法是实现两个数相乘，结果在2的8次方域上
     * @param a 乘数
     * @param b 被乘数
     * @return a*b
     */
    private static int multiply(int a,int b){
       int []temp=new int[8];
       temp[0]=a;
       int tempmultiply=0x00;
       for(int i=1;i<8;i++){
           temp[i]=doubleX(temp[i-1]);
       }
       tempmultiply=(b&0x01)*a;
       for(int i=1;i<=7;i++){
           tempmultiply^=(((b>>i)&0x01)*temp[i]);
       }
       return tempmultiply;
    }
    /**
     * 密钥扩展
     * @param key 初始密钥
     * @return 扩展后的密钥
     */
    private static char[][] productSubKey(String key){
        char[][] chars1=new char[4][44];//用于存储初始密钥和每一轮的子密钥
        int k=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(k<key.length()){
                    chars1[j][i]=key.charAt(k++);
                }else{
                    chars1[j][i]=0x00;
                }
            }
        }
        //子密钥的生成。
        char[] ch=new char[4];
        for(int i=4;i<44;i++){//i循环列
            if ((i % 4) == 0) {
                int m=(i/4)-1;
                for(int j=0;j<4;j++){
                    ch[j]=chars1[j][i-1];
                }
                ch=T(ch,m);//T函数
                for(int j=0;j<4;j++){
                    chars1[j][i]= (char) (chars1[j][i-4]^ch[j]);
                }

            } else {
                    for(int j=0;j<4;j++)
                    chars1[j][i] = (char) (chars1[j][i-1] ^ chars1[j][i-4]);
            }
        }
        return chars1;
    }
    //子密钥生成过程中的T函数
    private static char[] T(char[] chars,int i){
           char [] ch=chars;
           ch=leftCir(ch);//循环左移
            for(int j=0;j<4;j++){
                ch[j]=S(ch[j],0);//s盒代换
            }

            ch[0]= (char) (ch[0]^rCon[i]); //异或
            return ch;
    }
    //左循环移位一个字节
    private static char[] leftCir(char []chars){//chars是四个字节的数组，32比特
        char temp=chars[0];
        chars[0]=chars[1];
        chars[1]=chars[2];
        chars[2]=chars[3];
        chars[3]=temp;
        return chars;
    }
    //循环右移一个字节
    private static char[] rightCir(char []chars){//chars是四个字节的数组，32比特
        char temp=chars[3];
        chars[3]=chars[2];
        chars[2]=chars[1];
        chars[1]=chars[0];
        chars[0]=temp;
        return chars;
    }
    //s盒
    private static char S(char ch,int flag) {
        int num=ch;
        int left=num&0xf0;
        left=left>>4;
        int right=num&0x0f;
        if(flag==0){
            num=S1[left][right];
        }
        else{
            num=S2[left][right];
        }
        ch=(char)num;
        return ch;
    }
}


