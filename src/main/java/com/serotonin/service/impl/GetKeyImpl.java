package com.serotonin.service.impl;

import com.serotonin.service.GetKey;
import org.springframework.stereotype.Service;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class GetKeyImpl implements GetKey {

    @Override
    public String getKey(String com, Integer i, Integer deviceNum) {
        String key = com;
        int i1 = ((i - 10) / 50) + 1;
        if (i1 < 10) {
            key = key + "00" + String.valueOf(i1);
        } else {
            key = key + "0" + String.valueOf(i1);
        }
        int i2 = ((i - 10) % 50) * 4 + deviceNum;
        if (i2 < 10) {
            key = key + "00" + String.valueOf(i2);
        }
        if (i2 >= 10 && i2 < 100) {
            key = key + "0" + String.valueOf(i2);
        }
        if (i2 >= 100) {
            key = key + String.valueOf(i2);
        }
        return key;
    }
}
