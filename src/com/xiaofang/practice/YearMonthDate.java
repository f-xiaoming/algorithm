package com.xiaofang.practice;

import java.util.Scanner;
//输入某年某月某日，判断这一天是这一年的第几天？
public class YearMonthDate {
   public static void main(String []args){
       int year,month,date,count=0;
       boolean flag;
       System.out.println("请输入年月日：");
       Scanner sc=new Scanner(System.in);
       year=sc.nextInt();
       month=sc.nextInt();
       date=sc.nextInt();
       flag=isLeapYear(year);
      for(int i=1;i<month;i++){
          if((i==1)||(i==3)||(i==5)||(i==7)||(i==8)||(i==10)||(i==12)){
              count=count+31;
          }
          else if(i==2){
              if(flag){
                  count+=29;
              }
              else{
                  count+=28;
              }
          }
          else{
              count+=30;
          }
      }
      count=count+date;
       System.out.println("这一天是"+year+"的第"+count+"天");
       System.out.println(count);
   }
   private static boolean isLeapYear(int year){
       if(year%4==0&&year%100!=0||year%400==0){
           return true;
       }
       else {
           return false;
       }
    }

}
