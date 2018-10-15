package com.agioe.tool.data.tcp.server;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.agioe.tool.data.common.PropertiesConfig.TCP_SERVER_PORT;

/**
 * @author yshen
 * @since 2018/9/30
 */
public class Server {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    private static Worker worker;

    /**
     * 通信初始化
     */
    public static void init() {
        worker = new Worker(TCP_SERVER_PORT);
        new Thread(worker).start();
        logger.info("服务端通信线程加载完毕,端口{}", TCP_SERVER_PORT);
    }


    /**
     * 广播
     *
     * @param buf
     */
    public static void broadcast(ByteBuf buf) {
        worker.broadcast(buf);
    }

    /**
     * 单播
     *
     * @param ipAndPortString
     * @param buf
     */
    public static void unicast(String ipAndPortString, ByteBuf buf) {
        worker.unicast(ipAndPortString, buf);
    }
}
