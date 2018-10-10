package com.agioe.tool.data.tcp;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static com.agioe.tool.data.common.PropertiesConfig.PROTOCOL_TIMEOUT;

/**
 * 通信消息
 *
 * @author yshen
 * @since 2018/10/10
 */
public class Message implements Delayed {
    /**
     * 消息头
     */
    private Header header;
    /**
     * 消息体
     */
    private List<Object> body;

    /**
     * 消息发送时间
     */
    private long time;
    /**
     * 消息已发送次数
     */
    private int number;


    public Message() {
        this.header = new Header();
    }

    public Message(Header header) {
        this.header = header;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Object> getBody() {
        return body;
    }

    public void setBody(List<Object> body) {
        this.body = body;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.time + PROTOCOL_TIMEOUT - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Message message = (Message) o;
        if (this.time > message.time) {
            return 1;
        } else if (this.time > message.time) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(header, message.header);
    }

    @Override
    public int hashCode() {

        return Objects.hash(header);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("header=").append(header);
        sb.append(", body=").append(body);
        sb.append(", time=").append(time);
        sb.append(", number=").append(number);
        sb.append('}');
        return sb.toString();
    }
}
