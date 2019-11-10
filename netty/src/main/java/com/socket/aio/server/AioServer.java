package com.socket.aio.server;


import static com.socket.aio.Constant.DEFAULT_PORT;

public class AioServer {

    private static AioServerHandler serverHandler;
    public volatile static long clientCurCount = 0;
    public volatile static long clientTotalCount = 0;

    public static void start() {
        if (null != serverHandler) return;
        serverHandler = new AioServerHandler(DEFAULT_PORT);
        new Thread(serverHandler,"Server").start();
    }

    public static void main(String...args) {
        AioServer.start();
    }
}
