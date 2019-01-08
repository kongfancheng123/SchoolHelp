package com.serotonin.managementController;

import com.serotonin.entity.UserPermission;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.UserPermissionManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/8.
 */
@RestController
@RequestMapping(value = "/web/management/userPermission")
public class UserPermissionController {
    @Resource
    private UserPermissionManagementService userPermissionManagementService;

    /**
     * 获取用户权限列表
     */
    @RequestMapping(value = "/getUserPermission", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getUserPermission() {
        return userPermissionManagementService.getUserPermission();
    }

    /**
     * 添加用户权限
     */
    @RequestMapping(value = "/addUserPermission", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addUserPermission(@RequestBody UserPermission userPermission) {
        return userPermissionManagementService.addUserPermission(userPermission);
    }

    /**
     * 删除用户权限
     */
    @RequestMapping(value = "/deleteUserPermission", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteUserPermission(@RequestParam Integer id) {
        return userPermissionManagementService.deleteUserPermission(id);
    }

    /**
     * 更新用户权限
     */
    @RequestMapping(value = "/updateUserPermission", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateUserPermission(@RequestBody UserPermission userPermission) {
        return userPermissionManagementService.updateUserPermission(userPermission);
    }
}
