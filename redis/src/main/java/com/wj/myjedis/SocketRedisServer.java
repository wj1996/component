package com.wj.myjedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪的的redis服务器端（socket）
 */
public class SocketRedisServer {

    /**
     * 接收发送过来的内容
     * *3
     * $3
     * SET
     * $4
     * key1
     * $6
     * value1
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6379);
            Socket accept = serverSocket.accept();
            byte[] result = new byte[2048];
            accept.getInputStream().read(result);
            System.out.println(new String(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
