package com.serotonin.dao;

import com.serotonin.entity.SchoolDistrict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface SchoolDistrictDao {
    /**
     * 新增校区
     *
     * @param query
     * @return
     */
    Integer insertSchoolDistrict(@Param("query") SchoolDistrict query);

    /**
     * 删除校区
     *
     * @param id
     * @return
     */
    Integer deleteSchoolDistrict(Integer id);

    /**
     * 多条件查询校区
     *
     * @param query
     * @return
     */
    List<SchoolDistrict> selectBySchoolDistrict(@Param("query)") SchoolDistrict query);

    /**
     * 更新校区
     *
     * @param query
     * @return
     */
    Integer updateSchoolDistrict(@Param("query)") SchoolDistrict query);

    /**
     * 查询所有校区
     */
    List<SchoolDistrict> selectAll();
}
