/**
 * Copyright (C) 2015 Infinite Automation Software. All rights reserved.
 *
 * @author Terry Packer
 */
package com.serotonin.modbus4j.serial;

import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Wrapper to further aid in abstracting Modbus4J from a serial port implementation
 *
 * @author Terry Packer
 *
 */
public interface SerialPortWrapper {

    /**
     * Close the Serial Port
     */
    void close(SerialPort serialPort) throws Exception;

    /**
     *
     */
    SerialPort open() throws Exception;

    /**
     *
     * Return the input stream for an open port
     * @return
     */
    InputStream getInputStream(SerialPort serialPort) throws Exception;

    /**
     * Return the output stream for an open port
     * @return
     */
    OutputStream getOutputStream(SerialPort serialPort) throws Exception;

    /**
     * @return
     */
    int getBaudRate();

    /**
     *
     * @return
     */
    int getFlowControlIn();

    /**
     *
     * @return
     */
    int getFlowControlOut();

    /**
     * @return
     */
    int getDataBits();

    /**
     * @return
     */
    int getStopBits();

    /**
     * @return
     */
    int getParity();


}
