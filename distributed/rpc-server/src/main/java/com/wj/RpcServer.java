package com.wj;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.rmi.registry.Registry;
import java.util.concurrent.*;

public class RpcServer {

    private int port = 10000;

    public RpcServer() {

    }

    public RpcServer(int port) {
        this.port = port;
    }


    private ExecutorService executorService = null;

    public static void main(String[] args) throws IOException {
        new RpcServer().start();
    }

    class HandleConnectionTask implements Runnable {

        private Socket socket;
        public HandleConnectionTask(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                System.out.println(Thread.currentThread().getId() + "------------------");
                handleConnection(socket);
//                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void handleConnection(Socket accept) throws IOException {
            System.out.println("handle a new connection");
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                Data data = (Data) objectInputStream.readObject();
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Object obj = handleData(data);
                objectOutputStream.writeObject(obj);
            } catch (Exception e) {
                objectOutputStream.writeObject(e);
            } finally {
                objectOutputStream.flush();
            }

        }
        private Object handleData(Data data) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
            if (data == null) throw new RuntimeException("参数不能为空");
            Class<?> clazz = Class.forName(data.getClassName());
            Object o = clazz.newInstance();
            Method method = clazz.getDeclaredMethod(data.getMethodName(), data.getParamTypes());
            Object obj = method.invoke(o, data.getParamValues());
            return obj;
        }
    }


    public void init() throws IOException {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().availableProcessors() * 2,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(10));
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        registry();
        while (true) {
            Socket accept = serverSocket.accept();
            try {
                executorService.submit(new HandleConnectionTask(accept));
            } catch (Exception e) {

            }
        }
    }






    public void start() throws IOException {
        init();
    }
    private void registry() throws UnknownHostException {
        new MyRegistry().registry(RegistryInfo.builder()
                .port(port)
                .ip(getLocalIP())
                .build());
    }

    private String getLocalIP() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }


}
