package com.oliiyu.userservice.common;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

    /**
     * 这里通过logger的名字来获取logger，LoggerFactory和Logger都是slf4j包里的，
     * 没有用到log4j2的任何类，这就是把log4j2的scope配置为runtime的原因
     */
    private static Logger logger = LoggerFactory.getLogger("com.foo.Bar");

    public static void main(String[] args) throws Exception {
        System.out.printf("\n-- START %s\n", LocalDateTime.now());
        logger.warn("warn");
//        try {
//            while (true) {
//                logger.info("show something " + LocalDateTime.now());
//            }
//        } finally {
//            System.out.printf("\n-- DONE %s\n", LocalDateTime.now());
//        }
    }
}
