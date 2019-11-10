package com.socket.bio;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket("127.0.0.1",18888);
                            System.out.println("成功");
                            OutputStream outputStream = socket.getOutputStream();
                            PrintWriter writer = new PrintWriter(outputStream);
                            writer.println("getTime");
                            writer.flush();
                            InputStream inputStream = socket.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            System.out.println(bufferedReader.readLine());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
    }
}
