package com.serotonin.service.impl;

import com.serotonin.dao.CollectorInfoDao;
import com.serotonin.entity.CollectorInfo;
import com.serotonin.service.CollectorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/11/19.
 */
@Service
public class CollectorInfoServiceImpl implements CollectorInfoService {
    @Autowired
    private CollectorInfoDao collectorInfoDao;

    @Override
    public List<CollectorInfo> selectByCollectorInfo(CollectorInfo collectorInfo) {
        return collectorInfoDao.selectByCollectorInfo(collectorInfo);
    }
}
