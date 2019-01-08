package com.serotonin.managementController;

import com.serotonin.entity.Permission;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.PermissionManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/8.
 */
@RestController
@RequestMapping(value = "/web/management/permission")
public class PermissionController {
    @Resource
    private PermissionManagementService permissionManagementService;

    /**
     * 获取权限列表
     */
    @RequestMapping(value = "/getPermissions", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getPermissions() {
        return permissionManagementService.getPermissions();
    }

    /**
     * 添加权限
     */
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addPermission(@RequestBody Permission permission) {
        return permissionManagementService.addPermission(permission);
    }

    /**
     * 删除权限
     */
    @RequestMapping(value = "/deletePermission", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deletePermission(@RequestParam Integer id) {
        return permissionManagementService.deletePermission(id);
    }

    /**
     * 更新权限
     */
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updatePermission(@RequestBody Permission permission) {
        return permissionManagementService.updatePermission(permission);
    }
}
