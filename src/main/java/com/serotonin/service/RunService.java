package com.serotonin.service;

import com.serotonin.modbus4j.ModbusMaster;

/**
 * Create by fchkong on 2018/11/16.
 */
public interface RunService {
    void paramEntry() throws Exception;

    void tcpRun() throws Exception;

    void rduRun() throws Exception;

    void run(String com, ModbusMaster master) throws Exception;
}
