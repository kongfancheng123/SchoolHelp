package com.serotonin.managementService;

import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2018/12/27.
 */
public interface LoginManagementService {
    /**
     * 登录
     */
    WebResponse login(User user);
}
