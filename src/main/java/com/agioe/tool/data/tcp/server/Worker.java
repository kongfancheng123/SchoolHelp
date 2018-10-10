package com.agioe.tool.data.tcp.server;

import com.agioe.tool.data.tcp.MessageDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.agioe.tool.data.common.PropertiesConfig.HEARTBEAT_INTERVAL;

/**
 * @author yshen
 * @since 2018/9/30
 */
public class Worker implements Runnable {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    private static ChannelFuture channelFuture = null;

    private int port;
    /**
     * 定时线程
     */
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public Worker(int port) {
        this.port = port;
    }

    /**
     * 发送数据
     *
     * @param buf
     */
    public static void send(ByteBuf buf) {
        logger.info("准备发送数据:{}", ByteBufUtil.hexDump(buf));
        channelFuture.channel().writeAndFlush(buf);
    }

    @Override
    public void run() {
        bind();
        logger.info("服务端通信线程加载完毕");
    }

    public void bind() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, 0));
                            ch.pipeline().addLast(new MessageDecoder(Integer.MAX_VALUE, 4, 4));
                            ch.pipeline().addLast(new ByteArrayEncoder());
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            //todo 释放资源
            logger.error("通信连接线程中断异常", e);
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        } finally {
            //重连
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    bind();
                } catch (InterruptedException e) {
                    logger.error("通信连接线程重连异常", e);
                }
            });
        }
    }
}

