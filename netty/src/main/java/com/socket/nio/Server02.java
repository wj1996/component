package com.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server02 {


    public static void main(String[] args) {
        try {
            //多路复用器，选择器，用于注册通道的   开启多路复用器
            Selector selector = Selector.open();
            //开启服务通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            /**
             * 这里的通道和SocketChannel socketChannel = channel.accept(); 这里的通道的区别是什么？？？？？
             *
             * 通过debug模式来观察
             *      开启的服务通道serverSocketChannel为：
             *          fd：1280
             *      执行绑定后，可以发现这个通道监听的端口为8888，地址为localhost
             *
             *      通过accept接收产生的SocketChannel为：
             *          fd：1324
             *          使用的端口是8888
             *          远程连接的端口为50624
             *
             *
             *
             */

            //设置通道非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定连接 监听端口
            serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
            //注册
            /**
             * 状态编码
             *      OP_ACCEPT   连接成功的标记位
             *      OP_READ     可以读取数据的标志位
             *      OP_WRITE    可以写入数据集的标志位
             *      OP_CONNECT  建立连接后的标记
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务启动");
            while (true) {
                //阻塞方法，至少一个通道被选中，此方法返回
                //通道是否选中，由注册到多路复用器中的通道标记决定
                selector.select(1000);
                //返回已选中的通道标记集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
//                System.out.println("重新获取选择器");
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        if (selectionKey.isValid()) {
                            if (selectionKey.isAcceptable()) {
                                System.out.println("服务连接成功");
                                ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                                //阻塞方法，当客户端发起请求后返回
                                SocketChannel socketChannel = channel.accept();
                                /***
                                 *  服务器和客户端建立连接，服务器接收客户端，获取到客户端的SocketChannel信息
                                 *  在自己连接新建一个Socket对象，包含客户端的信息（ip+端口）
                                 *  注意此处：这里和客户端是不一样的，此处建立连接后，服务器端的serverSocketChannel不会发生任何改变，不会将远程客户端的信息写入这个对象中
                                 *
                                 */

                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector,SelectionKey.OP_READ);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("请想客户端发送消息");
                                        while (true) {
                                            try {
                                                String msg = new Scanner(System.in).nextLine();
                                                ByteBuffer byteBuffer = ByteBuffer.allocate(msg.getBytes().length);
                                                byteBuffer.put(msg.getBytes());
                                                byteBuffer.flip();
                                                socketChannel.write(byteBuffer);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }).start();
                            }
                            if (selectionKey.isReadable()) {
                                System.out.println("服务器开始读取数据");
                                SocketChannel channel = (SocketChannel) selectionKey.channel();

                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);   //这里如果分配的大小不足以写入所有客户端的数据时，会等待一次次写入
                                //将通道中的数据读取到缓存中，通道中的数据就是客户端发送给服务器的数据

                                /**
                                 * 流程分析
                                 *  channel.read(byteBuffer);
                                 *      -> SocketChannelImpl
                                 *          var3 = IOUtil.read(this.fd, var1, -1L, nd);
                                 *              -> IOUtil
                                 *                  int var6 = readIntoNativeBuffer(var0, var5, var2, var4);
                                 *                      readIntoNativeBuffer
                                 *                          var9 = var4.read(var0, ((DirectBuffer)var1).address() + (long)var5, var7);   -> var4 ----->SocketDispatcher
                                 *                              -> SocketDispatcher
                                 *                                  read0(var1, var2, var4)
                                 *                                      调用native方法
                                 *                                      static native int read0(FileDescriptor var0, long var1, int var3) throws IOException;
                                 *
                                 *                                      翻译openjdk这里的源代码
                                 *                                          Java_sun_nio_ch_SocketDispatcher_read0(JNIEnv *env, jclass clazz, jobject fdo,
                                 *                                       jlong address, jint len)
                                 *                                          见下，使用Windows的socket套接字编程，首先是将数据读取到定义的buffers中
                                 *                                          然后，调用函数return convertReturnVal(env, (jint)read, JNI_TRUE); 将数据返回、
                                 *
                                 *
                                 *
                                 *
                                 *   经过测试可以发现的状况是：
                                 *          如果发送数据大小超过了读取时定义的ByteBuffer的大小时，会去读取多次，将数据全部读取到。
                                 */
                                /* read into the buffers */
                                /*i = WSARecv((SOCKET)fd, *//* Socket *//*
                                        &buf,           *//* pointers to the buffers *//*
                                        (DWORD)1,       *//* number of buffers to process *//*
                                        &read,          *//* receives number of bytes read *//*
                                        &flags,         *//* no flags *//*
                                        0,              *//* no overlapped sockets *//*
                                        0);            */ /* no completion routine */

                                int readLength = channel.read(byteBuffer);
                                if (readLength > 0) {
                                    //flip NIO中最复杂的操作就是Buffer的控制
                                    /**
                                     * Buffer中有一个游标，游标信息在操作后不会归零。如果直接访问Buffer的话，数据有不一致的可能。
                                     * flip是重置游标的方法。在NIO编程中，flip方法是常用方法
                                     */
                                    byteBuffer.flip();
                                    //字节数组，保存具体数据  byteBuffer.reamining方法是获取Buffer中有效数据长度
                                    byte[] bytes = new byte[byteBuffer.remaining()];
                                    //将buffer中的数据保存到bytes字节数组中
                                    byteBuffer.get(bytes);
                                    String requestStr = new String(bytes,"utf-8");
                                    System.out.println("请求的数据来源：" + channel.getRemoteAddress() +",请求的数据为：" + requestStr);
                                    String respStr = "哈哈";
                                    if ("1".equals(requestStr)) {
                                        respStr = "hello world";
                                    } else if ("2".equals(requestStr)) {
                                        respStr = "once complie";
                                    }

                                   /* System.out.println("服务器写数据");
                                    respStr = new Scanner(System.in).nextLine();
                                    byte[] writeBytes = respStr.getBytes();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
                                    writeBuffer.put(writeBytes);
                                    writeBuffer.flip();
                                    SocketChannel writeChannel = (SocketChannel) selectionKey.channel();
                                    writeChannel.write(writeBuffer);*/
                                    //注册写通道
//                                    channel.register(selector,SelectionKey.OP_WRITE);
                                } else {
                                    selectionKey.channel().close();
                                    selectionKey.cancel();
                                }

                            }

                            /*if (selectionKey.isWritable()) {
                                System.out.println("服务器端响应数据：");
                                SocketChannel channel = (SocketChannel) selectionKey.channel();
                                String line = new Scanner(System.in).nextLine();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(line.getBytes().length);
                                byteBuffer.put(line.getBytes());
                                byteBuffer.flip();
                                channel.write(byteBuffer);
                                channel.register(selector,SelectionKey.OP_READ);
                            }*/
                        }
                    } catch (Exception e) {
                        selectionKey.cancel(); //端开连接
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
