package com.serotonin.service.impl;

import com.serotonin.RunnerApplication;
import com.serotonin.entity.HostInfo;
import com.serotonin.entity.RealtimeEvent;
import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RealtimeEventService;
import com.serotonin.service.SalveRunService;
import com.serotonin.util.DecimalToBinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by fchkong on 2018/12/17.
 */
@Service
public class SalveRunServiceImpl implements SalveRunService {
    private Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private DealEventService dealEventService;

    @Autowired
    private RealtimeEventService realtimeEventService;

    @Override
    public void SalveRun(BasicProcessImage processImage, HostInfo hostInfo1, String com) {
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
            for (int i = 10; i < 810; i++) {
                Number numeric = processImage.getNumeric(RegisterRange.HOLDING_REGISTER, i, DataType.TWO_BYTE_INT_UNSIGNED);
                Integer intValue = Integer.valueOf(String.valueOf(numeric));
                longList.add(intValue);
                //还原缓存数据
                processImage.setNumeric(RegisterRange.HOLDING_REGISTER, i, DataType.TWO_BYTE_INT_UNSIGNED, 32767);
            }
            System.out.println(longList.size());
            hostInfo1.setIsConn(1);
            //更新数据表
            hostInfoService.updateHostInfo(hostInfo1);
            //调用数据处理一
            dealEventService.storeCommuData1(hostInfo1);
        } catch (Exception e) {
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
        int i = 10;
        for (Integer integer : longList) {//起始和最终地址可配
            try {
                if (integer != 32767) {
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
                }
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
        System.out.println("=========================");
        Long time = endTime - startTime;
        System.out.println("共耗时间为:" + time);

//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//        }
    }
}
