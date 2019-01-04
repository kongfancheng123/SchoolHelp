package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.SchoolDistrictService;
import com.serotonin.dao.SchoolDistrictDao;
import com.serotonin.entity.SchoolDistrict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/3.
 */
@Service
public class SchoolDistrictServiceImpl implements SchoolDistrictService {
    @Resource
    private SchoolDistrictDao schoolDistrictDao;

    @Override
    public Integer insertSchoolDistrict(SchoolDistrict query) {
        return schoolDistrictDao.insertSchoolDistrict(query);
    }

    @Override
    public Integer deleteSchoolDistrict(Integer id) {
        return schoolDistrictDao.deleteSchoolDistrict(id);
    }

    @Override
    public List<SchoolDistrict> selectBySchoolDistrict(SchoolDistrict query) {
        return schoolDistrictDao.selectBySchoolDistrict(query);
    }

    @Override
    public Integer updateSchoolDistrict(SchoolDistrict query) {
        return schoolDistrictDao.updateSchoolDistrict(query);
    }

    @Override
    public List<SchoolDistrict> selectAll() {
        return schoolDistrictDao.selectAll();
    }
}
