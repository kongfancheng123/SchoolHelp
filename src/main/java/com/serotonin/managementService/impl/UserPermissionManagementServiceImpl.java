package com.serotonin.managementService.impl;

import com.serotonin.BaseService.PermissionService;
import com.serotonin.BaseService.UserPermissionService;
import com.serotonin.BaseService.UserService;
import com.serotonin.entity.Permission;
import com.serotonin.entity.User;
import com.serotonin.entity.UserPermission;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementQoAndVo.vo.GetUserPermissionVo;
import com.serotonin.managementService.UserPermissionManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by fchkong on 2019/1/8.
 */
@Service
public class UserPermissionManagementServiceImpl implements UserPermissionManagementService {
    @Resource
    private UserPermissionService userPermissionService;
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    public WebResponse getUserPermission() {
        List<GetUserPermissionVo> getUserPermissionVos=new ArrayList<>();
        List<UserPermission> userPermissions = userPermissionService.selectAll();
        if(userPermissions.size()>0){
            for(UserPermission userPermission:userPermissions){
                GetUserPermissionVo getUserPermissionVo=new GetUserPermissionVo();
                getUserPermissionVo.setId(userPermission.getId());
                getUserPermissionVo.setUserId(userPermission.getUserId());
                getUserPermissionVo.setPermissionId(userPermission.getPermissionId());
                //获取用户
                User user=new User();
                user.setId(userPermission.getUserId());
                List<User> users = userService.selectByUser(user);
                if(users.size()>0){
                    getUserPermissionVo.setUserName(users.get(0).getUserName());
                }
                //获取权限
                Permission permission=new Permission();
                permission.setId(userPermission.getPermissionId());
                List<Permission> permissions = permissionService.selectByPermission(permission);
                if(permissions.size()>0){
                    getUserPermissionVo.setPermissionName(permissions.get(0).getPermissionName());
                }
                getUserPermissionVos.add(getUserPermissionVo);
            }
        }
        return WebResponse.success(getUserPermissionVos);
    }

    @Override
    public WebResponse addUserPermission(UserPermission userPermission) {
        userPermissionService.insertUserPermission(userPermission);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteUserPermission(Integer id) {
        userPermissionService.deleteUserPermission(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateUserPermission(UserPermission userPermission) {
        userPermissionService.updateUserPermission(userPermission);
        return WebResponse.success();
    }
}
