package com.wj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {


    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private ServerSocketChannel serverSocketChannel;
    private int port;

    public static void main(String[] args) {
        new NioServer(8002).listen();
    }

    public NioServer(int port) {
        try {
            this.port = port;
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            //NIO模型默认采用阻塞模式
            serverSocketChannel.configureBlocking(false);
            this.selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("listen port:" + this.port);
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    try {
                        process(selectionKey);
                    } catch (Exception e) {

                    }

                    iterator.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void process(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);

        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            int len = socketChannel.read(byteBuffer);
            if (len > 0) {
                byteBuffer.flip();
                String content = new String(byteBuffer.array(),0,len);
                System.out.println("读取的内容为:" + content);
                SelectionKey key = socketChannel.register(selector, SelectionKey.OP_WRITE);
                key.attach(content);
            }

        } else if (selectionKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String attachment = (String) selectionKey.attachment();
            socketChannel.write(ByteBuffer.wrap(attachment.getBytes()));
        }
    }



}
