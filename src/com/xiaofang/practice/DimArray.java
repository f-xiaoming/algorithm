package com.xiaofang.practice;

public class DimArray {
    public static void main(String []args){
        int array[][]={{1,2,3},{4,5,6},{7,8,9}};
        int count=0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(j==i){
                    count+=array[i][j];
                }
            }
        }
        for(int i=0;i<array.length;i++){
            count+=array[i][i];
        }
        System.out.println(count);
    }
}
