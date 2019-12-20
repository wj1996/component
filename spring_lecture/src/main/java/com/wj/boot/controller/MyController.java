package com.wj.boot.controller;

import com.wj.boot.config.MyConfigBean;
import com.wj.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "api",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MyController {

    @Value("${myconfig.myobject.myname}")
    private String myname;
    @Value("${myconfig.myobject.myage}")
    private String myage;

    @Autowired
    private MyConfigBean myConfigBean;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public Object test() {
        System.out.println(myage + "," + myname);
        System.out.println(myConfigBean.getMyage() + "," + myConfigBean.getMyname());
        Person person = new Person();
        person.setAge(12);
        person.setBirthday(new Date());
        person.setName("张三");
        return person;
    }

}
