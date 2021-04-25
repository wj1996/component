package com.wj.log.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Demo {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(Demo.class);
        log.info("hello");
        log.warn("hello1");
        log.error("hello2");
        log.debug("hello3");
    }

}
