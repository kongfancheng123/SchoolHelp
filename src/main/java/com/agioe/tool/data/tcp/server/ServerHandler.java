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

    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        String socketString = "";
        socketString = ctx.channel().remoteAddress().toString();
        return socketString;
    }

    public static String getIPString(ChannelHandlerContext ctx) {
        String ipString = "";
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        ipString = socketString.substring(1, colonAt);
        return ipString;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端" + getRemoteAddress(ctx) + "接入连接");
        Worker.getClientMap().put(getIPString(ctx), ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Worker.getClientMap().remove(getIPString(ctx));
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
        //回应
        Message replyMsg = new Message();
        Header replyHeader = new Header();
        //响应消息头的sessionId为请求报文的sessionId
        replyHeader.setSessionId(sessionId);
        replyMsg.setHeader(replyHeader);
        protocol.reply(replyMsg);
        //解码
        Header header = new Header(sessionId, length, type);
        Message msg = protocol.decode(buf, header);
        //业务处理
        protocol.onAvailable(msg);
        buf.release();

    }
}

