package com.agioe.tool.data.tcp;

import java.util.concurrent.DelayQueue;

/**
 * 消息延迟队列
 *
 * @author yshen
 * @since 2018/10/10
 */
public class MessageDelayQueue {

    private static DelayQueue<Message> delayQueue = new DelayQueue<>();

    public static void add(Message message) {
        delayQueue.add(message);
    }

    public static Message take() throws InterruptedException {
        return delayQueue.take();
    }

    /**
     * 从队列删除与message的session相同的消息
     *
     * @param message
     */
    public static void remove(Message message) {
        delayQueue.remove(message);
    }
}
