import java.util.Random;
import java.util.Scanner;

public class AES
{
    public static void main(String[] args) {
     //enCode();
        productSubKey();
    }
    public static void enCode(){
        String str="";
        char [][] chars2=new char[4][4];//chars2是明文，用二维字符数组存储。
        char[][] chars=new char[44][4];//chars是密钥，除了初始密钥，还有10轮的子密钥。
        System.out.println("请输入要加密的明文(不要超过128比特位)：");
        Scanner sc=new Scanner(System.in);
        str=sc.nextLine();
        int s=0;

        //把输入的str字符串，赋值给二维数组
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if (s< str.length()) {
                    chars2[i][j] = str.charAt(s++);//把输入的明文的值用二维字符数组存储。
                } else {
                    chars2[i][j] = 48;//不够128比特1的补充0
                }
            }
        }
        //产生随机密钥
        chars=productSubKey();

        //明文和初始密钥做异或
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars2[i][j]= (char) (chars2[i][j]&chars[i][j]);
            }
        }
        //进行10论加密，调用10次roundEnCode(),最后一次不执行列混合。
        for(int i=1;i<=10;i++){
            char chars3[][]=new char[4][4];
            int k=0;
            for(int j=i*4;k<4;j++){
                chars3[k++]=chars[j];//把每一轮的子密钥赋值给chars3,chars3是一个二维数组，共128位
            }
            chars2=roundEnCode(chars2,chars3,i);//
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.println(chars2);
            }
        }

    }
    //轮加密函数
    public static char[][] roundEnCode(char[][] chars2,char [][] chars3,int k){//chars是128位明文，4行4列的二维数组
        //对明文进行s盒代换
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                chars2[i][j] = S(chars2[i][j]);
            }
        }
        //循环移位，chars[i]循环左移i个字节
        for(int i=0;i<4;i++){
            for(int j=0;j<i;j++){
                chars2[i]=leftCir(chars2[i]);
            }
        }
        //列混合
        if(k!=10) {
            columnBlend(chars2);
        }
        //轮密钥加
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars2[i][j]= (char) (chars2[i][j]&chars3[i][j]);
            }
        }
        return chars2;
    }
    //列混合
    public static void columnBlend(char[][] chars){
        for(int j=0;j<4;j++){
            chars[0][j]= (char) ((2*chars[0][j])^(3*chars[1][j])^chars[2][j]^chars[3][j]);
        }
        for(int j=0;j<4;j++){
            chars[1][j]= (char) (chars[0][j]^(2*chars[1][j])^(3*chars[2][j])^chars[3][j]);
        }
        for(int j=0;j<4;j++){
            chars[2][j]= (char) (chars[0][j]^chars[1][j]^(2*chars[2][j])^(3*chars[3][j]));
        }
        for(int j=0;j<4;j++){
            chars[3][j]= (char) ((3*chars[0][j])^chars[1][j]^chars[2][j]^(2*chars[3][j]));
        }
    }
    //生成子密钥
    public static char[][] productSubKey(){
        String str="abcdefghijklmnop";
        //初始密钥
        char[][] chars1=new char[4][44];//初始密钥和每一轮的子密钥
       //128位初始密钥通过随机函数生成
        int k=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                chars1[j][i]=str.charAt(k++);
            }
        }
       /* for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(chars1[i][j]+" ");
            }
            System.out.println();
        }*/
        //子密钥的生成。
        char[] ch=new char[4];
        for(int i=4;i<44;i++){//i循环列
            if ((i % 4) == 0) {
                int m=(i/4)-1;
                //System.out.println(m);
                for(int j=0;j<4;j++){
                    ch[j]=chars1[j][i-1];
                   // System.out.println("ch测试");
                    //System.out.println(ch[j]);
                }
                ch=T(ch,m);//T函数
                for(int j=0;j<4;j++){
                    chars1[j][i]= (char) (chars1[j][i-1]^ch[j]);
                }

            } else {
                for(int j=0;j<4;j++)
                    chars1[j][i] = (char) (chars1[j][i-1] ^ chars1[j][i-4]);
            }
        }
        for(int i=0;i<44;i++){
            for(int j=0;j<4;j++){
                System.out.print(chars1[i][j]+" ");
            }
            System.out.println();
        }
        return chars1;
    }
    //子密钥生成过程中的T函数
    public static char[] T(char[] chars,int i){
           char [] ch=chars;
          //System.out.println("T函数。。。。");
           //for(int k=0;k<4;k++){
             //  System.out.println(ch[k]);
           //}
           ch=leftCir(ch);//循环左移
       // for(int s=0;s<4;s++){
            //System.out.println(ch[s]);
       // }循环测试可以
            for(int j=0;j<4;j++){
                System.out.println("s盒之前："+ch[j]);
                ch[j]=S(ch[j]);//s盒代换
                System.out.println("s盒之后:"+ch[j]);
            }
             char[] rcon={0x01, 0x02,
                    0x04, 0x08,
                    0x10, 0x20,
                    0x40, 0x80,
                    0x1b0, 0x36};
            System.out.println(rcon[i]);
            ch[0]= (char) (ch[0]&rcon[i]);
            return ch;
    }
    //左循环移位一个字节
    public static char[] leftCir(char []chars){//chars是四个字节的数组，32比特
        char temp=chars[0];
        chars[0]=chars[1];
        chars[1]=chars[2];
        chars[2]=chars[3];
        chars[3]=temp;
        return chars;
    }
    //s盒
    public static char S(char ch) {
        char[][] chars1={
                {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
                {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
                {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
                {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
                {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
                {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
                {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
                {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
                {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
                {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
                {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
                {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
                {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
                {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
                {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
                {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};
        int num=ch;
        System.out.println(ch+"的整数值："+num);
        int left=num&0x000000f0;
        left=left>>4;
        System.out.println(ch+"的高四位："+left);
        int right=num&0x0000000f;
        System.out.println(ch+"的低四位："+right);
        ch=chars1[left][right];
        int w=ch;
        System.out.println("ch字符测试"+ch);
        System.out.println("ch测试"+w);
        return ch;
    }
    /**
     * 生成随机密钥
     * @return
     */
    /*public static char[][] getRandomPrimKey(){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        char[][] chars= new char[4][4];
        StringBuffer sb = new StringBuffer();//
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        int k=0;
        //把随机生成的密钥赋值给chars,二维数组，共128比特
        for(int i=0;i<4;i++){
          for(int j=0;j<4;j++){
              chars[i][j]=sb.charAt(k++);
          }
        }
        return chars;
    }*/
}


