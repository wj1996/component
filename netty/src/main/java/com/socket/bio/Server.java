package com.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    private static boolean isStart = true;

    private volatile static int count = 0;

    public static void main(String[] args) {

        //入口类
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1",18888),5);
            // 等待用户连入  accept方法阻塞当前线程
            while (isStart) {
                Socket socket = serverSocket.accept();
                count++;
                System.out.println("连接成功:" + count);
                /*while (true) {

                }*/
                new Thread(new ServerTask(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ServerTask implements Runnable{

        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                System.out.println(line);
                String responseStr = "BAD REQUEST";
                if ("getTime".equals(line)) {
                    responseStr = new Date().toString();
                }
                while (true) {

                }
//                PrintWriter writer = new PrintWriter(outputStream);
////                writer.println(responseStr);
//                writer.write(responseStr);
//                writer.write("\r\n");
//                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
