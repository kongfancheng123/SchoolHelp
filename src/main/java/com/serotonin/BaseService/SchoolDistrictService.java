package com.serotonin.BaseService;

import com.serotonin.entity.SchoolDistrict;

import java.util.List;

/**
 * Create by fchkong on 2019/1/3.
 */
public interface SchoolDistrictService {
    /**
     * 新增校区
     *
     * @param query
     * @return
     */
    Integer insertSchoolDistrict(SchoolDistrict query);

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
    List<SchoolDistrict> selectBySchoolDistrict(SchoolDistrict query);

    /**
     * 更新校区
     *
     * @param query
     * @return
     */
    Integer updateSchoolDistrict(SchoolDistrict query);

    /**
     * 查询所有校区
     */
    List<SchoolDistrict> selectAll();
}
