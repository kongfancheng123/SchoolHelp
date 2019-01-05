package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.NameRepeatErrorService;
import com.serotonin.entity.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by fchkong on 2019/1/4.
 */
@Service
public class NameRepeatErrorServiceImpl implements NameRepeatErrorService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public WebResponse getNameRepeatError(Exception e, String string) {
        if (e.getMessage().contains("DuplicateKeyException")) {
            logger.error("名称重复");
            return WebResponse.error(400, "名称重复");
        }
        logger.error(string, e);
        return WebResponse.error(400, string);
    }
}
