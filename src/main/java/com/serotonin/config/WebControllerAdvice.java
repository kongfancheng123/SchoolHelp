package com.serotonin.config;

import com.serotonin.BaseService.NameRepeatErrorService;
import com.serotonin.entity.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * 接口异常拦截响应
 *
 * @author yshen
 * @since 2018/7/21
 */
@RestControllerAdvice
public class WebControllerAdvice {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private NameRepeatErrorService nameRepeatErrorService;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public WebResponse errorHandler(Exception e) {
        return nameRepeatErrorService.getNameRepeatError(e, "接口异常");
//        logger.error("接口异常", e);
//        return WebResponse.serverError();
    }
}
