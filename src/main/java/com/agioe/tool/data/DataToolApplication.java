package com.agioe.tool.data;

import com.agioe.tool.data.Test.TcpComponent;
import com.agioe.tool.data.common.SpringContextUtil;
import com.agioe.tool.data.log.LoggerMessage;
import com.agioe.tool.data.log.LoggerQueue;
import com.agioe.tool.data.tcp.api.ControlListener;
import com.agioe.tool.data.tcp.api.DefaultTcpApiInstance;
import com.agioe.tool.data.tcp.api.TcpApi;
import com.agioe.tool.data.tcp.server.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yshen
 * @since 2018/9/27
 */
@SpringBootApplication
@EnableScheduling
@EnableWebSocketMessageBroker
@MapperScan("com.agioe.tool.data.dao")
public class DataToolApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DataToolApplication.class);


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ControlListener myControlListener;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DataToolApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }


    @Override
    public void run(String... args) throws Exception {
        //通信服务端初始化
        Server.init();
    }

    /**
     * 推送日志到/topic/pullLogger
     */
    @PostConstruct
    public void pushLogger() {
        logger.info("开启日志推送");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            while (true) {
                try {
                    LoggerMessage log = LoggerQueue.getInstance().poll();
                    if (log != null) {
                        if (messagingTemplate != null) {
                            messagingTemplate.convertAndSend("/topic/pullLogger", log);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(runnable);
    }


    @Bean
    public TcpComponent tcpComponent() {
        TcpApi instance = new DefaultTcpApiInstance(myControlListener);
        return new TcpComponent(instance);
    }
}
