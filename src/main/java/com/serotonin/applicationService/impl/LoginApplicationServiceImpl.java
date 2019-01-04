package com.serotonin.applicationService.impl;

import com.serotonin.BaseService.UserService;
import com.serotonin.applicationService.LoginApplicationService;
import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/27.
 */
@Service
public class LoginApplicationServiceImpl implements LoginApplicationService {
    @Resource
    private UserService userService;

    @Override
    public WebResponse register(User user) {
        //判重
        List<User> users = userService.selectByUser(user);
        if (users.size() > 0) {
            return WebResponse.error(400, "用户名已存在");
        } else {
            Integer integer = userService.insertUser(user);
            return WebResponse.success();
        }
    }

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
