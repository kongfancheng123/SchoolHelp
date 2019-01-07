package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.PermissionService;
import com.serotonin.dao.PermissionDao;
import com.serotonin.entity.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/7.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    @Override
    public Integer insertPermission(Permission query) {
        return permissionDao.insertPermission(query);
    }

    @Override
    public Integer deletePermission(Integer id) {
        return permissionDao.deletePermission(id);
    }

    @Override
    public List<Permission> selectByPermission(Permission query) {
        return permissionDao.selectByPermission(query);
    }

    @Override
    public Integer updatePermission(Permission query) {
        return permissionDao.updatePermission(query);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionDao.selectAll();
    }
}
