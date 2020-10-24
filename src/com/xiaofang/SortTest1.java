package com.xiaofang;
//2020.10.15
//用文件输入输出，加上运行时间，不定长字符串排序。
import java.util.Arrays;
import java.util.Scanner;

//对10个数进行排序
public class SortTest1 {
    public static void main(String []args){
        int  []array=new int[5];
        int length=array.length;
        boolean flag=false;
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<length;i++){
            array[i]=sc.nextInt();
        }
        //冒泡排序
       for(int i=0;i<length-1;i++) {
            for (int j = 0; j < length - i-1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;
                }
            }
           if (flag == false) {
               break;
           }
        }
        //test
       // Arrays.sort(array);
        for(int i=0;i<length;i++){
            System.out.print(array[i]+" ");
        }

    }
}
