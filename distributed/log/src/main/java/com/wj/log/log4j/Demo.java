package com.wj.log.log4j;

import org.apache.log4j.Logger;

public class Demo {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Demo.class);
        logger.info("hello");
    }
}
