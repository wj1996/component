package com.socket.nio;

import java.nio.ByteBuffer;

/**
 * buffer的应用固定逻辑
 * clear()
 * put（）/ get（）
 * flip 重置游标
 * SocketChannel.write(buffer)z
 */
public class TestBuffer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byte[] temp = new byte[]{11,22,33};

        /**
         * pos游标位置
         * lim 限制数量
         * cap 最大容量
         */
        System.out.println("写入数据之前：" + byteBuffer); //[pos=0 lim=1024 cap=1024]

        byteBuffer.put(temp);

        System.out.println("写入数据之后：" + byteBuffer); //[pos=3 lim=1024 cap=1024]

        //当前游标指向位置的值

        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println("读取后：" + byteBuffer);

        //重置游标 把pos的值赋给lim ,pos的值重置为0，如果lim为0的时候，去flip重置游标，此时操作这个容器，获取或者赋值都会报错
        byteBuffer.flip();

//        System.out.println(byteBuffer.get());
        /*System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());*/

        System.out.println("重置游标之后：" + byteBuffer); //[pos=3 lim=1024 cap=1024]

        for (int i = 1; i < byteBuffer.remaining(); i++) {
            int data = byteBuffer.get(i);
            System.out.println(i + " - " + data);
        }

        //看源码
//        byteBuffer.clear();
    }

}
