package com.wj.test;

import com.wj.Data;
import com.wj.MyRegistry;
import com.wj.RegistryInfo;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ClientTest {

    @Test
    public void testConnection() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(10000));
        System.out.println("connection successs...");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("hello i am a client");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("receive：" + bufferedReader.readLine());
    }

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName("com.wj.service.HelloWorldService");
        Object o = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("doService", String.class,int.class);
        method.invoke(o,"I am a boy",12);
    }

    @Test
    public void testInvoke() throws IOException, ClassNotFoundException {



        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(10000));
        System.out.println("connection successs...");
        Data data = Data.builder()
                .className("com.wj.service.HelloWorldService")
                .methodName("doService")
                .paramTypes(new Class[]{String.class,int.class})
                .paramValues(new Object[]{"I am a boy",12})
                .build();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object o = objectInputStream.readObject();
        System.out.println(o);
    }

    @Test
    public void multiThreadInvoke() throws InterruptedException {
        int n = 10;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0 ; i < n ; i++) {
           new Thread (() -> {
               try {
                   Socket socket = new Socket();
                   socket.connect(new InetSocketAddress(10000));
                   System.out.println("connection successs...");
                   Data data = Data.builder()
                           .className("com.wj.service.HelloWorldService")
                           .methodName("doService")
                           .paramTypes(new Class[]{String.class,int.class})
                           .paramValues(new Object[]{"I am a boy",new Random().nextInt(10000)})
                           .build();
                   ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                   objectOutputStream.writeObject(data);
                   objectOutputStream.flush();
                   ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                   Object o = objectInputStream.readObject();
                   System.out.println(o);

               } catch (Exception e) {

               } finally {
                   countDownLatch.countDown();
               }
           }).start();
        }

        countDownLatch.await();
    }

    @Test
    public void test2() throws Exception {
        /*List<RegistryInfo> server = new MyRegistry().findSever("server");

       *//* server.forEach(registryInfo ->  {
            System.out.println(registryInfo);
        });*//*
        RegistryInfo registryInfo = server.get(0);
        Socket socket = new Socket(registryInfo.getIp(),registryInfo.getPort());
        System.out.println("connection successs...");
        Data data = Data.builder()
                .className("com.wj.service.HelloWorldService")
                .methodName("doService")
                .paramTypes(new Class[]{String.class,int.class})
                .paramValues(new Object[]{"I am a boy",new Random().nextInt(10000)})
                .build();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object o = objectInputStream.readObject();
        System.out.println(o);*/
    }


    @Test
    public void testRandom() {
        //10 - 20
       /* for (int i = 0; i < 10; i++) {
            System.out.println((int)(10 * Math.random()) + 10);
        }*/
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(10);
            System.out.println(num + 10);
        }
    }

}
