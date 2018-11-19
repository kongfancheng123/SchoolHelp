package com.serotonin.dao;

import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.HisEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Create by fchkong on 2018/11/19.
 */
@Repository
public interface HisEventDao {
    /**
     * 新增实时事件
     *
     * @param hisEvent
     * @return
     */
    Integer insertHisEvent(@Param("hisEvent") HisEvent hisEvent, @Param("createTableParam") CreateTableParam createTableParam);


    /**
     * 查询表名是否存在
     */

    Integer selectTable(@Param("createTableParam") CreateTableParam createTableParam);
}
