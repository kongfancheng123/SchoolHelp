package com.serotonin;


import com.serotonin.service.*;
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

    @Autowired
    private DealEventService dealEventService;

    @Autowired
    private HisEventService hisEventService;

    @Autowired
    private RealtimeEventService realtimeEventService;

    @Autowired
    private ParamEntryService paramEntryService;

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(RunnerApplication.class);
        application.run(args);
    }


    @Override
    public void run(String... args) {
        paramEntryService.paramEntry();

//        try {
//            //参数
//            IpParameters params = new IpParameters();
//            //获取主机串口编号
//            String com = "001";
//            //从站所在地址
//            params.setHost("192.168.52.50");
//            //从站端口
//            params.setPort(502);
//            //创建主站
//            ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);
//            master.init();
//            boolean isCoon = master.testSlaveNode(0);
//            System.out.println("=============================="+isCoon);
//            NumericLocator el = new NumericLocator(2, RegisterRange.HOLDING_REGISTER, 10, DataType.TWO_BYTE_INT_UNSIGNED);
//            while (true) {
//                for (int i = 0; i < 10; i++) {
//                    Thread.sleep(1000);
//                    try {
//                        System.out.println("el: " + master.getValue(el));
//                        boolean isCoon1 = master.testSlaveNode(0);
//                        System.out.println("=============================="+isCoon);
////                System.out.println("fjk: " + master.getValue(fjk));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        boolean isCoon1 = master.testSlaveNode(0);
//                        System.out.println("=============================="+isCoon);
//                    }
//                }
//            }
//        }catch (Exception e){
//            logger.info("连接失败");
//        }


    }
}
