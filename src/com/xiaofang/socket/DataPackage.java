package com.xiaofang.socket;

import java.util.ArrayList;
import java.util.Random;

public class DataPackage {

    public static byte[] getHead(){
        //首部字节数组
        byte head[];
        //随机数产生类
        Random random = new Random();

        head = new byte[12];
        //标识字段
        head[0] = (byte)random.nextInt(0x100);
        head[1] = (byte)random.nextInt(0x100);
        /*
        flag字段:
            QR-->0
            opcode-->0000
            AA-->0
            TC-->0
            RD-->1
            RA-->0
            Z-->000
            RCODE-->0000
        */
        head[2] = (byte)0x01;
        head[3] = (byte)0x00;
        //问题数
        head[5] = (byte)0x01;

        return head;
    }

    public static byte[] getQueries(String name,int type){
        //域名字段
        String domain[] = name.split("\\.");
        ArrayList<Byte> list = new ArrayList<>();
        for(String i:domain){
            list.add((byte)i.length());
            for(int j=0;j<i.length();j++){
                list.add((byte) i.charAt(j));
            }
        }
        list.add((byte)0x00);
        //Type字段
        if(type>255) {
            list.add((byte)(type>>8 &0xff));
            list.add((byte)(type & 0xff));
        }else {
            list.add((byte) 0x00);
            list.add((byte)(type & 0xff));
        }
        //Class字段
        list.add((byte)0x00);
        list.add((byte)0x01);
        byte arr[] = new byte[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static byte[] getPackage(String name,int type){
        //获取固定首部
        byte head[] = getHead();
        //获取问题域
        byte question[] = getQueries(name,type);
        //设置数据包大小
        int size = head.length + question.length + 12;
        byte Package[] = new byte[size];

        //合并数组生成数据包
        System.arraycopy(head,0,Package,0,head.length);
        System.arraycopy(question,0,Package,head.length,question.length);

        //返回
        return Package;
    }
}
