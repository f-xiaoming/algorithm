package com.xiaofang.sort;

import java.io.*;
import java.util.*;

public class StringsSort {
    public static void main(String[] args) {
        Random r = new Random(1);
        BufferedWriter bw1= null;
        OutputStreamWriter writer1 = null;
        BufferedWriter bw2= null;
        OutputStreamWriter writer2 = null;
        System.out.println("请输入要随机生成的字符串的个数：");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String []str=new String[n];
        int ran1=0;
        for (int i = 0; i < n; i++) {
             ran1 = r.nextInt(10) + 1;
            //list.add(getRandomString(ran1));
            str[i]=getRandomString(ran1);

        }
        try{
            File file1 = new File("E:\\RandomString1.txt");
            File file2=new File("E:\\RandomString2.txt");
            writer1 = new OutputStreamWriter(new FileOutputStream(file1));
            bw1 = new BufferedWriter(writer1);
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
            for(int i=0;i<n;i++){
                //bw1.append(list.get(i));
                bw1.append(str[i]);
                bw1.newLine();
            }
            long startTime=System.currentTimeMillis();
           // Collections.sort(list);
           // stringsSort(str);
            QuickSort(str, 0, n-1);
            long endTime=System.currentTimeMillis();
            System.out.println("程序运行的时间为:"+(endTime-startTime)+"ms");
            for(int j=0;j<n;j++){
               // bw2.append(list.get(j));
                bw2.append(str[j]);
                bw2.newLine();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("程序出错");
        }
        finally {
            try {
                bw1.close();
                writer1.close();
                bw2.close();
                writer2.close();
            } catch (IOException e) {
                System.out.println("关闭出错。");
            }
        }
    }
    public static String getRandomString ( int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();//
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
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

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static String[] QuickSort(String[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(String[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            //if (array[i] <= array[end]) {
            if (array[i].compareTo(array[end])<=0) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
