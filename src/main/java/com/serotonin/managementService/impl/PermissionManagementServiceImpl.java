package com.serotonin.managementService.impl;

import com.serotonin.BaseService.PermissionService;
import com.serotonin.entity.Permission;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.PermissionManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/8.
 */
@Service
public class PermissionManagementServiceImpl implements PermissionManagementService {
    @Resource
    private PermissionService permissionService;

    @Override
    public WebResponse getPermissions() {
        List<Permission> permissions = permissionService.selectAll();
        return WebResponse.success(permissions);
    }

    @Override
    public WebResponse addPermission(Permission permission) {
        permissionService.insertPermission(permission);
        return WebResponse.success();
    }

    @Override
    public WebResponse deletePermission(Integer id) {
        permissionService.deletePermission(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updatePermission(Permission permission) {
        permissionService.updatePermission(permission);
        return WebResponse.success();
    }
}
