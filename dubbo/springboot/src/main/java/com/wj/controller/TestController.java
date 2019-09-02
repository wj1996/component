package com.wj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class TestController {


    @RequestMapping(value = "test")
    @ResponseBody
    public String test(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                String header = request.getHeader(s);
                System.out.println(s + ">" + header);
            }
        }

        return "";
    }
}
