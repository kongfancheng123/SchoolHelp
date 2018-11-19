package com.serotonin.service;

import com.serotonin.entity.CreateTableParam;

/**
 * Create by fchkong on 2018/11/15.
 */
public interface DealEventService {
    void dealEvent(Integer i, String result1, String com, Integer deviceNum);

    void storeData(String key, Integer i, Integer j, String detail);

    CreateTableParam getCreateTableParam();
}
