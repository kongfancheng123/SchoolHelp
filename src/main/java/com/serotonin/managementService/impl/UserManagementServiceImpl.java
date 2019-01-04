package com.serotonin.managementService.impl;

import com.serotonin.BaseService.UserService;
import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.UserManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/4.
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Resource
    private UserService userService;

    @Override
    public WebResponse getUsers() {
        List<User> users = userService.selectAll();
        return WebResponse.success(users);
    }

    @Override
    public WebResponse addUser(User user) {
        //todo:判断重复
        userService.insertUser(user);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteUser(Integer id) {
        //todo:判断绑定关系
        userService.deleteUser(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateUser(User user) {
        userService.updateUser(user);
        return WebResponse.success();
    }
}
