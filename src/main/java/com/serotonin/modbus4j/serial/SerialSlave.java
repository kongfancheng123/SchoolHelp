/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2011 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.serotonin.modbus4j.serial;

import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.sero.messaging.StreamTransport;
import gnu.io.SerialPort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

abstract public class SerialSlave extends ModbusSlaveSet {

    private final Log LOG = LogFactory.getLog(SerialSlave.class);
    protected StreamTransport transport;
    // Runtime fields
    private SerialPortWrapper wrapper;

    public SerialSlave(SerialPortWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void start() throws ModbusInitException {
        try {

            SerialPort open = wrapper.open();

            transport = new StreamTransport(wrapper.getInputStream(open), wrapper.getOutputStream(open));
        } catch (Exception e) {
            throw new ModbusInitException(e);
        }
    }

    @Override
    public void stop() {
        try {
            SerialPort open = wrapper.open();
            wrapper.close(open);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
