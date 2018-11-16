package com.serotonin.service;

import com.serotonin.entity.ContainerInfo;
import com.serotonin.entity.HostInfo;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
public interface ContainerInfoService {
    /**
     * 多条件查询数据
     *
     * @param containerInfo
     * @return
     */
    List<HostInfo> selectByContainerInfo(ContainerInfo containerInfo);
}
