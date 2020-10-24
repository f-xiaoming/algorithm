package com.xiaofang;
//打印杨辉三角1
public class YangHui {
    public static void main(String[] args) {
        yangHui(15);
        System.out.println("***************");
        int n=10;
        int []a={1,0,0,0,0,0};
        int []b={1,0,0,0,0,0};
        for(int i=1;i<=6;i++){
            int j=n;
            int k=0;
            while(j>=0){
                System.out.print(" ");
                j--;
            }
            n=n-2;
            System.out.print(a[0]);
            System.out.print("   ");
            for(k=1;k<=i-2;k++){
                b[k]=a[k]+a[k-1];
                System.out.print(b[k]);
                System.out.print("     ");
            }
            for(int s=0;s<=i-1;s++){
                a[s]=b[s];
            }
            if(i!=1){
                a[i-1]=1;
                System.out.print(a[i-1]);
            }
            System.out.println();
        }
    }

    public static void yangHui(int n){
        int [][] yh = new int [n][n];
        for (int i= 0; i<yh.length; i++){
            int spaceCount = (yh.length-1-i)*4;
            for (int k=spaceCount;k>=0; k--){
                System.out.print(" ");
            }
            for (int j = 0; j<=i; j++){
                if (j==0 ||i==0|| i==j){
                    yh[i][j] = 1;
                }else{
                    yh[i][j] = yh[i-1][j-1] + yh[i-1][j];
                }
                System.out.print(yh[i][j]+"      ");
            }
            System.out.println();
        }
    }
}
