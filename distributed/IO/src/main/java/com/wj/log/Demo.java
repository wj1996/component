package com.wj.log;

import org.apache.log4j.Logger;

public class Demo {

    private static Logger log = Logger.getLogger(Demo.class);


    public static void main(String[] args) {
        log.info("hello world");
        log.debug("hello world");
        log.error("hello world");
        log.warn("hello world");
    }

}
