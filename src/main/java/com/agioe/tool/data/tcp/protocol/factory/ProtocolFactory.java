package com.agioe.tool.data.tcp.protocol.factory;

import com.agioe.tool.data.tcp.protocol.*;

import static com.agioe.tool.data.tcp.payload.meta.ProtocolConstant.*;

/**
 * 协议工厂
 *
 * @author yshen
 * @since 2018/10/10
 */
public class ProtocolFactory {

    /**
     * 构建协议
     * 根据协议类型 构建协议
     *
     * @param type 协议类型
     * @return
     */
    public static AbstractProtocol buildProtocol(byte type) {
        AbstractProtocol protocol = null;
        switch (type) {
            case PING:
                protocol = new Ping();
                break;
            case PONG:
                protocol = new Pong();
                break;
            case DATA_SEND:
                protocol = new DataSend();
                break;
            case DATA_REPLY:
                protocol = new DataReply();
                break;
            case EVENT_SEND:
                protocol = new EventSend();
                break;
            case EVENT_REPLY:
                protocol = new EventReply();
                break;
            case CONTROL_SEND:
                protocol = new ControlSend();
                break;
            case CONTROL_REPLY:
                protocol = new ControlReply();
                break;
            default:
                break;
        }
        return protocol;
    }


    /**
     * 获取协议匹配的响应协议
     *
     * @param type
     * @return
     */
    public static AbstractProtocol getReplyProtocol(byte type) {
        AbstractProtocol protocol = null;
        switch (type) {
            case PING:
                protocol = new Pong();
                break;
            case PONG:
                break;
            case DATA_SEND:
                break;
            case DATA_REPLY:
                break;
            case EVENT_SEND:
                break;
            case EVENT_REPLY:
                break;
            case CONTROL_SEND:
                protocol = new ControlReply();
                break;
            case CONTROL_REPLY:
                break;
            default:
                break;
        }
        return protocol;
    }

}
