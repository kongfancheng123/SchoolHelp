package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.payload.ControlParameter;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

import static com.agioe.tool.data.tcp.Header.LENGTH_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.Header.TYPE_FIELD_BYTE_COUNT;

/**
 * 2.5.2.采集服务响应控制命令
 *
 * @author yshen
 * @since 2018/10/10
 */
public class ControlReply extends AbstractProtocol {

    public ControlReply() {
        super((byte) 10);
    }

    @Override
    public ByteBuf encode(Message msg) {
        ByteBuf bodyBuf = Unpooled.buffer();
        //控制点数量
        bodyBuf.writeShort(msg.getBody().size());
        for (Object obj : msg.getBody()) {
            ControlParameter param = (ControlParameter) obj;
            //按照ASCII码将采集点号转换成字节数组
            byte[] keyBytes = param.getKey().getBytes(Charset.forName("ascii"));
            //控制点编码长度
            bodyBuf.writeByte(keyBytes.length);
            //控制点编码
            bodyBuf.writeBytes(keyBytes);
            //执行结果
            bodyBuf.writeByte(param.getFeedback());

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
