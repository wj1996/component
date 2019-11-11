package com.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Demo01 {




}


class Server {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    /**
     * 启动服务器
     */
    public void start() throws IOException, InterruptedException {

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",18888));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动。。。。。。。");
        execute(selector);
    }


    private void execute(Selector selector) throws IOException {
        while (true) {
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isValid()) {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        System.out.println("服务器已经连接客户端");
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }


                    /*if (selectionKey.isConnectable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("客户端已经连接");
                        serverSocketChannel.register(selector,SelectionKey.OP_READ);
                    }*/

                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        System.out.println("服务器开始读取数据");
                        ByteBuffer bBuffer = ByteBuffer.allocate(2);
                        int read = socketChannel.read(bBuffer);
                        bBuffer.flip();
                        if (read > 0) {
                            byte[] bytes = new byte[bBuffer.remaining()];
                            bBuffer.get(bytes);
                            System.out.println("读取到的数据为：" + new String(bytes,"utf-8"));
                            //服务器端的响应
                            socketChannel.register(selector,SelectionKey.OP_WRITE);
                        }

                    }

                    if (selectionKey.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        System.out.println("服务器开始处理客户端请求的逻辑");
                        handle(socketChannel);
                    }



                }
            }

        }
    }


    private void handle(SocketChannel socketChannel) throws IOException {
        ByteBuffer bBuffer = null;
//        String type = new String(bBuffer.array());
        String type = "";
        String result = "";
        socketChannel.register(selector,SelectionKey.OP_READ);
        if ("1".equals(type)) {
            System.out.println("1对应的操作为：查询天气");
            result = "今天杭州的天气为晴天";
            bBuffer = ByteBuffer.allocateDirect(result.getBytes("utf-8").length);
            bBuffer.put(result.getBytes());
            bBuffer.flip();
            socketChannel.write(bBuffer);
            return;
        }

        if ("2".equals(type)) {
            System.out.println("2对应的操作为：查询成绩");
            result = "您的成绩为优秀";
            bBuffer = ByteBuffer.allocateDirect(result.getBytes("utf-8").length);
            bBuffer.put(result.getBytes());
            bBuffer.flip();
            socketChannel.write(bBuffer);
            return;
        }

        System.out.println("操作类型不合法");
        result = "操作类型不合法";
        bBuffer = ByteBuffer.allocateDirect(result.getBytes("utf-8").length);
        bBuffer.put(result.getBytes());
        bBuffer.flip();
        socketChannel.write(bBuffer);

    }
}


class Client {

    private SocketChannel socketChannel;
    private Selector selector;


    public Client () throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",18888));
        socketChannel.register(selector,SelectionKey.OP_CONNECT);
    }


    public void start() throws IOException {
        execute();
    }


    private void execute() throws IOException {
        while (true) {
            selector.select(1000);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isValid()) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if (!channel.finishConnect()) {
                            System.exit(1);
                        }
                        System.out.println("客户端已经连接上服务器");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    System.out.println("服务器端输入数据：");
                                    String type = new String(new Scanner(System.in).nextLine());
                                    try {
                                        ByteBuffer bBuffer = ByteBuffer.allocate(type.getBytes("utf-8").length);
                                        bBuffer.put(type.getBytes("utf-8"));
                                        bBuffer.flip();
                                        socketChannel.write(bBuffer);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        socketChannel.register(selector,SelectionKey.OP_READ);

                    }

                    if (selectionKey.isReadable()) {
                        System.out.println("从服务器端获取响应数据");
                        ByteBuffer bBuffer = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(bBuffer);
                        bBuffer.flip();
                        if (read > 0) {
                            //这样写也可以读取到数据
                            System.out.println("读取到的数据为：" + new String(bBuffer.array()));
                            bBuffer.flip();
                        }
                    }
                }
            }
        }
    }


}

















