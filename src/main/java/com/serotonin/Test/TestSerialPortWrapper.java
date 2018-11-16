/**
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 *
 * @author Terry Packer
 */
package com.serotonin.Test;

import com.serotonin.modbus4j.serial.SerialPortWrapper;
import gnu.io.*;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * This class is not finished
 *
 * @author Terry Packer
 *
 */
public class TestSerialPortWrapper implements SerialPortWrapper {

    private String commPortId;
    private int baudRate;
    private int flowControlIn;
    private int flowControlOut;
    private int dataBits;
    private int stopBits;
    private int parity;

    public TestSerialPortWrapper(String commPortId, int baudRate, int flowControlIn,
                                 int flowControlOut, int dataBits, int stopBits, int parity) {

        this.baudRate = baudRate;
        this.flowControlIn = flowControlIn;
        this.flowControlOut = flowControlOut;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.commPortId = commPortId;
    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#close()
     */
    @Override
    public void close(SerialPort serialPort) throws Exception {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }

    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#open()
     */
    @Override
    public SerialPort open() throws Exception {
        try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(this.commPortId);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(this.commPortId, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {

                SerialPort serialPort = (SerialPort) commPort;

                try {
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(this.baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
//					throw new SerialPortParameterFailure();
                }

                //System.out.println("Open " + portName + " sucessfully !");
                return serialPort;

            } else {
                //不是串口
//				throw new NotASerialPort();
            }
        } catch (NoSuchPortException e1) {
//			throw new NoSuchPort();
        } catch (PortInUseException e2) {
//			throw new PortInUse();
        }
        return null;

    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getInputStream()
     */
    @Override
    public InputStream getInputStream(SerialPort serialPort) throws Exception {
        InputStream in = serialPort.getInputStream();
        return in;
    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getOutputStream()
     */
    @Override
    public OutputStream getOutputStream(SerialPort serialPort) throws Exception {
        OutputStream out = serialPort.getOutputStream();
        return out;
    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getBaudRate()
     */
    @Override
    public int getBaudRate() {
        return this.baudRate;
    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getStopBits()
     */
    @Override
    public int getStopBits() {
        return this.stopBits;
    }

    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getParity()
     */
    @Override
    public int getParity() {
        return this.parity;
    }


    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getFlowControlIn()
     */
    @Override
    public int getFlowControlIn() {
        return this.flowControlIn;
    }


    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getFlowControlOut()
     */
    @Override
    public int getFlowControlOut() {
        return this.flowControlOut;
    }


    /* (non-Javadoc)
     * @see com.serotonin.modbus4j.serial.SerialPortWrapper#getDataBits()
     */
    @Override
    public int getDataBits() {
        return this.dataBits;
    }

    public String getCommPortId() {
        return commPortId;
    }
}
