package com.wj;

public class Demo03 {

    private static  Object object = new Object();

    private static long waitTime = 1000;



    public void test() {

        String str = null;

        int retryNum = 0;

        while (null == str) {
            synchronized (object) {
                try {
                    System.out.println("开始等待了");
                    object.wait(waitTime);
                    System.out.println("等待结束了");
                    retryNum++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (retryNum == 4) {
                    throw new RuntimeException("还没有获取到");
                }

            }

        }
    }


    public static void main(String[] args) {
        new Demo03().test();
    }


}
