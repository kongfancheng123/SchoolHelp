package com.serotonin.dao;

import com.serotonin.entity.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface CityDao {
    /**
     * 新增城市
     *
     * @param query
     * @return
     */
    Integer insertCity(@Param("query") City query);

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
    List<City> selectByCity(@Param("query)") City query);

    /**
     * 更新城市
     *
     * @param query
     * @return
     */
    Integer updateCity(@Param("query)") City query);

    /**
     * 查询所有城市
     */
    List<City> selectAll();
}
