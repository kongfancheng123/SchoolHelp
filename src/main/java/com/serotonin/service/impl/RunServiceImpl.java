package com.serotonin.service.impl;

import com.serotonin.constant.Constant;
import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RunService;
import com.serotonin.util.DecimalToBinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by fchkong on 2018/11/16.
 */
@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private HostInfoService hostInfoService;

    @Autowired
    private DealEventService dealEventService;
    @Override
    public void run(String com, ModbusMaster master, HostInfo hostInfo1) {
        while (true) {
            for (int i = 10; i < 810; i++) {//起始和最终地址可配
                try {
                    BaseLocator<Number> loc1 = BaseLocator.holdingRegister(1, i, DataType.TWO_BYTE_INT_UNSIGNED);
                    Number value1 = master.getValue(loc1);
                    String result1 = DecimalToBinary.decimalToBinary(value1);
                    String substring1 = result1.substring(2, 4);
//                        String key1 = "";
                    Integer deviceNum1 = 1;
                    dealEventService.dealEvent(i, substring1, com, deviceNum1);
                    String substring2 = result1.substring(6, 8);
//                        String key2 = "";
                    Integer deviceNum2 = 2;
                    dealEventService.dealEvent(i, substring2, com, deviceNum2);
                    String substring3 = result1.substring(10, 12);
//                        String key3 = "";
                    Integer deviceNum3 = 3;
                    dealEventService.dealEvent(i, substring3, com, deviceNum3);
                    String substring4 = result1.substring(14, 16);
//                        String key4 = "";
                    Integer deviceNum4 = 4;
                    dealEventService.dealEvent(i, substring4, com, deviceNum4);

                    hostInfo1.setIsConn(1);
                    //更新数据表
                    hostInfoService.updateHostInfo(hostInfo1);
                    //调用数据处理一
                    dealEventService.storeCommuData1(hostInfo1);
                } catch (Exception e) {
                    hostInfo1.setIsConn(0);
                    //更新数据表
                    hostInfoService.updateHostInfo(hostInfo1);
                    //调用数据处理二
                    dealEventService.storeCommuData2(hostInfo1);
                    System.out.println("准备停止程序");
                    System.exit(0);
                }
            }
            System.out.println("停止获取数据,休息中..........");
            try {
                Thread.sleep(Constant.THREAD_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
