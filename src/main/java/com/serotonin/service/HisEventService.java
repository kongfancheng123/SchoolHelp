package com.serotonin.service;

import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.HisEvent;

/**
 * Create by fchkong on 2018/11/19.
 */
public interface HisEventService {
    /**
     * 新增实时事件
     *
     * @param hisEvent
     * @return
     */
    Integer insertHisEvent(HisEvent hisEvent, CreateTableParam createTableParam);


    /**
     * 查询表名是否存在
     */

    Integer selectTable(CreateTableParam createTableParam);
}
