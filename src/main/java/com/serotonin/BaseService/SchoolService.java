package com.serotonin.BaseService;

import com.serotonin.entity.School;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
public interface SchoolService {
    /**
     * 新增城市
     *
     * @param query
     * @return
     */
    Integer insertSchool(School query);

    /**
     * 删除城市
     *
     * @param id
     * @return
     */
    Integer deleteSchool(Integer id);

    /**
     * 多条件查询城市
     *
     * @param query
     * @return
     */
    List<School> selectBySchool(School query);

    /**
     * 更新城市
     *
     * @param query
     * @return
     */
    Integer updateSchool(School query);

    /**
     * 查询所有城市
     */
    List<School> selectAll();
}
