import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomTest1 {
    public static void main(String[] args) {
        Random r = new Random(1);
        List<String> list=new ArrayList<String>();

        BufferedWriter bw1= null;
        OutputStreamWriter writer1 = null;
        BufferedWriter bw2= null;
        OutputStreamWriter writer2 = null;

        for (int i = 0; i < 1000; i++) {
            int ran1 = r.nextInt(10);
            for(int j=0;j<1000;j++) {
                //System.out.println(getRandomString(ran1));
                list.add(getRandomString(ran1));
            }
        }
        try{
            File file1 = new File("E:\\RandomString1.txt");
            File file2=new File("E:\\RandomString2.txt");
            writer1 = new OutputStreamWriter(new FileOutputStream(file1));
            bw1 = new BufferedWriter(writer1);
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
            for(int i=0;i<1000;i++){
                bw1.append(list.get(i));
                bw1.newLine();
            }
            long startTime=System.currentTimeMillis();
            Collections.sort(list);
            long endTime=System.currentTimeMillis();
            System.out.println("程序运行的时间为:"+(endTime-startTime)+"ms");
            for(int j=0;j<1000;j++){
                bw2.append(list.get(j));
                bw2.append(" ");
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
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(52);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
