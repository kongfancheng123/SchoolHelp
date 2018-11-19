package com.serotonin.service;

import com.serotonin.entity.CollectorInfo;

import java.util.List;

/**
 * Create by fchkong on 2018/11/19.
 */
public interface CollectorInfoService {
    /**
     * 多条件查询数据
     *
     * @param collectorInfo
     * @return
     */
    List<CollectorInfo> selectByCollectorInfo(CollectorInfo collectorInfo);
}
