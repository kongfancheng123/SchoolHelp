package com.agioe.tool.data.log;

import lombok.Data;

/**
 * 日志消息格式
 *
 * @author yshen
 * @since 2018/9/27
 */
@Data
public class LoggerMessage {

    private String body;
    private String timestamp;
    private String threadName;
    private String className;
    private String level;

    public LoggerMessage(String body, String timestamp, String threadName, String className, String level) {
        this.body = body;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.className = className;
        this.level = level;
    }

    public LoggerMessage() {
    }


}
