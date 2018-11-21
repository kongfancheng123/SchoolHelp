package com.serotonin.dao;

import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.RealtimeEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Repository
public interface RealtimeEventDao {
    /**
     * 新增实时事件
     *
     * @param realtimeEvent
     * @return
     */
    Integer insertRealtimeEvent(@Param("realtimeEvent") RealtimeEvent realtimeEvent);

    /**
     * 删除主机
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
    List<RealtimeEvent> selectByRealtimeEvent(@Param("realtimeEvent") RealtimeEvent realtimeEvent);

    /**
     * 更新实时事件
     *
     * @param realtimeEvent
     * @return
     */
    Integer updateRealtimeEvent(@Param("realtimeEvent") RealtimeEvent realtimeEvent);

    /**
     * 建表
     */
    Integer createTable(@Param("createTableParam") CreateTableParam createTableParam);

    /**
     * 查询所有
     */
    List<RealtimeEvent> selectAll();
}
