package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.MessageDelayQueue;
import com.agioe.tool.data.tcp.payload.SensorEvent;
import com.agioe.tool.data.tcp.payload.meta.SensorDataType;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.server.Server;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

import static com.agioe.tool.data.tcp.Header.LENGTH_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.Header.TYPE_FIELD_BYTE_COUNT;

/**
 * 2.3.1.采集服务上报事件信息
 *
 * @author yshen
 * @since 2018/10/10
 */
public class EventSend extends AbstractProtocol {

    public EventSend() {
        super((byte) 5);
    }

    @Override
    public ByteBuf encode(Message msg) {
        ByteBuf bodyBuf = Unpooled.buffer();
        //采集点数量
        bodyBuf.writeShort(msg.getBody().size());
        //采集点事件
        for (Object obj : msg.getBody()) {
            SensorEvent event = (SensorEvent) obj;
            //按照ASCII码将采集点号转换成字节数组
            byte[] keyBytes = event.getKey().getBytes(Charset.forName("ascii"));
            //采集点编码长度
            bodyBuf.writeByte(keyBytes.length);
            //采集点编码
            bodyBuf.writeBytes(keyBytes);
            //事件类型
            bodyBuf.writeByte(event.getType());
            //事件码
            bodyBuf.writeShort(event.getEventCode());
            //事件时间(时间戳精确到秒)
            bodyBuf.writeInt((int) (System.currentTimeMillis() / 1000));
            //事件数据类型
            bodyBuf.writeByte(event.getEventDataType());
            //数据值长度 与 事件数据
            SensorDataType sensorDataType = SensorDataType.getByType(event.getEventDataType());
            switch (sensorDataType) {
                case YC:
                    bodyBuf.writeShort(4);
                    bodyBuf.writeFloat(Float.valueOf(event.getEventDataValue()));
                    break;
                case YX:
                    bodyBuf.writeShort(1);
                    bodyBuf.writeByte(Byte.valueOf(event.getEventDataValue()));
                    break;
                case YC_FLOAT_ARRAY:
                    //todo
                    break;
                case YC_INT_ARRAY:
                    //todo
                    break;
                case CHARACTER_STRING:
                    //todo
                    break;
                default:
                    break;
            }
        }
        ByteBuf msgBuf = Unpooled.buffer();
        msgBuf.writeInt(msg.getHeader().getSessionId());
        msgBuf.writeInt(LENGTH_FIELD_BYTE_COUNT + TYPE_FIELD_BYTE_COUNT + bodyBuf.readableBytes());
        msgBuf.writeByte(getType());
        msgBuf.writeBytes(bodyBuf);
        return msgBuf;
    }

    @Override
    public Message decode(ByteBuf bodyBuf, Header header) {
        return null;
    }

    @Override
    public void reply(Message msg) {

    }

    @Override
    public void onAvailable(Message msg) {

    }

    @Override
    public void send(Message msg) {
        msg.getHeader().setType(getType());
        Server.send(encode(msg));
        //当前发送时间
        msg.setTime(System.currentTimeMillis());
        //当前发送次数+1
        msg.setNumber(msg.getNumber() + 1);
        MessageDelayQueue.add(msg);

    }
}
