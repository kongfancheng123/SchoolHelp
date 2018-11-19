package com.serotonin.service;

import com.serotonin.entity.ContainerInfo;

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
    List<ContainerInfo> selectByContainerInfo(ContainerInfo containerInfo);
}
