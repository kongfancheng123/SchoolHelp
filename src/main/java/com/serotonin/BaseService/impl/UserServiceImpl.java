package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.UserService;
import com.serotonin.dao.UserDao;
import com.serotonin.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer insertUser(User query) {
        return userDao.insertUser(query);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> selectByUser(User query) {
        return userDao.selectByUser(query);
    }

    @Override
    public Integer updateUser(User query) {
        return userDao.updateUser(query);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }
}
