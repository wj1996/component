package com.wj.tomcat2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTomcat {

    private int port = 8888;
    private ServerSocket serverSocket;

    public MyTomcat() {

    }

    public MyTomcat(int port) {
        this.port = port;
    }


    private void init() throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(this.port));
    }

    public void start() {
        try {
            this.init();
            //接收请求
            while (true) {
                Socket socket = this.serverSocket.accept();
                process(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            /*while (null != (line = bufferedReader.readLine())) {
                System.out.println(line);
            }*/
            OutputStream outputStream = socket.getOutputStream();
            StringBuilder sb = new StringBuilder("HTTP/1.1 200 ok\n");
            sb.append("content-type: text/html;\n");
            sb.append("\r\n");
            sb.append("hello world");
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
            System.out.println("输出数据：");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new MyTomcat().start();
    }
}
