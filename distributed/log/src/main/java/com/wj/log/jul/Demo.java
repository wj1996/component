package com.wj.log.jul;

import java.util.logging.Logger;

public class Demo {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("demo");
        logger.info("hello");
        logger.warning("hello2");
    }
}
