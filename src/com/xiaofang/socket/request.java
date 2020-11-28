package com.xiaofang.socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class request {
    public final int PORT_NUM = 53; //协议端口

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public request(String IP, String name, int type) {
        try {

            /*** 发送数据***//**/
            datagramSocket = new DatagramSocket();
            // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
            byte[] buf = DataPackage.getPackage(name, type);

            InetAddress address = InetAddress.getByName(IP);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            // 发送数据
            datagramSocket.send(datagramPacket);

            System.out.println();

            /*** 接收数据***/
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            Head head = Answer.AnswerHead(recePacket.getData());    //解析首部

            ArrayList<Result> list = Answer.getResults(recePacket.getData()); //解析问题

            System.out.println("响应首部:");
            System.out.println(head);

            System.out.println("问题部分:");
            System.out.println(list.get(0));

            System.out.println("回答部分:");
            for(int i=1;i<list.size();i++){
                System.out.println(list.get(i));
            }

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
    }

    public static void main(String[] args) {
        String ip, name, type = "1";
        //Scanner input = new Scanner(System.in);
        // System.out.println("输入目的IP:");
        // ip = input.next();
        // System.out.println("输入域名:");
        // name = input.next();
        int i;
        long stratTime=System.currentTimeMillis();
        for (i = 0; i < 200; i++) {
            new request(ip = "202.196.64.1", name = "www.zzu.edu.cn", 1);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("用时"+(endTime-stratTime)+"ms");
    }
}