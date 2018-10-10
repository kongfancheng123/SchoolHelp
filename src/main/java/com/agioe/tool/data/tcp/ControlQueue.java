package com.agioe.tool.data.tcp;


import com.agioe.tool.data.tcp.payload.ControlParameter;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 控制队列
 *
 * @author yshen
 * @since 2018/10/10
 */
public class ControlQueue {
    /**
     * 队列大小
     */
    public static final int QUEUE_MAX_SIZE = 10000;
    private static ControlQueue controlQueue = new ControlQueue();
    /**
     * 阻塞队列
     */
    private BlockingQueue<List<ControlParameter>> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    private ControlQueue() {

    }

    public static ControlQueue getInstance() {
        return controlQueue;
    }

    /**
     * 消息入队
     *
     * @param parameter
     * @return
     */
    public boolean push(List<ControlParameter> parameterList) {
        //队列满了就抛出异常，不阻塞
        return this.blockingQueue.add(parameterList);
    }

    /**
     * 消息出队
     *
     * @return
     */
    public List<ControlParameter> poll() {
        List<ControlParameter> result = null;
        try {
            result = this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
