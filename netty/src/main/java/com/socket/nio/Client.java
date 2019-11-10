package com.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client implements Runnable{

    private SocketChannel socketChannel;
    private Selector selector;

    public Client() {
        try {
            this.socketChannel = SocketChannel.open();
            this.selector = Selector.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {

            SocketAddress socketAddress = new InetSocketAddress("localhost",8888);
            if (socketChannel.connect(socketAddress));    //去连接服务器
//            else socketChannel.register(selector, SelectionKey.OP_CONNECT);

            /**
             * 对于SocketChannel来说，有效的事件OP_CONNECT,OP_READ,OP_WRTIE
             * 对于ServerSocketChannel来说，有效的事件OP_ACCEPT
             */
            socketChannel.register(selector,SelectionKey.OP_CONNECT);

            /*while (!socketChannel.finishConnect()) {   //判断是否已经连接完成，如果没有连接完成，那么久循环等待
                ;
            }*/
//            System.out.println("连接成功");
            /**
             * socketChannel
             *      执行connect后，会标记远程的连接为：127.0.0.1:8888
             *      fd:1296
             */
            while (true) {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    if (key.isValid()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        /**
                         * 当连接成功后，相当于本地会有一个socket启动，来跟远程服务器连接，客户端的socket端口为60624，此处的channel跟socketChannel完全一模一样的
                         * 此处相当于是完全连接成功
                         * fd:1296
                         */
                        if (key.isConnectable()) {
                            if (channel.finishConnect());    //这一步完成后，才标记本地的信息：（ip+端口）
                            else System.exit(1);
                            System.out.println("连接成功");
                            channel.register(selector,SelectionKey.OP_READ);
                        }


                        if (key.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int readBytes = channel.read(byteBuffer);
                            if (readBytes > 0) {
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                byteBuffer.get(bytes);
                                String result = new String(bytes,"utf-8");
                                System.out.println("客户端收到消息" + result);
                            }
//                            channel.register(selector,SelectionKey.OP_WRITE);
                        }

                        /*if (key.isWritable()) {
                            System.out.println("开始写数据：");
                            String msg = new Scanner(System.in).nextLine();
                            ByteBuffer bBuffer = ByteBuffer.allocate(msg.getBytes().length);
                            bBuffer.put(msg.getBytes());
                            bBuffer.flip();
                            channel.write(bBuffer);
                            channel.register(selector,SelectionKey.OP_READ);
                        }*/


                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        Client client = new Client();
        new Thread(client).start();
        System.out.println("输入信息：");
        while (isQuit(new Scanner(System.in).nextLine(),client.socketChannel,client.selector)) {

        }
    }

    public static boolean isQuit(String msg,SocketChannel socketChannel,Selector selector) throws IOException {
        if ("quit".equals(msg)) return false;
        sendMsg(msg,socketChannel,selector);
        return true;
    }

    public static void sendMsg(String msg,SocketChannel socketChannel,Selector selector) throws IOException {
//        socketChannel.register(selector,SelectionKey.OP_READ);
        doWrite(socketChannel,msg);

    }

    private static void doWrite(SocketChannel channel,String request) throws IOException{
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }


}
