package com.serotonin.managementService;

import com.serotonin.entity.*;

/**
 * Create by fchkong on 2019/1/3.
 */
public interface AreaManagementService {
    /**
     * 获取省份列表
     */
    WebResponse getProvince();

    /**
     * 添加省份
     */
    WebResponse addProvince(Province province);

    /**
     * 删除省份
     */
    WebResponse deleteProvince(Integer id);

    /**
     * 修改省份
     */
    WebResponse updateProvince(Province province);

    /**
     * 获取市列表
     */
    WebResponse getCity();

    /**
     * 添加市
     */
    WebResponse addCity(City city);

    /**
     * 删除市
     */
    WebResponse deleteCity(Integer id);

    /**
     * 更新市
     */
    WebResponse updateCity(City city);

    /**
     * 获取学校列表
     */
    WebResponse getSchool();

    /**
     * 添加学校
     */
    WebResponse addSchool(School school);

    /**
     * 删除学校
     */
    WebResponse deleteSchool(Integer id);

    /**
     * 更新学校
     */
    WebResponse updateSchool(School school);

    /**
     * 获取学院列表
     */
    WebResponse getCollege();

    /**
     * 添加学院
     */
    WebResponse addCollege(College college);

    /**
     * 删除学院
     */
    WebResponse deleteCollege(Integer id);

    /**
     * 更新学院
     */
    WebResponse updateCollege(College college);

    /**
     * 获取校区列表
     */
    WebResponse getSchoolDistrict();

    /**
     * 添加校区
     */
    WebResponse addSchoolDistrict(SchoolDistrict schoolDistrict);

    /**
     * 删除校区
     */
    WebResponse deleteSchoolDistrict(Integer id);

    /**
     * 更新校区
     */
    WebResponse updateSchoolDistrict(SchoolDistrict schoolDistrict);
}
