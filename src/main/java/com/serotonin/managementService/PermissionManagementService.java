package com.serotonin.managementService;

import com.serotonin.entity.Permission;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/8.
 */
public interface PermissionManagementService {
    /**
     * 获取权限列表
     */
    WebResponse getPermissions();

    /**
     * 添加权限
     */
    WebResponse addPermission(Permission permission);

    /**
     * 删除权限
     */
    WebResponse deletePermission(Integer id);

    /**
     * 更新权限
     */
    WebResponse updatePermission(Permission permission);
}
