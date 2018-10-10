package com.agioe.tool.data.tcp.api;

import com.agioe.tool.data.tcp.payload.ControlParameter;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.SensorEvent;

import java.util.List;

/**
 * 通信接口
 *
 * @author yshen
 * @since 2018/10/10
 */
public interface TcpApi {
    /**
     * 发送 数据
     *
     * @param dataList 传感器数据
     */
    void sendSensorData(List<SensorData> dataList);

    /**
     * 发送 事件
     *
     * @param eventList 传感器事件
     */
    void sendSensorEvent(List<SensorEvent> eventList);

    /**
     * 注册 控制命令监听器
     *
     * @param listener 监听器
     */
    void registerControlListener(ControlListener listener);

    /**
     * 发送 控制响应
     *
     * @param parameterList 控制响应
     */
    void senControlReply(List<ControlParameter> parameterList);

}
