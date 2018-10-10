package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.meta.SensorDataType;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

import static com.agioe.tool.data.tcp.Header.LENGTH_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.Header.TYPE_FIELD_BYTE_COUNT;

/**
 * 2.2.1.采集服务上报实时数据
 *
 * @author yshen
 * @since 2018/10/10
 */
public class DataSend extends AbstractProtocol {

    public DataSend() {
        super((byte) 3);
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
    public void reply(Message msg) {

    }

    @Override
    public void onAvailable(Message msg) {

    }

    @Override
    public void send(Message msg) {

    }
}
