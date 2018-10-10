package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;

/**
 * @author yshen
 * @since 2018/10/10
 */
public class DataSend extends AbstractProtocol {

    public DataSend() {
        super((byte) 3);
    }

    @Override
    public ByteBuf encode(Message msg) {
        return null;
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
