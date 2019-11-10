package com.socket.aio.client;


import java.io.IOException;
import java.util.Scanner;

import static com.socket.aio.Constant.DEFAULT_PORT;
import static com.socket.aio.Constant.DEFAULT_SERVER_IP;


public class AioClient {

    private static AioClientHandler clientHandler;

    public static void start() {
        if (null != clientHandler) {
            return;
        }

        clientHandler = new AioClientHandler(DEFAULT_SERVER_IP,DEFAULT_PORT);
        new Thread(clientHandler,"Client").start();
    }

    public static boolean sendMsg(String msg) throws Exception {
        if ("q".equals(msg)) return stop();
        clientHandler.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws Exception {
        AioClient.start();
        System.out.println("请输入请求消息");
        Scanner scanner = new Scanner(System.in);
        while (AioClient.sendMsg(scanner.nextLine()));
    }


    public static boolean stop() {
        try {
            clientHandler.getLatch().countDown();
            clientHandler.getChannel().close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            AioServer.clientCurCount--;
        }
        return false;
    }

}
