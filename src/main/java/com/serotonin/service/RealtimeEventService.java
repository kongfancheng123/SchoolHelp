package com.serotonin.service;

import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.RealtimeEvent;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
public interface RealtimeEventService {
    /**
     * 新增实时事件
     *
     * @param realtimeEvent
     * @return
     */
    Integer insertRealtimeEvent(RealtimeEvent realtimeEvent);

    /**
     * 删除事件
     *
     * @param id
     * @return
     */
    Integer deleteRealtimeEvent(Integer id);

    /**
     * 多条件查询实时事件
     *
     * @param realtimeEvent
     * @return
     */
    List<RealtimeEvent> selectByRealtimeEvent(RealtimeEvent realtimeEvent);

    /**
     * 更新实时事件
     *
     * @param realtimeEvent
     * @return
     */
    Integer updateRealtimeEvent(RealtimeEvent realtimeEvent);

    /**
     * 建表
     */
    Integer createTable(CreateTableParam createTableParam);

    /**
     * 查询所有
     */
    List<RealtimeEvent> selectAll();
}
