package com.serotonin.dao;

import com.serotonin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/27.
 */
@Repository
public interface UserDao {
    /**
     * 新增用户
     *
     * @param query
     * @return
     */
    Integer insertUser(@Param("query") User query);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Integer deleteUser(Integer id);

    /**
     * 多条件查询用户
     *
     * @param query
     * @return
     */
    List<User> selectByUser(@Param("query)") User query);

    /**
     * 更新用户
     *
     * @param query
     * @return
     */
    Integer updateUser(@Param("query)") User query);

    /**
     * 查询所有用户
     */
    List<User> selectAll();


}
