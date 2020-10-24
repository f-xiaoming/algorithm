import java.io.*;
import java.util.*;

//生成随机数，并排序。文件输入输出。
public class RandomTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>(8);
        List<String> linkdList = new LinkedList<>();
        Map<String, String>  map = new HashMap<>();
        map.put("name", "zhangsan");

        Set<String> stet = new HashSet<>();


        Random r = new Random(1);
        boolean flag = false;
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int ran1 = r.nextInt(1000);
            arr[i] = ran1;
            //System.out.print(ran1+" ");
        }

        BufferedWriter bw1= null;
        OutputStreamWriter writer1 = null;
        BufferedWriter bw2= null;
        OutputStreamWriter writer2 = null;
        String str="";
        try {
            File file1 = new File("E:\\Random.txt");
            writer1 = new OutputStreamWriter(new FileOutputStream(file1));
            bw1 = new BufferedWriter(writer1);
            for (int i = 0; i < 1000; i++) {
                str=String.valueOf(arr[i]);
                bw1.append(str);
                bw1.append(" ");
            }
            long startTime =System.currentTimeMillis();
            /*for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp;
                        temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        flag = true;
                    }
                }
                if (flag == false) {
                    break;
                }
            }*/
            long endTime=System.currentTimeMillis();
            long time=endTime-startTime;
            System.out.println("程序运行的时间为："+time+"ms");
            File file2 = new File("E:\\RandomSort.txt");
            writer2 = new OutputStreamWriter(new FileOutputStream(file2));
            bw2 = new BufferedWriter(writer2);
            for (int i = 0; i < 1000; i++) {
                str=String.valueOf(arr[i]);
                bw2.append(str);
                bw2.append(" ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("程序出错。");

        } finally {
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


 }
