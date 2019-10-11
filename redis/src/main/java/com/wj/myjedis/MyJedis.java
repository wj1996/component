package com.wj.myjedis;

import java.io.IOException;
import java.net.Socket;

public class MyJedis {

    /**
     *     *3
     *      * $3
     *      * SET
     *      * $4
     *      * key1
     *      * $6
     *      * value1
     * @param socket
     * @param key
     * @param value
     * @return
     */
    public static String set(Socket socket, String key, String value) throws IOException {
        StringBuffer str = new StringBuffer();
        str.append("*3").append("\r\n");
        str.append("$3").append("set").append("\r\n");
        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append(key).append("\r\n");
        str.append("$").append(value.getBytes().length).append("\r\n");
        str.append(value).append("\r");

        //向redis发送resp
        socket.getOutputStream().write(str.toString().getBytes());
        byte[] resp = new byte[2048];
        socket.getInputStream().read(resp);

        return new String(resp);
    }

    public static String get(Socket socket,String key) throws IOException {
        StringBuffer str = new StringBuffer();
        str.append("*3").append("\r\n");
        str.append("$3").append("get").append("\r\n");
        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append(key).append("\r");

        socket.getOutputStream().write(str.toString().getBytes());
        byte[] resp = new byte[2048];
        socket.getInputStream().read(resp);

        return new String(resp);
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",6379);
        String set = set(socket, "test1", "value1");
        System.out.println(set);
    }

}
