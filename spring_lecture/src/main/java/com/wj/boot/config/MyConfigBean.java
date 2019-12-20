package com.wj.boot.config;

import org.springframework.beans.factory.annotation.Value;

public class MyConfigBean {

    @Value("${myconfig.myobject.myname}")
    private String myname;
    @Value("${myconfig.myobject.myage}")
    private String myage;


    public String getMyname() {
        return myname;
    }

    public String getMyage() {
        return myage;
    }
}
