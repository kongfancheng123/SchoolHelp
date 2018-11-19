package com.serotonin.service.impl;

import com.serotonin.dao.HisEventDao;
import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.HisEvent;
import com.serotonin.service.HisEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by fchkong on 2018/11/19.
 */
@Service
public class HisEventServiceImpl implements HisEventService {
    @Autowired
    private HisEventDao hisEventDao;

    @Override
    public Integer insertHisEvent(HisEvent hisEvent, CreateTableParam createTableParam) {
        return hisEventDao.insertHisEvent(hisEvent, createTableParam);
    }

    @Override
    public Integer selectTable(CreateTableParam createTableParam) {
        return hisEventDao.selectTable(createTableParam);
    }
}
