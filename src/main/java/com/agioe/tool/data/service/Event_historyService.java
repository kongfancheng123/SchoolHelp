package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.Event_history;

import java.util.List;

public interface Event_historyService {
    /**
     * 新增历史事件
     *
     * @param event_history
     * @return
     */
    Integer insertEvent_history(Event_history event_history);

    /**
     * 删除历史事件
     *
     * @param id
     * @return
     */
    Integer deleteEvent_history(Integer id);

    /**
     * 多条件查询历史事件
     *
     * @param event_history
     * @return
     */
    List<Event_history> selectByEvent_history(Event_history event_history);

    /**
     * 更新历史事件
     *
     * @param event_history
     * @return
     */
    Integer updateEvent_history(Event_history event_history);

    /**
     * 查找所有历史事件
     */
    List<Event_history> selectAll();

    /**
     * 根据id进行查找历史事件
     */
    Event_history selectByid(Integer id);


}
