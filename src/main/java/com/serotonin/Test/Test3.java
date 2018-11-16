/*
    Copyright (C) 2006-2007 Serotonin Software Technologies Inc.
 	@author Matthew Lohbihler
 */
package com.serotonin.Test;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.locator.BaseLocator;

/**
 * @author Matthew Lohbihler
 * Rtu串口连接
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        //串口名
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

        System.out.println(master.testSlaveNode(5));

        // Define the point locator.
        BaseLocator<Number> loc = BaseLocator.holdingRegister(5, 2, DataType.TWO_BYTE_INT_UNSIGNED);
        //获取值
        System.out.println(master.getValue(loc));

        // Set the point value
        //设置值
        master.setValue(loc, 1800);

        // Get the point value
        //获取值
        System.out.println(master.getValue(loc));
    }
}
