package com.serotonin.dao;

import com.serotonin.entity.College;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface CollegeDao {
    /**
     * 新增学院
     *
     * @param query
     * @return
     */
    Integer insertCollege(@Param("query") College query);

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
    List<College> selectByCollege(@Param("query)") College query);

    /**
     * 更新学院
     *
     * @param query
     * @return
     */
    Integer updateCollege(@Param("query)") College query);

    /**
     * 查询所有学院
     */
    List<College> selectAll();
}
