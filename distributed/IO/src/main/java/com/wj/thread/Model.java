package com.wj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    List list = new ArrayList<>();
    int limit = 10;

    public static final String str = "abcdefghijklmnopqrstuvwxyz";
    public static final int length = str.length();


    public synchronized void add(String str) throws InterruptedException {
        if (list.size() == limit) {
            wait();
        }
        System.out.println("生产的数据为:" + str);
        list.add(str);
        notify();
    }

    public synchronized void get() throws InterruptedException {
        if (list.size() == 0) {
            notify();
        }
        wait();
        String str = (String) list.remove(0);
        System.out.println("消费的数据为:" + str);
    }

    class Producer extends Thread {

        private Model model;

        public Producer(Model model) {
            this.model = model;
        }

        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(new Random().nextInt(length)));
            sb.append(str.charAt(new Random().nextInt(length)));
            sb.append(str.charAt(new Random().nextInt(length)));
            sb.append(str.charAt(new Random().nextInt(length)));
            try {
                model.add(sb.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
