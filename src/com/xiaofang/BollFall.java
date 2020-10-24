package com.xiaofang;
//一球从100米高度自由落下，每次落地后反跳回原高度的一半；
//        再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
public class BollFall {
    public static void main(String []args){
        double high=100;
        double highSum=0;
        for(int i=1;i<10;i++){
           highSum+=high;
           high=high/2.0;
        }
        highSum=100+highSum;
        high=high/2.0;
        System.out.println("第10次落地时共经历"+highSum+"米");
        System.out.println("第10次反弹的高度为："+high);
        System.out.println(100.00/1024.00);
    }
}
