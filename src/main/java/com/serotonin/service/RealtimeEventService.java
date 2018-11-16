package com.serotonin.service;

import com.serotonin.entity.HostInfo;
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
    List<HostInfo> selectByRealtimeEvent(RealtimeEvent realtimeEvent);

    /**
     * 更新实时事件
     *
     * @param realtimeEvent
     * @return
     */
    Integer updateRealtimeEvent(RealtimeEvent realtimeEvent);
}
