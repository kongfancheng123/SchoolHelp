package com.serotonin.BaseService;

import com.serotonin.entity.Province;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
public interface ProvinceService {
    /**
     * 新增省份
     *
     * @param query
     * @return
     */
    Integer insertProvince(Province query);

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
    List<Province> selectByProvince(Province query);

    /**
     * 更新省份
     *
     * @param query
     * @return
     */
    Integer updateProvince(Province query);

    /**
     * 查询所有省份
     */
    List<Province> selectAll();
}
