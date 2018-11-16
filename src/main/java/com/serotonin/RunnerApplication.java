package com.serotonin;


import com.serotonin.service.RunService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RunService runService;

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(RunnerApplication.class);
        application.run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        runService.tcpRun();
    }
}
