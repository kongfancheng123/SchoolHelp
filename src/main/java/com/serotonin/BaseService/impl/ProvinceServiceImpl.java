package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.ProvinceService;
import com.serotonin.dao.ProvinceDao;
import com.serotonin.entity.Province;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Resource
    private ProvinceDao provinceDao;

    @Override
    public Integer insertProvince(Province query) {
        return provinceDao.insertProvince(query);
    }

    @Override
    public Integer deleteProvince(Integer id) {
        return provinceDao.deleteProvince(id);
    }

    @Override
    public List<Province> selectByProvince(Province query) {
        return provinceDao.selectByProvince(query);
    }

    @Override
    public Integer updateProvince(Province query) {
        return provinceDao.updateProvince(query);
    }

    @Override
    public List<Province> selectAll() {
        return provinceDao.selectAll();
    }
}
