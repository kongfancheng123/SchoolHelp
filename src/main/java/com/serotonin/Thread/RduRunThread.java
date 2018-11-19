package com.serotonin.Thread;

import com.serotonin.Test.TestSerialPortWrapper;
import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RunService;

/**
 * Create by fchkong on 2018/11/19.
 */
public class RduRunThread implements Runnable {
    private HostInfo hostInfo1;
    private HostInfoService hostInfoService;
    private RunService runService;
    private DealEventService dealEventService;

    public RduRunThread(HostInfo hostInfo1, HostInfoService hostInfoService, RunService runService, DealEventService dealEventService) {
        this.hostInfo1 = hostInfo1;
        this.hostInfoService = hostInfoService;
        this.runService = runService;
        this.dealEventService = dealEventService;
    }

    @Override
    public void run() {
        //串口名
        String commPortId = hostInfo1.getHostAddr();
        String com = hostInfo1.getDefault2();
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
        try {
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        boolean isCoon = master.testSlaveNode(0);
        if (isCoon) {
            hostInfo1.setIsConn(1);
            //更新数据表
            hostInfoService.updateHostInfo(hostInfo1);
            //调用数据处理一
            dealEventService.storeCommuData1(hostInfo1);
        } else {
            hostInfo1.setIsConn(0);
            //更新数据表
            hostInfoService.updateHostInfo(hostInfo1);
            //调用数据处理二
            dealEventService.storeCommuData2(hostInfo1);
        }
        //todo:捕获异常,如果有异常,代表连接失败,要进行重新连接
        //测试数据0是否连接
        System.out.println(master.testSlaveNode(0));
        try {
            runService.run(com, master);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
