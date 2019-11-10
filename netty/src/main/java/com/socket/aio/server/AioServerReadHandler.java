package com.socket.aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioServerReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public AioServerReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if (-1 == result) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
//        System.out.println(result);
        try {
            String msg = new String(bytes,"utf-8");
            System.out.println("server accept " + msg);
            String repMsg = "哈哈";
            write(repMsg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }

    public void write(String msg) {
        byte[] bytes = msg.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (byteBuffer.hasRemaining()) {
                    channel.write(byteBuffer,byteBuffer,this);  //数据可能没写完，还让写
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //异步读
                    channel.read(readBuffer,readBuffer,new AioServerReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
