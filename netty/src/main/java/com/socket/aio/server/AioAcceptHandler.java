package com.socket.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerHandler> {


    @Override
    public void completed(AsynchronousSocketChannel channel, AioServerHandler serverHandler) {
        AioServer.clientCurCount++;
        AioServer.clientTotalCount++;
        System.out.println("连接的客户总数：" + AioServer.clientTotalCount + "，当前连接客户数：" + AioServer.clientCurCount);
        //重新注册监听
        serverHandler.channel.accept(serverHandler,this);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //异步读
        channel.read(byteBuffer,byteBuffer,new AioServerReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AioServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
