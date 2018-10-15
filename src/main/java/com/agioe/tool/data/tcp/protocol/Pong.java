package com.agioe.tool.data.tcp.protocol;

import com.agioe.tool.data.common.PropertiesConfig;
import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.MessageDelayQueue;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.server.Server;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.agioe.tool.data.tcp.Header.LENGTH_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.Header.TYPE_FIELD_BYTE_COUNT;
import static com.agioe.tool.data.tcp.payload.meta.ProtocolConstant.PONG;

/**
 * 2.1.2.采集服务响应ping命令
 *
 * @author yshen
 * @since 2018/10/10
 */
public class Pong extends AbstractProtocol {

    private static Logger logger = LoggerFactory.getLogger(Pong.class);

    /**
     * type 2
     */
    public Pong() {
        super(PONG);
    }

    @Override
    public ByteBuf encode(Message msg) {
        ByteBuf msgBuf = Unpooled.buffer();
        msgBuf.writeInt(msg.getHeader().getSessionId());
        msgBuf.writeInt(LENGTH_FIELD_BYTE_COUNT + TYPE_FIELD_BYTE_COUNT);
        msgBuf.writeByte(getType());
        return msgBuf;
    }

    @Override
    public Message decode(ByteBuf bodyBuf, Header header) {
        return null;
    }

    @Override
    public void reply(String ipAndPortString, Message msg) {

    }

    @Override
    public void onAvailable(Message msg) {

    }

    @Override
    public void send(String ipAndPortString, Message msg) {
        Server.unicast(ipAndPortString, encode(msg));
        //当前发送时间
        msg.setTime(System.currentTimeMillis());
        //当前发送次数+1
        msg.setNumber(msg.getNumber() + 1);
        if (msg.getNumber() <= PropertiesConfig.RETRY_MAX_NUMBERS) {
            MessageDelayQueue.add(msg);
        } else {
            logger.error("通信故障" + ByteBufUtil.hexDump(encode(msg)));
        }
    }
}
