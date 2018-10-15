package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.MessageDelayQueue;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.server.Server;
import io.netty.buffer.ByteBuf;

import java.util.List;

import static com.agioe.tool.data.tcp.payload.meta.ProtocolConstant.DATA_REPLY;

/**
 * 2.2.2.上级平台响应实时数据
 *
 * @author yshen
 * @since 2018/10/10
 */
public class DataReply extends AbstractProtocol {

    public DataReply() {
        super(DATA_REPLY);
    }

    @Override
    public ByteBuf encode(Message msg) {
        return null;
    }

    @Override
    public Message decode(ByteBuf bodyBuf, Header header) {
        return new Message(header);
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
    public void onAvailable(Message msg) {

    }

    @Override
    public void send(String ipAndPortString, Message msg) {
        msg.getHeader().setType(getType());
        Server.unicast(ipAndPortString, encode(msg));
        //当前发送时间
        msg.setTime(System.currentTimeMillis());
        //当前发送次数+1
        msg.setNumber(msg.getNumber() + 1);
        MessageDelayQueue.add(msg);
    }
}
