package com.serotonin.service.impl;

import com.serotonin.dao.HostInfoDao;
import com.serotonin.entity.HostInfo;
import com.serotonin.service.HostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class HostInfoServiceImpl implements HostInfoService {
    @Autowired
    private HostInfoDao hostInfoDao;

    @Override
    public List<HostInfo> selectByHostInfo(HostInfo hostInfo) {
        return hostInfoDao.selectByHostInfo(hostInfo);
    }

    @Override
    public Integer updateHostInfo(HostInfo hostInfo) {
        return hostInfoDao.updateHostInfo(hostInfo);
    }
}
