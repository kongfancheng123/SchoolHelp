package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.EventHistory;

import java.util.List;

public interface EventHistoryService {
    /**
     * 新增历史事件
     *
     * @param eventHistory
     * @return
     */
    Integer insertEventHistory(EventHistory eventHistory);

    /**
     * 删除历史事件
     *
     * @param id
     * @return
     */
    Integer deleteEventHistory(Integer id);

    /**
     * 多条件查询历史事件
     *
     * @param eventHistory
     * @return
     */
    List<EventHistory> selectByEventHistory(EventHistory eventHistory);

    /**
     * 更新历史事件
     *
     * @param eventHistory
     * @return
     */
    Integer updateEventHistory(EventHistory eventHistory);

    /**
     * 查找所有历史事件
     */
    List<EventHistory> selectAll();

    /**
     * 根据id进行查找历史事件
     */
    EventHistory selectByid(Integer id);


}
