package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import io.netty.buffer.ByteBuf;

/**
 * 2.1.1.上级平台发送ping命令
 *
 * @author yshen
 * @since 2018/10/10
 */
public class Ping extends AbstractProtocol {

    /**
     * type 1
     */
    public Ping() {
        super((byte) 1);
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
