package com.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client021 {

    public static void main(String[] args) {
        InetSocketAddress remote = new InetSocketAddress("127.0.0.1",8888);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            //连接远程服务器
            socketChannel.connect(remote);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("exit".equals(line)) break;
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put(line.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);

                byteBuffer.clear();
                int readLength = socketChannel.read(byteBuffer);
                if (readLength == -1) break;
                byteBuffer.flip();
                byte[] datas = new byte[byteBuffer.remaining()];
                byteBuffer.get(datas);
                System.out.println(new String(datas,"utf-8"));
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
