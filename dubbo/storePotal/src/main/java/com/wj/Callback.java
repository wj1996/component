package com.wj;

/**
 * 回调
 * 实现的入口：FutureFilter
 */
public class Callback {

    /**
     * 第一个参数为方法返回值
     * 第二参数为请求参数
     * @param result
     * @param money
     */
    public void onOrderSubmit(Object result,double money) {
        System.out.println("生成了订单，金额：" + money);
    }

    /**
     * 第一个参数是异常
     * 第二参数是请求参数
     * @param e
     */
    public void onError(Throwable e) {
        System.out.println("订单处理异常，订单金额：");
        System.out.println(e.getMessage());
    }
}
