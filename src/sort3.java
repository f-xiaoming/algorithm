import java.util.Scanner;

//对十个数从小到大进行排序
public class sort3 {
    public static void main(String[] args) {
        int []arr=new int[10];
        int temp=0;
        boolean flag=false;
        System.out.println("请输入十个整数");
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<10;i++){
            arr[i]=sc.nextInt();
        }
        //冒泡排序
        for(int i=0;i<10;i++){
            for(int j=0;j<10-i-1;j++){
                if(arr[j]>arr[j+1]){
                    temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                    flag=true;
                }
            }
            if(flag==false){
                break;
            }
        }
        //
        for(int i=0;i<10;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
