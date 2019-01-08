package com.serotonin.managementService;

import com.serotonin.entity.UserPermission;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/8.
 */
public interface UserPermissionManagementService {
    /**
     * 获取用户权限列表
     * @return
     */
    WebResponse getUserPermission();

    /**
     * 添加用户权限
     */
    WebResponse addUserPermission(UserPermission userPermission);

    /**
     * 删除用户权限
     */
    WebResponse deleteUserPermission(Integer id);

    /**
     * 更新用户权限
     */
    WebResponse updateUserPermission(UserPermission userPermission);
}
