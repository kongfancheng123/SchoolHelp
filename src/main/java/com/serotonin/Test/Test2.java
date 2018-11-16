/*
    Copyright (C) 2006-2007 Serotonin Software Technologies Inc.
 	@author Matthew Lohbihler
 */
package com.serotonin.Test;

/**
 * @author Matthew Lohbihler
 * 网络连接测试
 */
public class Test2 {


    public static void main(String[] args) throws Exception {

//        DealEventService dealEventService = new DealEventServiceImpl();
//        HostInfoService hostInfoService = new HostInfoServiceImpl();
//        //todo:数据库获取主机地址以及端口
//        HostInfo hostInfo = new HostInfo();
//        hostInfo.setHostCode("FP9000C_1");
//        //参数
//        IpParameters params = new IpParameters();
//        List<HostInfo> hostInfos = hostInfoService.selectByHostInfo(hostInfo);
//        if (hostInfos.size() > 0) {
//            for (HostInfo hostInfo1 : hostInfos) {
//                //从站所在地址
//                params.setHost(hostInfo1.getHostAddr());
//                //从站端口
//                params.setPort(Integer.valueOf(hostInfo1.getDefault1()));
//                //创建主站
//                ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);
//                //启动主站,创建连接
//                master.init();
//                //todo:捕获异常,如果有异常,代表连接失败,要进行重新连接
//                //测试数据0是否连接
//                System.out.println(master.testSlaveNode(0));
//                while (true) {
//                    for (int i = 10; i < 20; i++) {//起始和最终地址可配
//                        //获取当前设备的key
//                        BaseLocator<Number> loc1 = BaseLocator.holdingRegister(1, i, DataType.TWO_BYTE_INT_UNSIGNED);
//                        Number value1 = master.getValue(loc1);
//                        String result1 = DecimalToBinary.decimalToBinary(value1);
//                        String substring1 = result1.substring(2, 4);
//                        String key1 = "";
//                        dealEventService.dealEvent(key1, substring1);
//                        String substring2 = result1.substring(6, 8);
//                        String key2 = "";
//                        dealEventService.dealEvent(key2, substring2);
//                        String substring3 = result1.substring(10, 12);
//                        String key3 = "";
//                        dealEventService.dealEvent(key3, substring3);
//                        String substring4 = result1.substring(14, 16);
//                        String key4 = "";
//                        dealEventService.dealEvent(key4, substring4);
//                    }
//                    System.out.println("停止获取数据,休息中..........");
//                    Thread.sleep(5000);
//                }
//            }
//        }


        // Define the point locator.

        //读取保持寄存器,对应功能码4x,可读可写
//        BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 11, DataType.TWO_BYTE_INT_UNSIGNED);
        //读取线圈状态,对应功能码为0x,值只能为false和true,对应显示值为0和1,可读可写
//        BaseLocator<Boolean> loc = BaseLocator.coilStatus(5, 2);
//        Boolean b=true;
        //读取输入状态,对应功能码为1x,只能读,不能写
//        BaseLocator<Boolean> loc = BaseLocator.inputStatus(5, 2);
//        Boolean b=true;
        //读取输入寄存器,对应功能码为3x,只能读,不能写
//        BaseLocator<Number> loc = BaseLocator.inputRegister(5, 2, DataType.TWO_BYTE_INT_UNSIGNED);
        //主站设置值
//        master.setValue(loc, 10);
        // Get the point value
//        Number value = master.getValue(loc);
//        String result = DecimalToBinary.decimalToBinary(value);
//        System.out.println(result);


    }
}
