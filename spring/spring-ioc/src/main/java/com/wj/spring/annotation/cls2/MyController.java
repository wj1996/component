package com.wj.spring.annotation.cls2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.inject.Inject;

@Controller
public class MyController {

//    @Autowired
////    @Qualifier("myService2")
//    private MyService myService;


    /*
    * @Resource和@Autowired作用是一样的
    * 不支持@Primary
    * 没有@Autowired的required=false这个功能
    * */
//    @Resource
//    @Autowired

    /*
    * 需要引入多余的包，和@Autowired差不多，支持@Primary ，建议使用@Autowired，只是没有@Autowired的required=false这个功能
    * */
    @Inject
    private MyService myService;


    public void test() {
        System.out.println("myController:" + myService);
        myService.test();
    }
}
