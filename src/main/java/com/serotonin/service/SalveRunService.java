package com.serotonin.service;

import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.BasicProcessImage;

/**
 * Create by fchkong on 2018/12/17.
 */
public interface SalveRunService {
    void SalveRun(BasicProcessImage processImage, HostInfo hostInfo1, String com);
}
