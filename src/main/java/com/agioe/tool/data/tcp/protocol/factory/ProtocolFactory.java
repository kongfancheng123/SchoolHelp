package com.agioe.tool.data.tcp.protocol.factory;

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
            default:
                break;
        }
        return protocol;
    }
}
