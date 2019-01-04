package com.serotonin.dao;

import com.serotonin.entity.Province;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface ProvinceDao {
    /**
     * 新增省份
     *
     * @param query
     * @return
     */
    Integer insertProvince(@Param("query") Province query);

    /**
     * 删除省份
     *
     * @param id
     * @return
     */
    Integer deleteProvince(Integer id);

    /**
     * 多条件查询省份
     *
     * @param query
     * @return
     */
    List<Province> selectByProvince(@Param("query)") Province query);

    /**
     * 更新省份
     *
     * @param query
     * @return
     */
    Integer updateProvince(@Param("query)") Province query);

    /**
     * 查询所有省份
     */
    List<Province> selectAll();
}
