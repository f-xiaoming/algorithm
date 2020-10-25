package com.xiaofang.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringsSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        Collections.sort(list);

    }
    public static String[] stringsSort(String []str){
        String str1="";
        for(int i=0;i<str.length-1;i++){
            for(int j=0;j<str.length-1-i;j++){
                if(str[j].compareTo(str[j+1])>0){
                   str1=str[j];
                   str[j]=str[j+1];
                   str[j+1]=str1;
                }
            }
        }
        return str;
    }

}
