package com.agioe.tool.data.Test;


import com.agioe.tool.data.tcp.api.TcpApi;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.SensorEvent;

import java.util.List;

/**
 * @author : YShen
 **/
public class TcpComponent {
    private TcpApi api;

    public TcpComponent(TcpApi api) {
        this.api = api;
    }

    public void sendData(List<SensorData> sensorDataList) {
        api.sendSensorData(sensorDataList);
    }

    public void sendEvent(List<SensorEvent> sensorEventList) {
        api.sendSensorEvent(sensorEventList);
    }
}
