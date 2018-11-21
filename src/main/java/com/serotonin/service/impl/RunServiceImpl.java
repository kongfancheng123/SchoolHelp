package com.serotonin.service.impl;

import com.serotonin.RunnerApplication;
import com.serotonin.entity.HostInfo;
import com.serotonin.entity.RealtimeEvent;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RealtimeEventService;
import com.serotonin.service.RunService;
import com.serotonin.util.DecimalToBinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class RunServiceImpl implements RunService {
    private Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

    @Value("${application.interval}")
    private int interval;

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private DealEventService dealEventService;

    @Autowired
    private RealtimeEventService realtimeEventService;
    @Override
    public void run(String com, ModbusMaster master, HostInfo hostInfo1) {
        while (true) {
            long startTime = System.currentTimeMillis();
            Map<String, RealtimeEvent> faultMap = new HashMap<>();
            //查询实时表
            List<RealtimeEvent> realtimeEvents = realtimeEventService.selectAll();
            if (realtimeEvents.size() > 0) {
                for (RealtimeEvent realtimeEvent : realtimeEvents) {
                    if (realtimeEvent.getEventType() != null && realtimeEvent.getEventType() == 1) {
                        faultMap.put(realtimeEvent.getLowMachineKey(), realtimeEvent);
                    }
                }
            }
            List<Integer> longList = new ArrayList<>();
            try {
                BatchRead<String> batchRead = new BatchRead<String>();
                for (int j = 10; j < 210; j++) {
                    batchRead.addLocator(String.valueOf(j), BaseLocator.holdingRegister(1, j, DataType.TWO_BYTE_INT_UNSIGNED));
                }
                BatchResults<String> results = master.send(batchRead);
                for (int j = 10; j < 210; j++) {
                    Integer intValue = results.getIntValue(String.valueOf(j));
                    longList.add(intValue);
                }

                BatchRead<String> batchRead1 = new BatchRead<String>();
                for (int j = 210; j < 410; j++) {
                    batchRead1.addLocator(String.valueOf(j), BaseLocator.holdingRegister(1, j, DataType.TWO_BYTE_INT_UNSIGNED));
                }
                BatchResults<String> results1 = master.send(batchRead1);
                for (int j = 210; j < 410; j++) {
                    Integer intValue = results1.getIntValue(String.valueOf(j));
                    longList.add(intValue);
                }

                BatchRead<String> batchRead2 = new BatchRead<String>();
                for (int j = 410; j < 610; j++) {
                    batchRead2.addLocator(String.valueOf(j), BaseLocator.holdingRegister(1, j, DataType.TWO_BYTE_INT_UNSIGNED));
                }
                BatchResults<String> results2 = master.send(batchRead2);
                for (int j = 410; j < 610; j++) {
                    Integer intValue = results2.getIntValue(String.valueOf(j));
                    longList.add(intValue);
                }

                BatchRead<String> batchRead3 = new BatchRead<String>();
                for (int j = 610; j < 810; j++) {
                    batchRead3.addLocator(String.valueOf(j), BaseLocator.holdingRegister(1, j, DataType.TWO_BYTE_INT_UNSIGNED));
                }
                BatchResults<String> results3 = master.send(batchRead3);
                for (int j = 610; j < 810; j++) {
                    Integer intValue = results3.getIntValue(String.valueOf(j));
                    longList.add(intValue);
                }
                System.out.println(longList.size());
                hostInfo1.setIsConn(1);
                //更新数据表
                hostInfoService.updateHostInfo(hostInfo1);
                //调用数据处理一
                dealEventService.storeCommuData1(hostInfo1);
            } catch (Exception e) {
                logger.info(e.getMessage());
                hostInfo1.setIsConn(0);
                //更新数据表
                hostInfoService.updateHostInfo(hostInfo1);
                //调用数据处理二
                dealEventService.storeCommuData2(hostInfo1);
                logger.info("准备停止程序");
                System.exit(0);
            }
            int i = 10;
            for (Integer integer : longList) {//起始和最终地址可配
                try {
                    String result1 = DecimalToBinary.decimalToBinary(integer);
                    String substring1 = result1.substring(2, 4);
//                        String key1 = "";
                    Integer deviceNum1 = 1;
                    dealEventService.dealEvent(i, substring1, com, deviceNum1, faultMap);
                    String substring2 = result1.substring(6, 8);
//                        String key2 = "";
                    Integer deviceNum2 = 2;
                    dealEventService.dealEvent(i, substring2, com, deviceNum2, faultMap);
                    String substring3 = result1.substring(10, 12);
//                        String key3 = "";
                    Integer deviceNum3 = 3;
                    dealEventService.dealEvent(i, substring3, com, deviceNum3, faultMap);
                    String substring4 = result1.substring(14, 16);
//                        String key4 = "";
                    Integer deviceNum4 = 4;
                    dealEventService.dealEvent(i, substring4, com, deviceNum4, faultMap);
                    i++;
                } catch (Exception e) {
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
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + "毫秒");
            System.out.println("=========================" + interval);
            System.out.println("停止获取数据,休息中..........");
            try {
                System.out.println("=========================" + interval);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
