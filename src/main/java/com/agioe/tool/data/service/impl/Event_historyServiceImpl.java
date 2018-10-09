package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.SendEvent_historyQo;
import com.agioe.tool.data.dao.Event_historyDao;
import com.agioe.tool.data.entity.Event_history;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Event_historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public WebResponse sendEvent_history(SendEvent_historyQo sendEvent_historyQo) {
        String event_code = sendEvent_historyQo.getEvent_code();
        Integer event_type = sendEvent_historyQo.getEvent_type();
        String event_value = sendEvent_historyQo.getEvent_value();
        Event_history event_history = new Event_history();
        event_history.setEvent_code(event_code);
        event_history.setEvent_state(0);
        event_history.setEvent_type(event_type);
        event_history.setEvent_value(event_value);
        event_history.setCreate_date(new Date());
        event_historyDao.insertEvent_history(event_history);
        //event_history==>Message==>ByteBuf==>发送
        //todo:发送事件
//        ByteBuf msgBuf = Unpooled.buffer();
//        Worker.send(msgBuf);
        return WebResponse.success();
    }

}
