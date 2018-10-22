package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.MessageDelayQueue;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.meta.SensorDataType;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.server.Server;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.List;

import static com.agioe.tool.data.tcp.Header.LENGTH_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.Header.TYPE_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.payload.meta.ProtocolConstant.DATA_SEND;

/**
 * 2.2.1.采集服务上报实时数据
 *
 * @author yshen
 * @since 2018/10/10
 */
public class DataSend extends AbstractProtocol {

    public DataSend() {
        super(DATA_SEND);
    }

    @Override
    public ByteBuf encode(Message msg) {
        ByteBuf bodyBuf = Unpooled.buffer();
        //采集点数量
        bodyBuf.writeShort(msg.getBody().size());
        for (Object obj : msg.getBody()) {
            SensorData data = (SensorData) obj;
            byte[] keyBytes = data.getKey().getBytes(Charset.forName("ascii"));
            //采集点编码长度
            bodyBuf.writeByte(keyBytes.length);
            //采集点编码
            bodyBuf.writeBytes(keyBytes);
            //采集值类型
            bodyBuf.writeByte(data.getType());
            //采集值长度 与 采集值
            SensorDataType sensorDataType = SensorDataType.getByType(data.getType());
            switch (sensorDataType) {
                case YC:
                    bodyBuf.writeShort(4);
                    bodyBuf.writeFloat(Float.valueOf(data.getVal()));
                    break;
                case YX:
                    bodyBuf.writeShort(1);
                    bodyBuf.writeByte(Byte.valueOf(data.getVal()));
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
    public void onAvailable(Message msg) {

    }

    @Override
    public AbstractProtocol getReplyProtocol() {
        return null;
    }

    @Override
    public Message buildReplyMessage(int sessionId, List<Object> objectList) {
        return null;
    }

    @Override
    public void send(String ipAndPortString, Message msg) {
        msg.getHeader().setType(getType());
        Server.broadcast(encode(msg));
        //当前发送时间
        msg.setTime(System.currentTimeMillis());
        //当前发送次数+1
        msg.setNumber(msg.getNumber() + 1);
        MessageDelayQueue.add(msg);
    }
}
