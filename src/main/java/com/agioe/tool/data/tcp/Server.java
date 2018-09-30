package com.agioe.tool.data.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.agioe.tool.data.common.PropertiesConfig.TCP_SERVER_PORT;

/**
 * @author yshen
 * @since 2018/9/30
 */
public class Server {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * 通信初始化
     */
    public static void init() {
        new Thread(new Worker(TCP_SERVER_PORT)).start();
        logger.info("服务端通信线程加载完毕,端口{}", TCP_SERVER_PORT);
    }

}
