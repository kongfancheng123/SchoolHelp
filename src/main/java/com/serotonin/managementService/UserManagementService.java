package com.serotonin.managementService;

import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/4.
 */
public interface UserManagementService {
    /**
     * 获取用户列表
     */
    WebResponse getUsers();

    /**
     * 添加用户
     */
    WebResponse addUser(User user);

    /**
     * 删除用户
     */
    WebResponse deleteUser(Integer id);

    /**
     * 更新用户
     */
    WebResponse updateUser(User user);
}
