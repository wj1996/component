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
        /**
         * 此处代码核心意思讲解
         *      new NioEventLoopGroup()------->
         *          this(0);   --------》
         *              默认传入的线程数量为0，也就可以指定线程数量
         *               this(nThreads, (Executor) null);------》
         *                  this(nThreads, executor, SelectorProvider.provider());-----》
         *                      this(nThreads, executor, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);-----》
         *                          super(nThreads, executor, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());----》
         *                              super是MultithreadEventExecutorGroup这个抽象类，实现了EventLoopGroup接口
         *                              MultithreadEventLoopGroup-------》
         *                                  MultithreadEventLoopGroup(int nThreads, Executor executor, Object... args)-----》
         *                                      super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, args);----》
         *                                          super是MultithreadEventExecutorGroup这个抽象类，此处传入的默认为0，会创建的线程数为DEFAULT_EVENT_LOOP_THREADS个，这个数据
         *                                          有静态快加载：如下：
         *                                              static {
                                                     *     DEFAULT_EVENT_LOOP_THREADS = Math.max(1, SystemPropertyUtil.getInt(
                                                     *             "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
                                                     *
                                                     *     if (logger.isDebugEnabled()) {
                                                     *         logger.debug("-Dio.netty.eventLoopThreads: {}", DEFAULT_EVENT_LOOP_THREADS);
                                                     *        }
                                                     * }
         *                                          MultithreadEventExecutorGroup------》
         *                                              MultithreadEventExecutorGroup(int nThreads, Executor executor, Object... args)------》
         *                                                   this(nThreads, executor, DefaultEventExecutorChooserFactory.INSTANCE, args);-----》
         *                                                      判断executor是否为空，如果为空，创建一个executor
         *                                                      if (executor == null) {
                                                     *             executor = new ThreadPerTaskExecutor(newDefaultThreadFactory());
                                                     *         }
         *                                                      创建EventExecutor对象，创建多少个，传入的线程数量，默认传入为0，创建的线程个数为当前机器处理器的个数*2
         *                                                      先创建EventExecutor数组对象，申请空间
         *                                                      private final EventExecutor[] children;  定义为类变量
         *                                                      children = new EventExecutor[nThreads];
         *                                                      开始for循环创建出nThreads个EventExecutor对象
         *                                                      children[i] = newChild(executor, args);------》
         *                                                          NioEventLoopGroup------》
         *                                                              EventLoop newChild(Executor executor, Object... args)-----》
         *                                                                  return new NioEventLoop(this, executor, (SelectorProvider) args[0],
         *                                                                          ((SelectStrategyFactory) args[1]).newSelectStrategy(), (RejectedExecutionHandler) args[2]);
         *
         *
         */
        try {
            //服务器端必备ServerBootStrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /*
            一个channel只能关联一个ChannelPipeline
             */
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
