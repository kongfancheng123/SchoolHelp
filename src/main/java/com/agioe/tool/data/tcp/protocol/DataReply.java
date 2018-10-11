package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;

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
    public void reply(Message msg) {

    }

    @Override
    public void onAvailable(Message msg) {

    }

    @Override
    public void send(Message msg) {

    }
}
