package com.socket.aio.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;

    public AioClientReadHandler(AsynchronousSocketChannel channel, CountDownLatch latch) {
        this.channel = channel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer byteBuffer) {
        byteBuffer.flip();  //开始读了，翻转，也就是让flip之后的position到limit这块区域变成之前的0到position这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        try {
            String msg = new String(bytes,"utf-8");
            System.out.println("msg:" + msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer byteBuffer) {
        System.out.println("数据读取失败");
        exc.printStackTrace();
        latch.countDown();
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
