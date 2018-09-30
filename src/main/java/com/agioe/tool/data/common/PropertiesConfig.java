package com.agioe.tool.data.common;

import org.springframework.core.env.Environment;

/**
 * @author yshen
 * @since 2018/9/30
 */
public class PropertiesConfig {


    /**
     * 通信服务通信端口
     */
    public static int TCP_SERVER_PORT = Integer.valueOf(
            SpringContextUtil.getBean(Environment.class).getProperty("atm.tcp-server-port")
    );


    /**
     * 心跳间隔
     */
    public static int HEARTBEAT_INTERVAL = Integer.valueOf(
            SpringContextUtil.getBean(Environment.class).getProperty("atm.protocol-heartbeat-interval")
    );
    /**
     * 协议超时重试次数上限
     */
    public static long RETRY_MAX_NUMBERS = Long.valueOf(
            SpringContextUtil.getBean(Environment.class).getProperty("atm.protocol-retry-numbers-max")
    );
    /**
     * 协议超时配置
     */
    public static long PROTOCOL_TIMEOUT = Long.valueOf(
            SpringContextUtil.getBean(Environment.class).getProperty("atm.protocol-timeout")
    );


}
