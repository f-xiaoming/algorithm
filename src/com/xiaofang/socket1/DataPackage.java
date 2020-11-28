package com.xiaofang.socket1;

import java.util.ArrayList;

public class DataPackage {

    public static byte[] getHead(){
        //首部字节数组
        byte head[];
        head = new byte[12];
        //标识字段
        head[0] = (byte)0x01;
        head[1] = (byte)0x00;
        /*
        flag字段:
            QR-->1
            opcode-->0000
            AA-->0
            TC-->0
            RD-->1
            RA-->1
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
        //
        
        //设置数据包大小
        int size = head.length + question.length + 12;
        byte Package[] = new byte[size];

        //合并数组生成数据包
        System.arraycopy(head,0,Package,0,head.length);
        System.arraycopy(question,0,Package,head.length,question.length);

        //返回
        return Package;
    }
    public static byte[] getanswer(byte[] Byte,int num)
    {
    
    	byte[] buf = getHead();
    	byte[] result=new byte[Byte.length+buf.length];
  
    	for(int i=0;i<11;i++)
    	{
    		result[i]=buf[i];
    	}
        /*
        flag字段:
            QR-->1
            opcode-->0000
            AA-->0
            TC-->0
            RD-->1
            RA-->1
            Z-->000
            RCODE-->0000
        */
    	result[2] = (byte)0x81;
    	result[3] = (byte)0x80;
    	result[7] =(byte)0x01;//回答
    	result[9] =(byte)0x00;
    	result[11] =(byte)0x00;
    	for(int i=12;i<12 + num + 4;i++) {
    		result[i]=Byte[i];
    	}
    	for(int i=12 + num + 4,j = 12;i<12 + 2*num+12;i++,j++) {
    		result[i]=Byte[j];
    	}
    	result[12 + 2*num + 9] = 0x70;//TTL
    	result[12 + 2*num + 12] = 0x00;//length
    	result[12 + 2*num + 13] = 0x04;//length
    	result[12 + 2*num + 14] = (byte) 0x7f;//ip1
    	result[12 + 2*num + 15] = 0x00;//ip2
    	result[12 + 2*num + 16] = 0x00;//ip2
    	result[12 + 2*num + 17] = 0x01;//ip2
    	return result;
    }
 
}
