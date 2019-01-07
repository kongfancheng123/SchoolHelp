package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.UserPermissionService;
import com.serotonin.dao.UserPermissionDao;
import com.serotonin.entity.UserPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Resource
    private UserPermissionDao userPermissionDao;

    @Override
    public Integer insertUserPermission(UserPermission query) {
        return userPermissionDao.insertUserPermission(query);
    }

    @Override
    public Integer deleteUserPermission(Integer id) {
        return userPermissionDao.deleteUserPermission(id);
    }

    @Override
    public List<UserPermission> selectByUserPermission(UserPermission query) {
        return userPermissionDao.selectByUserPermission(query);
    }

    @Override
    public Integer updateUserPermission(UserPermission query) {
        return userPermissionDao.updateUserPermission(query);
    }

    @Override
    public List<UserPermission> selectAll() {
        return userPermissionDao.selectAll();
    }
}
