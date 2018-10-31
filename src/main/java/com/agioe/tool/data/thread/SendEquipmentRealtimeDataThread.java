package com.agioe.tool.data.thread;

import com.agioe.tool.data.dao.EquipmentInfoDao;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.MonitorProperty;
import com.agioe.tool.data.service.MonitorPropertyService;
import com.agioe.tool.data.singleton.TcpApiSingleton;
import com.agioe.tool.data.tcp.api.DefaultTcpApiInstance;
import com.agioe.tool.data.tcp.payload.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SendEquipmentRealtimeDataThread implements Runnable {
    private String[][] propertyCodeAndValue;
    private MonitorPropertyService monitorPropertyService;
    private String parentNodeCode;
    private String equipmentType;
    private String equipmentPropertyTemplateCode;
    private EquipmentInfoDao equipmentInfoDao;
    private Integer feedCycle;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SendEquipmentRealtimeDataThread(String[][] propertyCodeAndValue, MonitorPropertyService monitorPropertyService, String parentNodeCode, String equipmentType, String equipmentPropertyTemplateCode, EquipmentInfoDao equipmentInfoDao, Integer feedCycle) {
        this.propertyCodeAndValue = propertyCodeAndValue;
        this.monitorPropertyService = monitorPropertyService;
        this.parentNodeCode = parentNodeCode;
        this.equipmentType = equipmentType;
        this.equipmentPropertyTemplateCode = equipmentPropertyTemplateCode;
        this.equipmentInfoDao = equipmentInfoDao;
        this.feedCycle = feedCycle;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                logger.info("上传周期为:"+String.valueOf(feedCycle)+"毫秒");
                Thread.currentThread().sleep(feedCycle);
                for (String[] propertyCodeAndValue1 : propertyCodeAndValue) {
                    boolean isIn = Thread.currentThread().isInterrupted();
                    System.out.println(isIn);
                    if (isIn) {
                        logger.info("准备停止发送");
                        break;
                    }
                    String equipmentPropertyCode = propertyCodeAndValue1[0];
                    //获取信号类型
                    Integer equipmentPropertyType = 0;
                    MonitorProperty monitorProperty = new MonitorProperty();
                    monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                    List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                    if (monitorProperties.size() > 0) {
                        MonitorProperty monitorProperty1 = monitorProperties.get(0);
                        equipmentPropertyType = monitorProperty1.getEquipmentPropertyType();
                    }
                    String baseValue = propertyCodeAndValue1[1];
                    String upAndDown = propertyCodeAndValue1[2];
                    EquipmentInfo equipmentInfo = new EquipmentInfo();
                    equipmentInfo.setParentNodeCode(parentNodeCode);
                    equipmentInfo.setEquipmentType(equipmentType);
                    equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                    equipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
                    List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
                    List<SensorData> dataList = new ArrayList<>();
                    if (equipmentInfos.size() > 0) {
                        for (EquipmentInfo equipmentInfo1 : equipmentInfos) {
                            String dataVal = "";
                            Double baseValueDouble = Double.valueOf(baseValue);
                            Double upAndDownDouble = Double.valueOf(upAndDown);
                            Double minBaseValue = baseValueDouble - upAndDownDouble;
                            //获取信号类型
                            //todo:获取随机的数据
                            Random random = new Random();
                            Double dataValDouble1 = random.nextDouble() * upAndDownDouble * 2 + minBaseValue;
                            //todo:保留两位小数
                            BigDecimal b = new BigDecimal(dataValDouble1);
                            Double dataValDouble = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            if (equipmentPropertyType == 0) {//信号类型为0,浮点数
                                dataVal = String.valueOf(dataValDouble);
                            } else {//信号类型为1,只能为0或者1
                                int i = random.nextInt(2);
                                dataVal = String.valueOf(i);
                            }
                            Date dataUpdate = new Date();
                            equipmentInfo1.setDataVal(dataVal);
                            equipmentInfo1.setDataUpdate(dataUpdate);
                            equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
                            SensorData sensorData = new SensorData();
                            sensorData.setKey(equipmentInfo1.getKeyword());
                            sensorData.setOrg("");
                            sensorData.setTime(dataUpdate.getTime());
                            sensorData.setType(Byte.decode(String.valueOf(equipmentPropertyType)));
                            sensorData.setVal(dataVal);
                            dataList.add(sensorData);
                        }
                    }
                    //todo:发送实时数据
                    DefaultTcpApiInstance defaultTcpApiInstance = TcpApiSingleton.getDefaultTcpApiInstance();
                    defaultTcpApiInstance.sendSensorData(dataList);
                }
            }
        } catch (InterruptedException e) {
            boolean isIn = Thread.currentThread().isInterrupted();
            return;
        }
    }
}
