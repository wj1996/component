package com.wj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.wj.service.OrderService;
import com.wj.service.PayService;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 演示异步调用（dubbo官方不建议使用）
 */
@Controller
public class CancelController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

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

    /**
     * 回声测试
     */
    @RequestMapping("test2")
    @ResponseBody
    public Map test(HttpServletRequest request) {
        String[] serviceIds = new String[]{"orderService","userService","vipUserService","payService"};
        Map<String,String> map = new HashMap<String, String>();
        Object ret = null;
        for (String id : serviceIds) {
            try {
                EchoService echoService = (EchoService) applicationContext.getBean(id);
                map.put(id,echoService.$echo("ok").toString());
            } catch (Exception e) {
                map.put(id,"not ready");
            }
        }

        return map;
    }


    @RequestMapping("test3")
    @ResponseBody
    public List test2() {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        return list;
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 泛化调用
     */
    @RequestMapping("other")
    @ResponseBody
    public String other(HttpServletRequest request) {
        GenericService genericService = (GenericService) applicationContext.getBean("otherService");
        Object obj = genericService.$invoke("test",new String[]{"java.lang.String"},new Object[]{"zhangshan"});
        return obj.toString();
    }
}
