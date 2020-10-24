package com.xiaofang;
//有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
public class NumCom {
    public static void main(String []args){
        int num=0,count=0;
        System.out.println("组成的三位数有：");
        for(int i=1;i<5;i++){
            for(int j=1;j<5;j++){
                if(j!=i){
                    num=i*100+j*10;
                }
                for(int k=1;k<5;k++){
                    if(k!=j&&k!=i&&i!=j){
                        count++;
                        num=num+k;
                        System.out.print(num+" ");
                        if(count%6==0){
                            System.out.println();
                        }
                    }

                }
            }
        }
        System.out.println("总个数为：");
        System.out.println(count);
    }
}
