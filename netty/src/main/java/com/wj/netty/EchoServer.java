package com.wj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(9999).start();
    }

    public void start() throws InterruptedException {
        //线程组
        final EchoServerHandler handler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //服务器端必备ServerBootStrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)   //指定通讯模式
                    .localAddress(new InetSocketAddress(port)) //配置监听端口
                    /*
                    接收到连接请求，新启动一个socket通信，也就是channel，每个channel都有自己事件的handler
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                            /*
                            如果 handler不是@ChannelHandler.Sharable这样的，这些写，启动多个客户端时，就会有问题。
                             */
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind().sync();  //绑定端口
            /**
             * bind（）方法解析
             *
             */
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully().sync(); //注意：服务器端，此处要有
        }
    }
}
