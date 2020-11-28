package com.xiaofang.socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketTest {
    public static void main(String[] args) throws Exception {
        // 监听指定的端口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
        while ((line = br.readLine() ) != null) {
            // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(line);
        }
        System.out.println("get message from client: " + sb);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello Client,I get the message.".getBytes("UTF-8"));
        br.close();
        outputStream.close();
        socket.close();
        server.close();
    }
}