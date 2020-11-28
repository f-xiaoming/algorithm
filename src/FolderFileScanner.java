import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
public class FolderFileScanner {
    private static ArrayList<Object> scanFiles = new ArrayList<Object>();
    public static ArrayList<Object> scanFiles(String folderPath) throws Exception, Exception {
        File directory = new File(folderPath);
        if(!directory.isDirectory()){
            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if(directory.isDirectory()){//输入的是文件夹
            File [] fileList = directory.listFiles();
            for(int i = 0; i < fileList.length; i++){//filelist[i]是每个apk反编译后的文件，A1-A1032。
                if(fileList[i].isDirectory()){
                    File [] fileList1=fileList[i].listFiles();
                    if(fileList1[3].getName().equals("lib")) {//找到lib文件
                        File [] fileList2=fileList1[3].listFiles();
                        for(int j=0;j<fileList2.length;j++){//循环Lib文件夹下的文件夹
                            File [] fileList3=fileList2[j].listFiles();
                            for(int k=0;k<fileList3.length;k++){//so文件
                                scanFiles.add(fileList3[k].getAbsoluteFile());
                            }
                        }
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return scanFiles;
    }
    public static void main(String[] args) throws Exception {
        String str="D:\\soft\\apktool";
        ArrayList<Object> files=new ArrayList<Object>();
        files=scanFiles(str);
        for (int i=0;i<files.size();i++){
            System.out.println(files.get(i));
        }
    }
}
