package com.serotonin.applicationService;

import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2018/12/27.
 */
public interface LoginApplicationService {
    /**
     * 注册
     *
     * @return
     */
    WebResponse register(User user);

    /**
     * 登录
     */
    WebResponse login(User user);
}
