package com.wj;

/**
 * 回调
 */
public class Callback {

    public void onOrderSubmit(double money) {
        System.out.println("生成了订单，金额：" + money);
    }

    public void onError(double money,Throwable e) {
        System.out.println("订单处理异常，订单金额：" + money);
        System.out.println(e.getMessage());
    }
}
