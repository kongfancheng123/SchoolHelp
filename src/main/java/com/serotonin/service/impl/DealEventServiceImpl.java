package com.serotonin.service.impl;

import com.serotonin.entity.*;
import com.serotonin.service.*;
import com.serotonin.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by fchkong on 2018/11/15.
 */
@Service
public class DealEventServiceImpl implements DealEventService {
//    private Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

    @Autowired
    private GetKey getKey;
    @Autowired
    private CollectorInfoService collectorInfoService;
    @Autowired
    private ContainerInfoService containerInfoService;
    @Autowired
    private RealtimeEventService realtimeEventService;
    @Autowired
    private HisEventService hisEventService;

    @Override
    public void dealEvent(Integer i, String result1, String com, Integer deviceNum, Map<String, RealtimeEvent> faultMap) {
        try {
            String alarm = result1.substring(0, 1);
            if (alarm.equals("0")) {//无报警
                //不做处理
            } else {//有报警
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                this.storeData(key, 0, 1, "发生报警");
//                logger.info("报警处理中.........");
            }
            String falut = result1.substring(1, 2);
            if (falut.equals("0")) {//无故障

                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,无就进行不进行操作,有就改变将故障存放在历史表中
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                RealtimeEvent realtimeEvent2 = faultMap.get(key);
                if (realtimeEvent2 != null && realtimeEvent2.getEventType() == 1) {
                    realtimeEventService.deleteRealtimeEvent(realtimeEvent2.getId());
                    //todo:添加到历史表中,先查询是否存在表
                    CreateTableParam createTableParam = this.getCreateTableParam();
                    Integer integer = hisEventService.selectTable(createTableParam);
                    if (integer <= 0) {
                        realtimeEventService.createTable(createTableParam);
                    }
                    //插入数据
                    HisEvent hisEvent = new HisEvent();
                    hisEvent.setDevCode(realtimeEvent2.getDevCode());
                    hisEvent.setIsConfirm(1);
                    hisEvent.setDevType(realtimeEvent2.getDevType());
                    hisEvent.setEventType(realtimeEvent2.getEventType());
                    hisEvent.setLowMachineKey(realtimeEvent2.getLowMachineKey());
                    hisEvent.setEventDetail(realtimeEvent2.getEventDetail());
                    hisEvent.setEventSubType(realtimeEvent2.getEventSubType());
                    hisEvent.setEventTime(realtimeEvent2.getEventTime());
                    hisEvent.setConfirmOperator("SYSTEM");
                    hisEvent.setConfirmDetail("系统确认");
                    hisEvent.setConfirmTime(new Date());
                    hisEvent.setIsSync(realtimeEvent2.getIsSync());
                    hisEvent.setUpdateTime(new Date());
                    hisEventService.insertHisEvent(hisEvent, createTableParam);
                }
            } else {//有故障
                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,有就不存储,无就进行存储
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                this.storeData(key, 1, 0, "发生故障");
//                logger.info("有故障,key为:" + key);
//                logger.info(falut);
//                logger.info("故障处理中.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeData(String key, Integer i, Integer j, String detail) {
        CollectorInfo collectorInfo = new CollectorInfo();
        collectorInfo.setLowMachineKey(key);
//        System.out.println("有报警,对数据库进行处理,key为:" + key);
        List<CollectorInfo> collectorInfos = collectorInfoService.selectByCollectorInfo(collectorInfo);
        if (collectorInfos.size() > 0) {
            CollectorInfo collectorInfo1 = collectorInfos.get(0);
            String containerCode = collectorInfo1.getContainerCode();
            ContainerInfo containerInfo = new ContainerInfo();
            containerInfo.setContainerCode(containerCode);
            List<ContainerInfo> containerInfos = containerInfoService.selectByContainerInfo(containerInfo);
            RealtimeEvent realtimeEvent = new RealtimeEvent();
            realtimeEvent.setDevCode(collectorInfo1.getContainerCode());
            realtimeEvent.setDevType(400);
            realtimeEvent.setEventType(i);
            realtimeEvent.setLowMachineKey(key);
            List<RealtimeEvent> realtimeEvents = realtimeEventService.selectByRealtimeEvent(realtimeEvent);
            if (containerInfos.size() > 0) {
                ContainerInfo containerInfo1 = containerInfos.get(0);
                realtimeEvent.setEventDetail(containerInfo1.getContainerName() + detail);
            }
            realtimeEvent.setEventSubType(j);
            realtimeEvent.setEventTime(new Date());
            realtimeEvent.setIsSync(0);
            realtimeEvent.setIsConfirm(0);
            realtimeEvent.setUpdateTime(new Date());
            if (realtimeEvents.size() > 0) {//有就不管
//                不做处理
            } else {//没有就新增
                realtimeEventService.insertRealtimeEvent(realtimeEvent);
            }

        }
    }

    @Override
    public CreateTableParam getCreateTableParam() {
        CreateTableParam createTableParam = new CreateTableParam();
        createTableParam.setOriginalTableName("realtime_event");
        String format = TimeUtil.format(new Date(), "yyyy-MM-dd");
        String[] split = format.split("-");
        String tableName = "hisevent_" + split[0] + split[1];
        createTableParam.setTableName(tableName);
        createTableParam.setSeqName("seq_" + tableName);
        return createTableParam;
    }

    @Override
    public void storeCommuData1(HostInfo hostInfo) {//查询实时表,有则删除,并移除到历史表中,没有的话不做任何操作
        //查询实时表
        RealtimeEvent realtimeEvent = new RealtimeEvent();
        realtimeEvent.setDevCode(hostInfo.getHostCode());
        List<RealtimeEvent> realtimeEvents = realtimeEventService.selectByRealtimeEvent(realtimeEvent);
        if (realtimeEvents.size() > 0) {
            RealtimeEvent realtimeEvent1 = realtimeEvents.get(0);
            realtimeEventService.deleteRealtimeEvent(realtimeEvent1.getId());
            //获取历史表参数
            CreateTableParam createTableParam = this.getCreateTableParam();
            //进行历史表存储
            HisEvent hisEvent = new HisEvent();
            hisEvent.setUpdateTime(new Date());
            hisEvent.setIsSync(0);
            hisEvent.setIsConfirm(1);
            hisEvent.setConfirmOperator(realtimeEvent1.getConfirmOperator());
            hisEvent.setConfirmDetail(realtimeEvent1.getConfirmDetail());
            hisEvent.setConfirmTime(realtimeEvent1.getConfirmTime());
            hisEvent.setDevCode(realtimeEvent1.getDevCode());
            hisEvent.setDevType(realtimeEvent1.getDevType());
            hisEvent.setEventType(1);
            hisEventService.insertHisEvent(hisEvent, createTableParam);
        }
    }

    @Override
    public void storeCommuData2(HostInfo hostInfo) {//查询实时表,有不操作,没有就进行添加操作
        RealtimeEvent realtimeEvent = new RealtimeEvent();
        realtimeEvent.setDevCode(hostInfo.getHostCode());
        List<RealtimeEvent> realtimeEvents = realtimeEventService.selectByRealtimeEvent(realtimeEvent);
        if (realtimeEvents.size() <= 0) {
            RealtimeEvent realtimeEvent1 = new RealtimeEvent();
            //进行历史表存储
            realtimeEvent1.setUpdateTime(new Date());
            realtimeEvent1.setIsSync(0);
            realtimeEvent1.setIsConfirm(0);
            realtimeEvent1.setConfirmOperator("SYSTEM");
            realtimeEvent1.setConfirmDetail("系统确认");
            realtimeEvent1.setConfirmTime(new Date());
            realtimeEvent1.setDevCode(hostInfo.getHostCode());
            realtimeEvent1.setEventType(1);
            realtimeEvent1.setDevType(1000);
            realtimeEventService.insertRealtimeEvent(realtimeEvent1);
        }
    }
}
