package com.serotonin.dao;

import com.serotonin.entity.School;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface SchoolDao {
    /**
     * 新增城市
     *
     * @param query
     * @return
     */
    Integer insertSchool(@Param("query") School query);

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
    List<School> selectBySchool(@Param("query)") School query);

    /**
     * 更新城市
     *
     * @param query
     * @return
     */
    Integer updateSchool(@Param("query)") School query);

    /**
     * 查询所有城市
     */
    List<School> selectAll();
}
