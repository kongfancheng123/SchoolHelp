package com.serotonin.service.impl;

import com.serotonin.RunnerApplication;
import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.SalveRunService;
import com.serotonin.service.SalveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Create by fchkong on 2018/12/17.
 */
@Service
public class SalveServiceImpl implements SalveService {
    private Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

    @Autowired
    private SalveRunService salveRunService;

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private DealEventService dealEventService;

    static BasicProcessImage getModscanProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setAllowInvalidAddress(true);

        processImage.setInvalidAddressValue(Short.MAX_VALUE);
        processImage.setExceptionStatus((byte) 151);

        return processImage;
    }

    @Override
    public void salveRun() {
        try {
            ModbusFactory modbusFactory = new ModbusFactory();
            final ModbusSlaveSet listener = modbusFactory.createTcpSlave(false);
            BasicProcessImage processImage1 = getModscanProcessImage(1);
            listener.addProcessImage(processImage1);
            HostInfo hostInfo = new HostInfo();
            hostInfo.setHostType(3);
            HostInfo hostInfo1 = hostInfoService.selectByHostInfo(hostInfo).get(0);

            new Thread(new Runnable() {
                public void run() {
                    try {
                        listener.start();
                    } catch (ModbusInitException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//
            while (true) {
                BasicProcessImage processImage = (BasicProcessImage) listener.getProcessImage(1);
                String com = "001";
                salveRunService.SalveRun(processImage, hostInfo1, com);
            }
        } catch (Exception e) {
            HostInfo hostInfo = new HostInfo();
            hostInfo.setHostType(3);
            HostInfo hostInfo1 = hostInfoService.selectByHostInfo(hostInfo).get(0);
            e.printStackTrace();
            logger.info(e.getMessage());
            hostInfo1.setIsConn(0);
            //更新数据表
            hostInfoService.updateHostInfo(hostInfo1);
            //调用数据处理二
            dealEventService.storeCommuData2(hostInfo1);
            logger.info("准备停止程序");
            System.exit(0);
        }

    }
}
