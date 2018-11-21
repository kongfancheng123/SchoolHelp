package com.serotonin.service.impl;

import com.serotonin.dao.RealtimeEventDao;
import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.RealtimeEvent;
import com.serotonin.service.RealtimeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class RealtimeEventServiceImpl implements RealtimeEventService {
    @Autowired
    private RealtimeEventDao realtimeEventDao;

    @Override
    public Integer insertRealtimeEvent(RealtimeEvent realtimeEvent) {
        return realtimeEventDao.insertRealtimeEvent(realtimeEvent);
    }

    @Override
    public Integer deleteRealtimeEvent(Integer id) {
        return realtimeEventDao.deleteRealtimeEvent(id);
    }

    @Override
    public List<RealtimeEvent> selectByRealtimeEvent(RealtimeEvent realtimeEvent) {
        return realtimeEventDao.selectByRealtimeEvent(realtimeEvent);
    }

    @Override
    public Integer updateRealtimeEvent(RealtimeEvent realtimeEvent) {
        return realtimeEventDao.updateRealtimeEvent(realtimeEvent);
    }

    @Override
    public Integer createTable(CreateTableParam createTableParam) {
        return realtimeEventDao.createTable(createTableParam);
    }

    @Override
    public List<RealtimeEvent> selectAll() {
        return realtimeEventDao.selectAll();
    }
}
