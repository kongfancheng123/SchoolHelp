package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.Event_historyDao;
import com.agioe.tool.data.entity.Event_history;
import com.agioe.tool.data.service.Event_historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Event_historyServiceImpl implements Event_historyService {
    @Autowired
    private Event_historyDao event_historyDao;

    @Override
    public Integer insertEvent_history(Event_history event_history) {
        return event_historyDao.insertEvent_history(event_history);
    }

    @Override
    public Integer deleteEvent_history(Integer id) {
        return event_historyDao.deleteEvent_history(id);
    }

    @Override
    public List<Event_history> selectByEvent_history(Event_history event_history) {
        return event_historyDao.selectByEvent_history(event_history);
    }

    @Override
    public Integer updateEvent_history(Event_history event_history) {
        return event_historyDao.updateEvent_history(event_history);
    }

    @Override
    public List<Event_history> selectAll() {
        return event_historyDao.selectAll();
    }

    @Override
    public Event_history selectByid(Integer id) {
        return event_historyDao.selectByid(id);
    }


}
