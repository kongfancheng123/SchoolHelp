package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.EventHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventHistoryDao {
    /**
     * 新增历史事件
     *
     * @param eventHistory
     * @return
     */
    Integer insertEventHistory(@Param("eventHistory") EventHistory eventHistory);

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
    List<EventHistory> selectByEventHistory(@Param("eventHistory") EventHistory eventHistory);

    /**
     * 更新历史事件
     *
     * @param eventHistory
     * @return
     */
    Integer updateEventHistory(@Param("eventHistory") EventHistory eventHistory);

    /**
     * 查找所有历史事件
     */
    List<EventHistory> selectAll();

    /**
     * 根据id进行查找历史事件
     */
    EventHistory selectByid(Integer id);
}
