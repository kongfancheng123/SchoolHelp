package com.serotonin.service.impl;

import com.serotonin.entity.*;
import com.serotonin.service.*;
import com.serotonin.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by fchkong on 2018/11/15.
 */
@Service
public class DealEventServiceImpl implements DealEventService {
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
    public void dealEvent(Integer i, String result1, String com, Integer deviceNum) {
        try {
            String alarm = result1.substring(0, 1);
            if (alarm.equals("0")) {//无报警
                //不做处理
            } else {//有报警
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                this.storeData(key, 0, 1, "发生报警");
                System.out.println("报警处理中.........");
            }
            String falut = result1.substring(1, 2);
            if (falut.equals("0")) {//无故障
                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,无就进行不进行操作,有就改变将故障存放在历史表中
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                CollectorInfo collectorInfo = new CollectorInfo();
                collectorInfo.setLowMachineKey(key);
                List<CollectorInfo> collectorInfos = collectorInfoService.selectByCollectorInfo(collectorInfo);
                if (collectorInfos.size() > 0) {
                    CollectorInfo collectorInfo1 = collectorInfos.get(0);
                    RealtimeEvent realtimeEvent = new RealtimeEvent();
                    realtimeEvent.setDevCode(collectorInfo1.getCollectorCode());
                    realtimeEvent.setDevType(collectorInfo1.getCollectorType());
                    realtimeEvent.setEventType(1);
                    realtimeEvent.setLowMachineKey(key);
                    List<RealtimeEvent> realtimeEvents = realtimeEventService.selectByRealtimeEvent(realtimeEvent);
                    if (realtimeEvents.size() > 0) {
                        RealtimeEvent realtimeEvent1 = realtimeEvents.get(0);
                        realtimeEventService.deleteRealtimeEvent(realtimeEvent1.getId());
                        //todo:添加到历史表中,先查询是否存在表
                        CreateTableParam createTableParam = this.getCreateTableParam();
                        Integer integer = hisEventService.selectTable(createTableParam);
                        if (integer <= 0) {
                            realtimeEventService.createTable(createTableParam);
                        }
                        //插入数据
                        HisEvent hisEvent = new HisEvent();
                        hisEvent.setDevCode(realtimeEvent1.getDevCode());
                        hisEvent.setIsConfirm(1);
                        hisEvent.setDevType(realtimeEvent1.getDevType());
                        hisEvent.setEventType(realtimeEvent1.getEventType());
                        hisEvent.setLowMachineKey(realtimeEvent1.getLowMachineKey());
                        hisEvent.setEventDetail(realtimeEvent1.getEventDetail());
                        hisEvent.setEventSubType(realtimeEvent1.getEventSubType());
                        hisEvent.setEventTime(realtimeEvent1.getEventTime());
                        hisEvent.setIsSync(realtimeEvent1.getIsSync());
                        hisEvent.setUpdateTime(new Date());
                        hisEventService.insertHisEvent(hisEvent, createTableParam);
                    }
                }
                System.out.println("无故障,key为:" + key);
                System.out.println(falut);
            } else {//有故障
                //todo:对数据库进行处理
                //todo:先查询数据库实时表中是否有这条数据,有就不存储,无就进行存储
                //获取key
                String key = getKey.getKey(com, i, deviceNum);
                this.storeData(key, 1, 0, "发生故障");
                System.out.println("有故障,key为:" + key);
                System.out.println(falut);
                System.out.println("故障处理中.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void storeData(String key, Integer i, Integer j, String detail) {
        CollectorInfo collectorInfo = new CollectorInfo();
        collectorInfo.setLowMachineKey(key);
        System.out.println("有报警,对数据库进行处理,key为:" + key);
//                Thread.sleep(1000);
        List<CollectorInfo> collectorInfos = collectorInfoService.selectByCollectorInfo(collectorInfo);
        if (collectorInfos.size() > 0) {
            CollectorInfo collectorInfo1 = collectorInfos.get(0);
            String containerCode = collectorInfo1.getContainerCode();
            ContainerInfo containerInfo = new ContainerInfo();
            containerInfo.setContainerCode(containerCode);
            List<ContainerInfo> containerInfos = containerInfoService.selectByContainerInfo(containerInfo);
            RealtimeEvent realtimeEvent = new RealtimeEvent();
            realtimeEvent.setDevCode(collectorInfo1.getCollectorCode());
            realtimeEvent.setDevType(collectorInfo1.getCollectorType());
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
}
