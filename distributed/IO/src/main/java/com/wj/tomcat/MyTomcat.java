package com.wj.tomcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class MyTomcat {

    private int port = 8888;
    private ServerSocket serverSocket;
    public MyTomcat() {

    }
    public MyTomcat(int port) {
        this.port = port;
    }
    private void init() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));

    }

    public void start() throws IOException {
        init();
    }




}
