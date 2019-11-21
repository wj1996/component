package com.wj.linebase;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

@ChannelHandler.Sharable /*可以在多个channel中共享，意味这个实现必须是线程安全的*/
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    //统计数量
    /*
    为了验证粘包问题，客户端发送100次，期望的是服务端收到的也是100次，可是。。。。
     */
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String str = in.toString(CharsetUtil.UTF_8);
        System.out.println("server accept " + str + ",count is " + atomicInteger.incrementAndGet());
        /*
        三种不同写的方式
            直接使用上下文写的方式和下面两种写的最大的不同是：
                上下文的写是直接找最近的一个出站处理器将数据写出去
                而下面两个都是从最后一个出站处理器一直写到最上面的那个出站处理器，写出去
                这个是根据业务情况来判断的，如果在某个入站处理器判断到了参数异常，那么久应该交给
                最近的那个出站处理器将数据写出，告知调用方法参数有问题，而不是从最后的一个出站处理器，一直调用到最近的那个
                出站处理，这样会浪费性能，可能后面的出站处理器只是用来处理业务请求的。
         */
        str += " hello world!" + System.getProperty("line.separator");
        ctx.write(Unpooled.copiedBuffer(str,CharsetUtil.UTF_8));
//        ctx.channel().writeAndFlush(in);
//        ctx.pipeline().writeAndFlush(in);
        /*
        释放资源  这样释放会很麻烦，所以在客户端的处理器继承了SimpleChannelInboundHandler，这样做的netty会自动释放
        区别：
            继承ChannelInboundHandlerAdapter这种，重写的是channelRead方法
            继承所以在客户端的处理器继承了SimpleChannelInboundHandler，重写的是channelRead0方法,可以看
            SimpleChannelInboundHandler的源码，可以发现其实底层都是在执行channelRead()方法
            SimpleChannelInboundHandler中的channelRead0方法是一个抽象方法，所有继承该类的都需要重写这个方法，
            channelRead方法实际是在调用channelRead0方法，在finally逻辑中释放
            channelRead0只是netty自己喜欢这种命名，在netty5中好像已经不再使用这种命名方式
         */
//        ReferenceCountUtil.refCnt(msg);


        /*
        ByteBuf分配

                ByteBufAllocator----->实现类（常用）
                    PooledByteBufAllocator （池化，会保持内存空间，下次接着使用,netty4中使用的就是池化）
                    UnpooledByteBufAllocator （每次申请都是一个新的内存空间）
         */
//        ByteBufAllocator alloc = ctx.alloc();
//        alloc.buffer();
//        ctx.channel().alloc();
//
//
//        Unpooled.buffer();
//        Unpooled.directBuffer(); //从直接内存申请内存空间

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);  //flush操作完毕后 关闭连接
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
