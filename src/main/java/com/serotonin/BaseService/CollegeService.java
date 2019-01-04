package com.serotonin.BaseService;

import com.serotonin.entity.College;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
public interface CollegeService {
    /**
     * 新增学院
     *
     * @param query
     * @return
     */
    Integer insertCollege(College query);

    /**
     * 删除学院
     *
     * @param id
     * @return
     */
    Integer deleteCollege(Integer id);

    /**
     * 多条件查询学院
     *
     * @param query
     * @return
     */
    List<College> selectByCollege(College query);

    /**
     * 更新学院
     *
     * @param query
     * @return
     */
    Integer updateCollege(College query);

    /**
     * 查询所有学院
     */
    List<College> selectAll();
}
