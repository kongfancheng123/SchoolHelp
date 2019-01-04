package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.CollegeService;
import com.serotonin.dao.CollegeDao;
import com.serotonin.entity.College;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Service
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeDao collegeDao;

    @Override
    public Integer insertCollege(College query) {
        return collegeDao.insertCollege(query);
    }

    @Override
    public Integer deleteCollege(Integer id) {
        return collegeDao.deleteCollege(id);
    }

    @Override
    public List<College> selectByCollege(College query) {
        return collegeDao.selectByCollege(query);
    }

    @Override
    public Integer updateCollege(College query) {
        return collegeDao.updateCollege(query);
    }

    @Override
    public List<College> selectAll() {
        return collegeDao.selectAll();
    }
}
