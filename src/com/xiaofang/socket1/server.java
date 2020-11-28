package com.xiaofang.socket1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class server {
    public final int PORT_NUM = 53; //协议端口
    
    private DatagramSocket datagramSocket;
    private DatagramSocket datagramSocket1;
    private DatagramPacket datagramPacket;
    public void s()
    {
    	 try {
    		 datagramSocket = new DatagramSocket(PORT_NUM);
             System.out.println("服务器已开启...等待请求");
             /*** 接收数据***/
             byte[] receBuf = new byte[1024];

             InetAddress address = InetAddress.getByName("127.0.0.1");
             
             DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
             datagramSocket.receive(recePacket);
             System.out.println("接收到请求..");
             int num = 0;
             int i = 12;
             while(true) {
            	 if(receBuf[i] != 0) {
            		 num = num + receBuf[i] + 1;
            		 i = i + receBuf[i] + 1;
            	 }
            	 else {
            		 num++;
            		 break;
            	 }
            		 
             }
             

             /*** 发送数据***//**/
             
             // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
             byte[] buf = DataPackage.getanswer(receBuf,num);
             datagramPacket = new DatagramPacket(buf, buf.length, address,recePacket.getPort());
             // 发送数据
             
             datagramSocket.send(datagramPacket);
           

         } catch (SocketException e) {
             e.printStackTrace();
         } catch (UnknownHostException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             // 关闭socket
             if (datagramSocket != null) {
                 datagramSocket.close();
             }
         }
        System.out.println("服务器已解析....");
    }
       public static void main(String[] args)
       {
    	   while(true) {
    	    	  server s=new server();
    	    	  s.s();
    	   }
       }
}
