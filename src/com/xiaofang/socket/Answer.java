package com.xiaofang.socket;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    //设置解析起始位置
    private static int index = 12;
//解析首部
    public static Head AnswerHead(byte[] data) {
//      创建首部类的对象
        Head head = new Head();
//        获取固定首部长度的数组
        byte[] h = new byte[12];
        System.arraycopy(data, 0, h, 0, 12);
//        标识位置解析
        head.setID("0x" + HexConver.conver16HexStr(h[0]) + HexConver.conver16HexStr(h[1]));
//      Flags字段解析
        String[] arr = {Long.toString(h[2] & 0xff, 2), Long.toString(h[3] & 0xff, 2)};
        head.setQR(arr[0].substring(0, 1));
        head.setOpcode(arr[0].substring(1, 5));
        head.setAA(arr[0].substring(5, 6));
        head.setTC(arr[0].substring(6, 7));
        head.setRD(arr[0].substring(7, 8));
        head.setRA(arr[1].substring(0, 1));
        head.setZ(arr[1].substring(1, 4));
        head.setRecode(arr[1].substring(4, 8));
//        问题数，回答数，授权信息数和附加信息数
        head.setQuestions(h[5]);
        head.setAnswers(h[7]);
        head.setAuthority(h[9]);
        head.setAdditional(h[11]);
//返回解析结果的对象
        return head;
    }

//获得解析结果的列表
    public static ArrayList<Result> getResults(byte[] data) {
//        获取首部信息
        Head head = AnswerHead(data);
//        创建存储回答对象的结果集列表对象
        ArrayList<Result> results = new ArrayList<>();
//        获得回答数
        int count = head.getAnswers();
//解析问题
        results.add(getres(data, 1));
//        按照回答数量解析回答结果
        for (int i = 0; i < count; i++) {
            results.add(getres(data, 2));
        }
//        初始化全局变量
        index = 12;
        return results;
    }

//    解析回答
    public static Result getres(byte[] data, int c) {//c为1时解析问题部分,c为2时解析回答部分
//        创建回答结构的对象
        Result res = new Result();
        if (c == 1) {
//            创建字符串数组(保存解析后的域名和指针的变化值)
            String name[];
//            调取域名解析函数
            name = getName(data, index);
//            改变指针位置
            index += Integer.parseInt(name[1]);

            res.setName(name[0]);
//            解析type字段
            res.setType(((data[index] & 0xff) << 8) | (data[index += 1]) & 0xff);
//            解析Class字段
            res.setCs((int) data[index += 2]);
            index++;
        } else {
            String name[];
            name = getName(data, index);
            index += Integer.parseInt(name[1]);
            res.setName(name[0]);
            res.setType(((data[index] & 0xff) << 8) | (data[index += 1]) & 0xff);
            res.setCs((int) data[index += 2]);
            int TTL = ((data[index += 1] & 0xff) << 24) | ((data[index += 1] & 0xff) << 16) |
                    ((data[index += 1] & 0xff) << 8) | ((data[index += 1] & 0xff) << 0);
            res.setTTL(TTL);
            int len = ((data[index += 1] & 0xff) << 8) | ((data[index += 1] & 0xff) << 0);
            res.setLength(len);
//            根据数据长度判断需要解析的内容
            if(len==4){
                res.setData(getIP(data,index+1));
                index += 5;
            }else {
                name = getName(data,index+1);
                res.setData(name[0]);
                index += Integer.parseInt(name[1]);
            }
        }
        return res;
    }

//    域名解析
    public static String[] getName(byte[] data, int s) {
        String[] res = new String[2];
        String name = "";
        int in;
        if ((data[s] & 0xc0) == 0xc0) {
            in = ((data[s] & 0x3f) << 8) | ((data[s + 1] & 0xff) << 0);
            res[0] = getName(data, in)[0];
            res[1] = 2 + "";
        } else {
            in = s;
            while (data[in] != 0) {
                int A = data[in];
                if ((A & 0xc0) == 0xc0) {
                    String arr[] = getName(data, in);
                    name += arr[0];
                    in += Integer.parseInt(arr[1]);
                    break;
                } else {
                    int n = in + 1;
                    int B = n + A;
                    for (; n < B; n++) {
                        name += (char) data[n];
                    }
                    name += ".";
                    in += A + 1;
                }
            }
            res[0] = name;
            res[1] = in - s + 1 + "";
        }
            return res;
    }

    //IP解析
    public static String getIP(byte []data,int c){
        int i = 3;
        String IP = "";
        IP += (data[c]&0xff);
        while (i>0){
            c++;
            IP += "."+(data[c]&0xff);
            i--;
        }
        return IP;
    }
}
