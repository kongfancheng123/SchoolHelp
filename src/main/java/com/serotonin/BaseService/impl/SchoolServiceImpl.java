package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.SchoolService;
import com.serotonin.dao.SchoolDao;
import com.serotonin.entity.School;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolDao schoolDao;

    @Override
    public Integer insertSchool(School query) {
        return schoolDao.insertSchool(query);
    }

    @Override
    public Integer deleteSchool(Integer id) {
        return schoolDao.deleteSchool(id);
    }

    @Override
    public List<School> selectBySchool(School query) {
        return schoolDao.selectBySchool(query);
    }

    @Override
    public Integer updateSchool(School query) {
        return schoolDao.updateSchool(query);
    }

    @Override
    public List<School> selectAll() {
        return schoolDao.selectAll();
    }
}
