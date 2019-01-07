package com.serotonin.BaseService;

import com.serotonin.entity.Permission;

import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
public interface PermissionService {
    /**
     * 新增权限
     *
     * @param query
     * @return
     */
    Integer insertPermission(Permission query);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    Integer deletePermission(Integer id);

    /**
     * 多条件查询权限
     *
     * @param query
     * @return
     */
    List<Permission> selectByPermission(Permission query);

    /**
     * 更新权限
     *
     * @param query
     * @return
     */
    Integer updatePermission(Permission query);

    /**
     * 查询所有权限
     */
    List<Permission> selectAll();
}
