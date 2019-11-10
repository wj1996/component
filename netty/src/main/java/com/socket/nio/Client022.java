package com.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 错误写法
 */
public class Client022 {

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",8888));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            /*while (!socketChannel.finishConnect()) {

            }*/
            while (true) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isValid()) {
                        if (selectionKey.isConnectable()) {
                            System.out.println("连接成功");
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            channel.configureBlocking(false);
                            System.out.println("请输入数据");
                            String line = new Scanner(System.in).nextLine();
                            channel.configureBlocking(false);
                            byte[] data = line.getBytes();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
                            byteBuffer.put(data);
                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            channel.register(selector,SelectionKey.OP_READ);
                        }

                        if (selectionKey.isWritable()) {
                            System.out.println("请输入数据");
                            String line = new Scanner(System.in).nextLine();
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            channel.configureBlocking(false);
                            byte[] data = line.getBytes();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(data.length);
                            byteBuffer.put(data);
                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            channel.register(selector,SelectionKey.OP_READ);
                        }

                        if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            channel.configureBlocking(false);
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int read = channel.read(byteBuffer);
                            if (read > 0) {
                                byteBuffer.flip();
                                byte[] data = new byte[byteBuffer.remaining()];
                                System.out.println("响应的数据为：" + new String(data,"utf-8"));
                                channel.register(selector,SelectionKey.OP_WRITE);
                            }
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
