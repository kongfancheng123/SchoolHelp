package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.CityService;
import com.serotonin.dao.CityDao;
import com.serotonin.entity.City;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityDao cityDao;

    @Override
    public Integer insertCity(City query) {
        return cityDao.insertCity(query);
    }

    @Override
    public Integer deleteCity(Integer id) {
        return cityDao.deleteCity(id);
    }

    @Override
    public List<City> selectByCity(City query) {
        return cityDao.selectByCity(query);
    }

    @Override
    public Integer updateCity(City query) {
        return cityDao.updateCity(query);
    }

    @Override
    public List<City> selectAll() {
        return cityDao.selectAll();
    }
}
