package com.wj01.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Shareable注解代表当前Handler是一个可以分享的处理器，服务注册此Handler后，可以分享给多个客户端同时使用
 * 如果不使用注解描述类型，则每次客户端请求时，必须为客户端重新创建一个新的Handler对象
 * 如果Handler是一个Shareable，注意线程安全问题
 *  bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
 *             protected void initChannel(SocketChannel socketChannel) throws Exception {
 *                 socketChannel.pipeline().addLast(new XxxHanlder());
 *             }
 *         });
 */
@ChannelHandler.Sharable
public class ServerHelloWorldHandler extends SimpleChannelInboundHandler {

    /**
     * @param ctx  上下文对象，其中包含客户端建立连接的所有资源
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //msg 获取到的数据，是一个换冲
        ByteBuf readBuff = (ByteBuf) msg;
        byte[] tempDatas = new byte[readBuff.readableBytes()];
        readBuff.readBytes(tempDatas);
        String message = new String(tempDatas,"utf-8");
        System.out.println("from client " + message);
        if ("exit".equals(message)) {
            ctx.close();
            return;
        }

        String line = "server message to client";
        //写操作自动释放内存，避免内存溢出问题
        ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes()));
        //注意：如果调用的是write方法，不会刷新缓冲，缓冲中的数据不会发送到客户端，必须再次调用flush方法
//        ctx.flush();
    }
}
