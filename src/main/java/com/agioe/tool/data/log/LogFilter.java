package com.agioe.tool.data.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import java.text.DateFormat;
import java.util.Date;

/**
 * 日志消息过滤器
 *
 * @author yshen
 * @since 2018/9/27
 */
public class LogFilter extends Filter {

    @Override
    public FilterReply decide(Object o) {
        ILoggingEvent event = (ILoggingEvent) o;
        LoggerMessage loggerMessage = new LoggerMessage(
                event.getMessage(),
                DateFormat.getDateTimeInstance().format(new Date(event.getTimeStamp())),
                event.getThreadName(),
                event.getLoggerName(),
                event.getLevel().levelStr
        );
        LoggerQueue.getInstance().push(loggerMessage);
        return FilterReply.ACCEPT;
    }
}
