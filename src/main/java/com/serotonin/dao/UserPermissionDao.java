package com.serotonin.dao;

import com.serotonin.entity.UserPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
@Repository
public interface UserPermissionDao {
    /**
     * 新增用户权限关系
     *
     * @param query
     * @return
     */
    Integer insertUserPermission(@Param("query") UserPermission query);

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
    List<UserPermission> selectByUserPermission(@Param("query)") UserPermission query);

    /**
     * 更新用户权限关系
     *
     * @param query
     * @return
     */
    Integer updateUserPermission(@Param("query)") UserPermission query);

    /**
     * 查询所有用户权限关系
     */
    List<UserPermission> selectAll();
}
