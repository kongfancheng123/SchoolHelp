package com.serotonin.dao;

import com.serotonin.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
@Repository
public interface PermissionDao {
    /**
     * 新增权限
     *
     * @param query
     * @return
     */
    Integer insertPermission(@Param("query") Permission query);

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
    List<Permission> selectByPermission(@Param("query)") Permission query);

    /**
     * 更新权限
     *
     * @param query
     * @return
     */
    Integer updatePermission(@Param("query)") Permission query);

    /**
     * 查询所有权限
     */
    List<Permission> selectAll();
}
