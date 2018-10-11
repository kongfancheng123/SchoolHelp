package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.EventHistoryDao;
import com.agioe.tool.data.entity.EventHistory;
import com.agioe.tool.data.service.EventHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventHistoryServiceImpl implements EventHistoryService {
    @Autowired
    private EventHistoryDao eventHistoryDao;

    @Override
    public Integer insertEventHistory(EventHistory eventHistory) {
        return eventHistoryDao.insertEventHistory(eventHistory);
    }

    @Override
    public Integer deleteEventHistory(Integer id) {
        return eventHistoryDao.deleteEventHistory(id);
    }

    @Override
    public List<EventHistory> selectByEventHistory(EventHistory eventHistory) {
        return eventHistoryDao.selectByEventHistory(eventHistory);
    }

    @Override
    public Integer updateEventHistory(EventHistory eventHistory) {
        return eventHistoryDao.updateEventHistory(eventHistory);
    }

    @Override
    public List<EventHistory> selectAll() {
        return eventHistoryDao.selectAll();
    }

    @Override
    public EventHistory selectByid(Integer id) {
        return eventHistoryDao.selectByid(id);
    }


}
