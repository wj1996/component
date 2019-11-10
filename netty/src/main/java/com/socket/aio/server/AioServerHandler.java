package com.socket.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AioServerHandler implements Runnable{


    public CountDownLatch latch;
    public AsynchronousServerSocketChannel channel;

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public AsynchronousServerSocketChannel getChannel() {
        return channel;
    }

    public void setChannel(AsynchronousServerSocketChannel channel) {
        this.channel = channel;
    }

    public AioServerHandler(int defaultPort) {
        try {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(defaultPort));
            System.out.println("server is start ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.accept(this,new AioAcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
