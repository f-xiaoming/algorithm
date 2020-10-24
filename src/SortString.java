import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SortString {
    public static void main(String[] args) {
        InputStreamReader reader = null;
        BufferedReader br = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        String str1="";
        List<String> list=new ArrayList<String>();
        int i=0;
        try {
            File file1 = new File("E:\\testIn.txt");
            File file2=new File("E:\\testOut.txt");
            reader = new InputStreamReader(new FileInputStream(file1));
            br = new BufferedReader(reader);//br字符流
            String line;
            line = br.readLine();
            while (line != null) {
              //  System.out.println(line);
                str1=line;
                list.add(str1);
               // System.out.println(list.get(i));
                // i++;
                line = br.readLine();
            }
            writer=new OutputStreamWriter(new FileOutputStream(file2));
            bw=new BufferedWriter(writer);
            long startTime =System.nanoTime();
            Collections.sort(list);
            long endTime=System.nanoTime();
            long time=endTime-startTime;
            System.out.println("程序运行的时间为："+time+"ns");
            for(String str:list){
                System.out.println(str);
                bw.append(str);
                bw.newLine();
            }
           // bw.append("啦啦");
            //bw.flush();*/

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("程序出错");
        }
        finally {
            try {
                br.close();
                reader.close();
                bw.close();
                writer.close();
            } catch (IOException e) {
                System.out.println("关闭出错。");
            }
        }

    }
}
