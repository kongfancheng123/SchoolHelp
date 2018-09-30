package com.agioe.tool.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yshen
 * @since 2018/9/27
 */
@RestController
public class DemoHelloApi {

    private final static Logger logger = LoggerFactory.getLogger(DemoHelloApi.class);

    @GetMapping("/test")
    public String test() {
        logger.warn("方法被调用");
        return "hello data tool";
    }
}
