package com.serotonin.BaseService;

import com.serotonin.entity.City;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
public interface CityService {
    /**
     * 新增城市
     *
     * @param query
     * @return
     */
    Integer insertCity(City query);

    /**
     * 删除城市
     *
     * @param id
     * @return
     */
    Integer deleteCity(Integer id);

    /**
     * 多条件查询城市
     *
     * @param query
     * @return
     */
    List<City> selectByCity(City query);

    /**
     * 更新城市
     *
     * @param query
     * @return
     */
    Integer updateCity(City query);

    /**
     * 查询所有城市
     */
    List<City> selectAll();
}
