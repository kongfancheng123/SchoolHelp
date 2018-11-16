package com.serotonin.service;

import com.serotonin.entity.HostInfo;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
public interface HostInfoService {
    /**
     * 多条件查询数据
     *
     * @param hostInfo
     * @return
     */
    List<HostInfo> selectByHostInfo(HostInfo hostInfo);
}
