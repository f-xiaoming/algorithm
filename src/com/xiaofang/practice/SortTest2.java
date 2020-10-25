package com.xiaofang.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SortTest2 {
    public static void main(String []args){
        List<Integer> arrList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    //   List<Integer> arrList1 = new LinkedList<>();
        Integer n=3;
        for(int i=arrList.size()-1;i>=0;i--){
            if(n>=arrList.get(i)) {
                arrList.add(i + 1, n);
                break;
            }
        }
        for(int i:arrList){
            System.out.print(i+" ");
        }
    }
}
