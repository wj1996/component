package com.wj.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netyy
 */
public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port,String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
        //线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            /**
             * 客户端启动必备
             * 使用建造者模式
             */
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)  //指明使用NIO通讯模式
                    .remoteAddress(host,port) //远程服务器配置
                    .handler(new EchoClientHandler()); /*
                指定handler
                    此处的继承SimpleChannelInboundHandler<I>
                    Netty中使用的数据对象是ByteBuf  注意：jdk原生的使用的是ByteBuffer
             */
                    /*

                     */
            ChannelFuture channelFuture = bootstrap.connect().sync();//连接到远程阻塞 sync 直到连接成功

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) {
        try {
            new EchoClient(9999,"localhost").start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

