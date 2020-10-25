package com.xiaofang.practice;
//将一个数组逆序并输出
public class ArrayTest {
    public static void main(String[] args) {
        int []arr=new int[]{1,2,3,4,5};
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<arr.length/2;i++){
            int temp;
            temp=arr[i];
            arr[i]=arr[arr.length-1];
            arr[arr.length-1]=temp;
        }
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}
