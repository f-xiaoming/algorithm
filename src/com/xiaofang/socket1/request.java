package com.xiaofang.socket1;

import com.xiaofang.socket.Answer;
import com.xiaofang.socket.Head;
import com.xiaofang.socket.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class request {
    public final int PORT_NUM = 53; //协议端口

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public request(String IP,String name,int type) {
        try {

            /*** 发送数据***//**/
            datagramSocket = new DatagramSocket();
            // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
            byte[] buf = DataPackage.getPackage(name,type);
            InetAddress address = InetAddress.getByName(IP);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            // 发送数据
            datagramSocket.send(datagramPacket);

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

    //界面类
    static class view extends JFrame implements ActionListener {
        JPanel jp1,jp2,jp3,jp4;
        JLabel jl1,jl2,jl3;
        JTextField IP,name,type;
        JButton send;
        public view(){
            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4 = new JPanel();

            jl1 = new JLabel("输入目的IP:");
            jl2 = new JLabel("输入域名:");
            jl3 = new JLabel("输入type:");

            IP = new JTextField();
            IP.setPreferredSize(new Dimension(160,30));
            IP.setText("127.0.0.1");
            name = new JTextField();
            name.setPreferredSize(new Dimension(160,30));
            name.setText("www15.zzu.edu.cn");
            type = new JTextField();
            type.setPreferredSize(new Dimension(160,30));
            type.setText("1");

            send = new JButton("发送");
            send.addActionListener(this);

            this.setLayout(new GridLayout(5,1));

            jp1.add(jl1);
            jp1.add(IP);

            jp2.add(jl2);
            jp2.add(name);

            jp3.add(jl3);
            jp3.add(type);

            jp4.add(send);

            this.add(jp1);
            this.add(jp2);
            this.add(jp3);
            this.add(jp4);
            this.add(new JLabel("                     type可输入多个中间以空格分开"));

            this.setTitle("数据包发送");//窗体标签
            this.setSize(300, 300);//窗体大小
            this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
            this.setVisible(true);//显示窗体
        }

//        点击事件监听函数
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "发送") {
                String ip = IP.getText();
                String Ynmae = name.getText();
                String types[] = type.getText().split(" ");
                for(String i:types) {
                    new request(ip, Ynmae,Integer.parseInt(i));
                }
            }
        }
    }
//    主函数运行程序
    public static void main(String[] args) {
        new view();
}
}
