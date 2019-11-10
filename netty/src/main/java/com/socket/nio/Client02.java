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

public class Client02 {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (!socketChannel.finishConnect()) {};
            System.out.println("连接成功");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("请输入数据：");
                    while (true) {
                        try {
                            String line = new Scanner(System.in).nextLine();
                            byte[] bytes = line.getBytes();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
                            byteBuffer.put(bytes);
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            while (true) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isValid()) {
                        if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            System.out.println("接收到参数：" + new String(bytes,"utf-8"));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
