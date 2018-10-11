package com.agioe.tool.data.tcp.payload.meta;

/**
 * 协议常量
 * (对照私有协议文档)
 *
 * @author yshen
 * @since 2018/10/11
 */
public interface ProtocolConstant {

    byte PING = 1;
    byte PONG = 2;
    byte DATA_SEND = 3;
    byte DATA_REPLY = 4;
    byte EVENT_SEND = 5;
    byte EVENT_REPLY = 6;
    byte CONTROL_SEND = 9;
    byte CONTROL_REPLY = 10;
}
