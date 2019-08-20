package com.wj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.wj.service.OrderService;
import com.wj.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 演示异步调用（dubbo官方不建议使用）
 */
@Controller
public class CancelController {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("cancel")
    public String cancel(int orderId, HttpServletRequest request) throws InterruptedException {
        long start = System.currentTimeMillis();
        //如果设置了async为true，方法立即返回null
        String pay = payService.cancelPay(orderId);
        //只有async=true，才能得到此对象，否则为null
//        Future<String> future = RpcContext.getContext().getFuture();
        Thread.sleep(1000);
        /*try {
            pay = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        long end = System.currentTimeMillis();
        request.setAttribute("orderView",pay);
        long take = end - start;
        request.setAttribute("time",String.valueOf(take));

        return "cancel";
    }
    /**
     * 事件通知
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("order/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response,Double money) {
        String detail = orderService.getDetail("1");
        orderService.submit(money);
        request.setAttribute("order",detail);
        return "order";
    }
}
