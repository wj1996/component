package com.wj.thread;


import java.util.concurrent.*;

public class MyListener {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                System.out.println("hello world");
                return "hello world";
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(futureTask);
        System.out.println("2");
        System.out.println(futureTask.get());
        System.out.println("1");
    }

}

