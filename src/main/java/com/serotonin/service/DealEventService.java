package com.serotonin.service;

import com.serotonin.entity.CreateTableParam;
import com.serotonin.entity.HostInfo;

/**
 * Create by fchkong on 2018/11/15.
 */
public interface DealEventService {
    void dealEvent(Integer i, String result1, String com, Integer deviceNum);

    void storeData(String key, Integer i, Integer j, String detail);

    CreateTableParam getCreateTableParam();

    /**
     * 连接成功对数据库操作
     *
     * @param hostInfo
     */
    void storeCommuData1(HostInfo hostInfo);

    /**
     * 连接失败对数据库操作
     * @param hostInfo
     */
    void storeCommuData2(HostInfo hostInfo);

}
