package com.serotonin.BaseService;

import com.serotonin.entity.User;

import java.util.List;

/**
 * Create by fchkong on 2018/12/28.
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param query
     * @return
     */
    Integer insertUser(User query);

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
    List<User> selectByUser(User query);

    /**
     * 更新用户
     *
     * @param query
     * @return
     */
    Integer updateUser(User query);

    /**
     * 查询所有用户
     */
    List<User> selectAll();
}
