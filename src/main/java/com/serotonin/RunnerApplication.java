package com.serotonin;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author yshen
 * @since 2018/9/27
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.serotonin.dao")
public class RunnerApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(RunnerApplication.class);


    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(RunnerApplication.class);
        application.run(args);
//        SpringApplication.run(RunnerApplication.class, args);
    }


    @Override
    public void run(String... args) {
        //进程 holder
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
