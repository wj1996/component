package com.wj.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @RequestMapping("hello")
    public Object sayHello() {
        return "hello";
    }




}
