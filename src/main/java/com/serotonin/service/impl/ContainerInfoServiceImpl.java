package com.serotonin.service.impl;

import com.serotonin.dao.ContainerInfoDao;
import com.serotonin.entity.ContainerInfo;
import com.serotonin.service.ContainerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class ContainerInfoServiceImpl implements ContainerInfoService {
    @Autowired
    private ContainerInfoDao containerInfoDao;

    @Override
    public List<ContainerInfo> selectByContainerInfo(ContainerInfo containerInfo) {
        return containerInfoDao.selectByContainerInfo(containerInfo);
    }
}
