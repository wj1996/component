package com.wj.nio.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Buffer
 * ByteBuffer
 *
 *
 *
 */
public class BufferDemo {

    public static void base() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        //放数据
        buffer.put((byte)1);
        buffer.put((byte)2);
        buffer.put((byte)3);
        buffer.put((byte)4);
        //读数据
//        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }

    public static void base2() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.put("str".getBytes());

        System.out.println(new String(buffer.array()));
    }

    public static void base3() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        FileInputStream fileInputStream = new FileInputStream("G:/file/test.txt");
        FileChannel channel = fileInputStream.getChannel();
        int read = channel.read(buffer);
        System.out.println("1");
    }

    public static void base4() throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("G:/file/test.txt");
//        FileChannel channel = fileInputStream.getChannel();
        RandomAccessFile rw = new RandomAccessFile("G:/file/test.txt", "rw");
        FileChannel channel = rw.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        map.put((byte)1);
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        map.put((byte)2);
    }



    public static void main(String[] args) throws Exception {
//        base();
//        base2();
//        base3();
        base4();
    }
}
