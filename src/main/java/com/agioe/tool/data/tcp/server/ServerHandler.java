package com.agioe.tool.data.tcp.server;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import com.agioe.tool.data.tcp.protocol.factory.AbstractProtocol;
import com.agioe.tool.data.tcp.protocol.factory.ProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yshen
 * @since 2018/9/30
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取远端机器IP和端口
     *
     * @param ctx
     * @return
     */
    public static String getIpAndPortString(ChannelHandlerContext ctx) {
        String socketString = ctx.channel().remoteAddress().toString();
        String ipAndPortString = socketString.substring(1, socketString.length());
        return ipAndPortString;
    }

    /**
     * 获取远端机器IP
     *
     * @param ctx
     * @return
     */
    public static String getIPString(ChannelHandlerContext ctx) {
        String ipString;
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        ipString = socketString.substring(1, colonAt);
        return ipString;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端" + getIpAndPortString(ctx) + "接入连接");
        Worker.getClientMap().put(getIpAndPortString(ctx), ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Worker.getClientMap().remove(getIpAndPortString(ctx));
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        ByteBuf buf = (ByteBuf) obj;
        logger.info("收到数据:{}", ByteBufUtil.hexDump(buf));
        //解析协议头
        int sessionId = buf.readInt();
        int length = buf.readInt();
        byte type = buf.readByte();
        //协议匹配
        AbstractProtocol protocol = ProtocolFactory.buildProtocol(type);
        //解码
        Header header = new Header(sessionId, length, type);
        Message msg = protocol.decode(buf, header);
        //响应协议匹配
        AbstractProtocol replyProtocol = protocol.getReplyProtocol();
        Message replyMsg = protocol.buildReplyMessage(sessionId, msg.getBody());
        //发送响应协议
        replyProtocol.send(getIpAndPortString(ctx), replyMsg);
        //业务处理
        protocol.onAvailable(msg);
        buf.release();

    }
}

