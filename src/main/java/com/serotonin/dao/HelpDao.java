package com.serotonin.dao;

import com.serotonin.entity.Help;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Repository
public interface HelpDao {
    /**
     * 新增帮助
     *
     * @param query
     * @return
     */
    Integer insertHelp(@Param("query") Help query);

    /**
     * 删除帮助
     *
     * @param id
     * @return
     */
    Integer deleteHelp(Integer id);

    /**
     * 多条件查询帮助
     *
     * @param query
     * @return
     */
    List<Help> selectByHelp(@Param("query)") Help query);

    /**
     * 更新帮助
     *
     * @param query
     * @return
     */
    Integer updateHelp(@Param("query)") Help query);

    /**
     * 查询所有帮助
     */
    List<Help> selectAll();
}
