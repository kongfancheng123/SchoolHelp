package com.serotonin.BaseService;

import com.serotonin.entity.UserPermission;

import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
public interface UserPermissionService {
    /**
     * 新增用户权限关系
     *
     * @param query
     * @return
     */
    Integer insertUserPermission(UserPermission query);

    /**
     * 删除用户权限关系
     *
     * @param id
     * @return
     */
    Integer deleteUserPermission(Integer id);

    /**
     * 多条件查询用户权限关系
     *
     * @param query
     * @return
     */
    List<UserPermission> selectByUserPermission(UserPermission query);

    /**
     * 更新用户权限关系
     *
     * @param query
     * @return
     */
    Integer updateUserPermission(UserPermission query);

    /**
     * 查询所有用户权限关系
     */
    List<UserPermission> selectAll();
}
