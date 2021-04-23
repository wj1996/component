package com.wj.service;

import com.wj.rpc.client.RpcInvoke;

public class MyService {

    public void test() throws Exception {

        IHelloSV sv = new RpcInvoke<IHelloSV>().getSV(IHelloSV.class);
        System.out.println(sv.doService("hello world"));
    }

    public static void main(String[] args) throws Exception {
        MyService myService = new MyService();
        for (int i = 0 ; i < 50; i++) {
            myService.test();
            Thread.sleep(3000);
        }
    }





}
