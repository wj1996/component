package com.socket.aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;

    public AioClientWriteHandler(AsynchronousSocketChannel channel, CountDownLatch latch) {
        this.channel = channel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer,byteBuffer,this);  //数据可能没写完，还让写
        } else {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            //异步读
            channel.read(readBuffer,readBuffer,new AioClientReadHandler(channel,latch));
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer byteBuffer) {
        System.out.println("数据发送失败");
        exc.printStackTrace();
        latch.countDown();
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
