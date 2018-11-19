import com.serotonin.RunnerApplication;
import com.serotonin.entity.RealtimeEvent;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.service.DealEventService;
import com.serotonin.service.HostInfoService;
import com.serotonin.service.RealtimeEventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RunnerApplication.class})
public class ApplicationTest {

    @Resource
    private HostInfoService hostInfoService;

    @Resource
    private DealEventService dealEventService;

    @Autowired
    private RealtimeEventService realtimeEventService;

    @Test
    public void testExample() throws ModbusInitException, ErrorResponseException, ModbusTransportException, InterruptedException {
//        //todo:数据库获取主机地址以及端口
//        HostInfo hostInfo = new HostInfo();
//        hostInfo.setHostCode("FP9000C_1");
//        //参数
//        IpParameters params = new IpParameters();
//        List<HostInfo> hostInfos = hostInfoService.selectByHostInfo(hostInfo);
//        if (hostInfos.size() > 0) {
//            for (HostInfo hostInfo1 : hostInfos) {
//                //从站所在地址
//                params.setHost(hostInfo1.getHostAddr());
//                //从站端口
//                params.setPort(Integer.valueOf(hostInfo1.getDefault1()));
//                //创建主站
//                ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);
//                //启动主站,创建连接
//                master.init();
//                //todo:捕获异常,如果有异常,代表连接失败,要进行重新连接
//                //测试数据0是否连接
//                System.out.println(master.testSlaveNode(0));
//                while (true) {
////                    for (int i = 10; i < 20; i++) {//起始和最终地址可配
//                        //获取当前设备的key
////                        BaseLocator<Number> loc1 = BaseLocator.holdingRegister(1, i, DataType.TWO_BYTE_INT_UNSIGNED);
////                        Number value1 = master.getValue(loc1);
////                        String result1 = DecimalToBinary.decimalToBinary(value1);
////                        String substring1 = result1.substring(2, 4);
////                        String key1 = "";
////                        dealEventService.dealEvent(key1, substring1);
////                        String substring2 = result1.substring(6, 8);
////                        String key2 = "";
////                        dealEventService.dealEvent(key2, substring2);
////                        String substring3 = result1.substring(10, 12);
////                        String key3 = "";
////                        dealEventService.dealEvent(key3, substring3);
////                        String substring4 = result1.substring(14, 16);
////                        String key4 = "";
////                        dealEventService.dealEvent(key4, substring4);
////                    }
//                    System.out.println("停止获取数据,休息中..........");
//                    Thread.sleep(5000);
//                }
//            }
//        }

        RealtimeEvent realtimeEvent = new RealtimeEvent();
        realtimeEvent.setId(1);
        List<RealtimeEvent> realtimeEvents = realtimeEventService.selectByRealtimeEvent(realtimeEvent);
        System.out.println(realtimeEvents.size());
    }
}
