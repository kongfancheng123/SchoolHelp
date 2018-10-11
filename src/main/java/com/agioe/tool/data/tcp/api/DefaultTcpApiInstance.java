package com.agioe.tool.data.tcp.api;

import com.agioe.tool.data.common.ObjectUtil;
import com.agioe.tool.data.tcp.ControlQueue;
import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.payload.ControlParameter;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.SensorEvent;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.protocol.factory.ProtocolFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 默认的接口实现类
 *
 * @author yshen
 * @since 2018/10/10
 */
@Service
public class DefaultTcpApiInstance implements TcpApi {

    private ControlListener listener;

    public DefaultTcpApiInstance(ControlListener listener) {
        this.listener = listener;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            while (true) {
                List<ControlParameter> controlParameterList = ControlQueue.getInstance().poll();
                if (listener != null) {
                    listener.onControlArrive(controlParameterList);
                }
            }
        };
        executorService.submit(runnable);

    }

    @Override
    public void sendSensorData(List<SensorData> dataList) {
        byte type = (byte) 3;
        senMessage(type, ObjectUtil.toObject(dataList));
    }

    @Override
    public void sendSensorEvent(List<SensorEvent> eventList) {
        byte type = (byte) 5;
        senMessage(type, ObjectUtil.toObject(eventList));
    }

    @Override
    public void registerControlListener(ControlListener listener) {
        this.listener = listener;
    }

    @Override
    public void senControlReply(List<ControlParameter> parameterList) {
        byte type = (byte) 10;
        senMessage(type, ObjectUtil.toObject(parameterList));
    }


    /**
     * 封装发送消息的方法
     *
     * @param type       协议类型
     * @param objectList 协议body
     */
    private void senMessage(byte type, List<Object> objectList) {
        //构造消息
        Message message = new Message();
        //构造消息头
        Header header = message.getHeader();
        header.setType(type);
        //构造消息体
        message.setBody(objectList);
        //匹配协议
        AbstractProtocol protocol = ProtocolFactory.buildProtocol(message.getHeader().getType());
        //发送
        protocol.send(message);
    }

}
