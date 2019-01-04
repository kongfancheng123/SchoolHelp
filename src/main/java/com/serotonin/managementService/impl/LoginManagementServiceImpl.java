package com.serotonin.managementService.impl;

import com.serotonin.BaseService.UserService;
import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.LoginManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/3.
 */
@Service
public class LoginManagementServiceImpl implements LoginManagementService {
    @Resource
    private UserService userService;

    @Override
    public WebResponse login(User user) {
        //查询用户是否存在
        List<User> users = userService.selectByUser(user);
        if (users.size() <= 0) {
            return WebResponse.error(400, "用户名或密码错误");
        } else {
            return WebResponse.success();
        }
    }
}
