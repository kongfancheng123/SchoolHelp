package com.serotonin.service.impl;

import com.serotonin.Test.TestSerialPortWrapper;
import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RunService;
import com.serotonin.util.DecimalToBinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void tcpRun() throws Exception {
        //todo:数据库获取主机地址以及端口
        HostInfo hostInfo = new HostInfo();
        hostInfo.setHostCode("FP9000C_1");
        //参数
        IpParameters params = new IpParameters();
        List<HostInfo> hostInfos = hostInfoService.selectByHostInfo(hostInfo);
        if (hostInfos.size() > 0) {
            for (HostInfo hostInfo1 : hostInfos) {
                //获取主机串口
                String com = hostInfo1.getDefault2();
                //从站所在地址
                params.setHost(hostInfo1.getHostAddr());
                //从站端口
                params.setPort(Integer.valueOf(hostInfo1.getDefault1()));
                //创建主站
                ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);
                //启动主站,创建连接
                master.init();
                //todo:捕获异常,如果有异常,代表连接失败,要进行重新连接
                //测试数据0是否连接
                System.out.println(master.testSlaveNode(0));
                while (true) {
                    for (int i = 10; i < 810; i++) {//起始和最终地址可配
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
                    }
                    System.out.println("停止获取数据,休息中..........");
                    Thread.sleep(5000);
                }
            }
        }
    }

    @Override
    public void rduRun() throws Exception {
//        串口名
        String commPortId = "COM2";
        //波特率
        int baudRate = 9600;
        int flowControlIn = 0;
        int flowControlOut = 0;
        //设置数据位
        int dataBits = 8;
        //设置停止位
        int stopBits = 1;
        int parity = 0;
        //串口包装类,包含启动串口,关闭串口,获取输入流,获取输出流等功能,要成功实现该类,需要手动导入RXTXcomm.jar包,并在系统盘中存放指定文件
        TestSerialPortWrapper wrapper = new TestSerialPortWrapper(commPortId, baudRate, flowControlIn, flowControlOut, dataBits, stopBits, parity);
        //创建rtu主站
        ModbusMaster master = new ModbusFactory().createRtuMaster(wrapper);
        //启动主站,流程为根据串口名校验,打开串口,获取输入和输出流,完成后关闭串口
        master.init();
        System.out.println(master.testSlaveNode(1));
        while (true) {

            // Define the point locator.
            BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 10, DataType.TWO_BYTE_INT_UNSIGNED);
            //获取值
            System.out.println(master.getValue(loc));

//            // Set the point value
//            //设置值
//            master.setValue(loc, 1800);

            // Get the point value
//            //获取值
//            System.out.println(master.getValue(loc));
        }
    }
}
