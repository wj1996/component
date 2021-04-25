package com.wj.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BioClient {


    private Socket socket;

    private BioClient(int port,String ip) throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress(port));
        System.out.println("连接成功.....");
    }
    public void send() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("hello,i am a bio client");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            System.out.println("receive : " + s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new BioClient(8002,null).send();
    }


}
