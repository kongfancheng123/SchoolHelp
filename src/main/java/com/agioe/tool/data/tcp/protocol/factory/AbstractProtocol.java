package com.agioe.tool.data.tcp.protocol.factory;

import com.agioe.tool.data.tcp.Header;
import com.agioe.tool.data.tcp.Message;
import io.netty.buffer.ByteBuf;

/**
 * 协议抽象类
 *
 * @author yshen
 * @since 2018/10/10
 */
public abstract class AbstractProtocol {
    /**
     * 命令类型
     */
    private byte type;

    public AbstractProtocol(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    /**
     * 消息编码（message）
     *
     * @param msg 消息
     * @return
     */
    public abstract ByteBuf encode(Message msg);

    /**
     * 数据区解码（body）
     *
     * @param bodyBuf 数据区缓冲
     * @param header  消息头
     * @return
     */
    public abstract Message decode(ByteBuf bodyBuf, Header header);

    /**
     * 通信应答
     *
     * @param msg
     */
    public abstract void reply(String ipAndPortString, Message msg);

    /**
     * 消息经过解码后执行的操作
     *
     * @param msg
     */
    public abstract void onAvailable(Message msg);

    /**
     * 消息发送
     * 用到的地方 1 新消息发送 2 超时未返回的消息重发
     * 注意sessionId的问题,超时重发的时候,msg是已经有session的
     *
     * @param ipAndPortString 远端机器IP和端口 当为广播时，传null 单播时 传具体ip和端口
     * @param msg             消息
     */
    public abstract void send(String ipAndPortString, Message msg);
}
