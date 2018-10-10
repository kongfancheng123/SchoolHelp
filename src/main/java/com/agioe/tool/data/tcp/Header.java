package com.agioe.tool.data.tcp;

import java.util.Objects;

/**
 * 通信消息头
 *
 * @author yshen
 * @since 2018/10/10
 */
public class Header {
    /**
     * 常量
     */
    public static int LENGTH_FIELD_BYTE_COUNT = 4;
    public static int TYPE_FIELD_BYTE_COUNT = 1;
    /**
     * 通信ID全局变量存储
     */
    private static int id = 1;
    /**
     * 通信ID
     */
    private int sessionId;
    /**
     * 数据长度
     */
    private int length;
    /**
     * 命令类型
     */
    private byte type;

    public Header(int sessionId) {
        this.sessionId = sessionId;
    }

    public Header(int sessionId, int length, byte type) {
        this.sessionId = sessionId;
        this.length = length;
        this.type = type;
    }

    public Header() {
        this.sessionId = idGenerator();
    }

    public Header(byte type) {
        this.sessionId = idGenerator();
        this.type = type;
    }


    public Header(int length, byte type) {
        this.sessionId = idGenerator();
        this.length = length;
        this.type = type;
    }

    /**
     * 通信全局生成器
     *
     * @return
     */
    private int idGenerator() {
        return id++;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }


    /**
     * 重写equals方法 只要sessionId相等 就认为相等
     * 目前sessionId的生成是全局的，不同组织的sessionId不会冲突
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Header)) {
            return false;
        }
        Header header = (Header) o;
        return sessionId == header.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Header{");
        sb.append("sessionId=").append(sessionId);
        sb.append(", length=").append(length);
        sb.append(", getType=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
