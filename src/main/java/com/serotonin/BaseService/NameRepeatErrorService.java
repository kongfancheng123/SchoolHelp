package com.serotonin.BaseService;


import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/4.
 */

/**
 * 名称重复捕获异常
 */
public interface NameRepeatErrorService {
    WebResponse getNameRepeatError(Exception e, String string);
}
