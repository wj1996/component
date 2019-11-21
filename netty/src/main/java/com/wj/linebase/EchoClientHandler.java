package com.wj.linebase;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 客户端接收到数据后的操作
     * 每一个ChannelHandler都会有一个ChannelHandlerContext上下文对象
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client accept " + msg.toString(CharsetUtil.UTF_8) + ",count is " + atomicInteger.incrementAndGet());

    }

    /**
     * 客户端被通知channel活跃以后
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*
            所谓的池化技术是什么？？？？
         */
        String str = "james,tom,jetty,tomcat,lison,lucy,judy" + System.getProperty("line.separator");
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(str,CharsetUtil.UTF_8));
        }

    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
