package com.serotonin.Thread;

import com.serotonin.entity.HostInfo;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RunService;

/**
 * Create by fchkong on 2018/11/19.
 */
public class TcpRunThread implements Runnable {
    private HostInfo hostInfo1;
    private HostInfoService hostInfoService;
    private RunService runService;
    private DealEventService dealEventService;

    public TcpRunThread(HostInfo hostInfo1, HostInfoService hostInfoService, RunService runService, DealEventService dealEventService) {
        this.hostInfo1 = hostInfo1;
        this.hostInfoService = hostInfoService;
        this.runService = runService;
        this.dealEventService = dealEventService;
    }

    @Override
    public void run() {
        //参数
        IpParameters params = new IpParameters();
        //获取主机串口编号
        String com = hostInfo1.getDefault2();
        //从站所在地址
        params.setHost(hostInfo1.getHostAddr());
        //从站端口
        params.setPort(Integer.valueOf(hostInfo1.getDefault1()));
        //创建主站
        ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);
        //启动主站,创建连接
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
