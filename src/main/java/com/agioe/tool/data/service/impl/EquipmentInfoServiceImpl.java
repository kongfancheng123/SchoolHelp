package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateAndParentNodeVo;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateVo;
import com.agioe.tool.data.Vo.ShowAllEquipmentInfoVo;
import com.agioe.tool.data.dao.EquipmentInfoDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.service.*;
import com.agioe.tool.data.tcp.api.TcpApi;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.SensorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipmentInfoServiceImpl implements EquipmentInfoService {
    @Autowired
    private EquipmentInfoDao equipmentInfoDao;
    @Autowired
    private EquipmentRealtimeDataService equipmentRealtimeDataService;
    @Autowired
    private MonitorPropertyTemplateBindService monitorPropertyTemplateBindService;
    @Autowired
    private MonitorPropertyService monitorPropertyService;
    @Autowired
    private ParentNodeService parentNodeService;
    @Autowired
    private TcpApi tcpApi;


    @Override
    public Integer insertEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.insertEquipmentInfo(equipmentInfo);
    }

    @Override
    public Integer deleteEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.deleteEquipmentInfo(equipmentInfo);
    }

    @Override
    public List<EquipmentInfo> selectByEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
    }

    @Override
    public Integer updateEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.updateEquipmentInfo(equipmentInfo);
    }

    @Override
    public List<EquipmentInfo> selectAll(String parentNodeCode) {
        return equipmentInfoDao.selectAll(parentNodeCode);
    }

    @Override
    public EquipmentInfo selectByid(Integer id) {
        return equipmentInfoDao.selectByid(id);
    }

    @Override
    public Integer createTable(CreateTableParam createTableParam) {
        return equipmentInfoDao.createTable(createTableParam);
    }

    @Override
    public WebResponse showAllEquipmentInfo() {
        List<ShowAllEquipmentInfoVo> showAllEquipmentInfoVos = new ArrayList<>();
        List<ParentNode> parentNodes = parentNodeService.selectAll();
        if (parentNodes.size() > 0) {
            for (ParentNode parentNode : parentNodes) {
                //获取所有的parentNodeCode
                List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectAll(parentNode.getParentNodeCode());
                if (EquipmentInfos.size() > 0) {
                    for (EquipmentInfo EquipmentInfo : EquipmentInfos) {
                        ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();
                        showAllEquipmentInfoVo.setEquipmentCode(EquipmentInfo.getEquipmentCode());
                        showAllEquipmentInfoVo.setEquipmentName(EquipmentInfo.getEquipmentName());
                        showAllEquipmentInfoVo.setEquipmentPropertyCode(EquipmentInfo.getEquipmentPropertyCode());
                        showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(EquipmentInfo.getEquipmentPropertyTemplateCode());
                        showAllEquipmentInfoVo.setEquipmentType(EquipmentInfo.getEquipmentType());
                        showAllEquipmentInfoVo.setKeyword(EquipmentInfo.getKeyword());
                        showAllEquipmentInfoVo.setParentNodeCode(EquipmentInfo.getParentNodeCode());
                        showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
                    }
                }
            }
        }
        return WebResponse.success(showAllEquipmentInfoVos);
    }

    @Override
    public WebResponse addEquipmentInfo1(AddEquipmentInfo1Qo addEquipmentInfo1Qo) {
        String equipmentPropertyTemplateCode = addEquipmentInfo1Qo.getEquipmentPropertyTemplateCode();
        String equipmentType = addEquipmentInfo1Qo.getEquipmentType();
        String parentNodeCode = addEquipmentInfo1Qo.getParentNodeCode();
        Integer equipmentNumber = addEquipmentInfo1Qo.getEquipmentNumber();

        String equipmentCodeBefore = addEquipmentInfo1Qo.getEquipmentCodeBefore();
        Integer equipmentCodeStart = addEquipmentInfo1Qo.getEquipmentCodeStart();
        String equipmentNameAfter = addEquipmentInfo1Qo.getEquipmentNameAfter();
        String equipmentNameBefore = addEquipmentInfo1Qo.getEquipmentNameBefore();
        Integer equipmentNameStart = addEquipmentInfo1Qo.getEquipmentNameStart();


        //根据模板编码和设备类型查找出属性集合
        GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo = new GetPropertyByTypeAndTemplateQo();
        getPropertyByTypeAndTemplateQo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        getPropertyByTypeAndTemplateQo.setEquipmentTypeCode(equipmentType);
        List<GetPropertyByTypeAndTemplateVo> propertyByTypeAndTemplate = monitorPropertyService.getPropertyByTypeAndTemplate(getPropertyByTypeAndTemplateQo);
        for (int i = 0; i < equipmentNumber; i++) {
            //获取设备编码
            String equipmentCodeStart1 = String.valueOf(equipmentCodeStart + i);
            String equipmentCode = equipmentCodeBefore + equipmentCodeStart1;
            //获取设备名称
            String equipmentNameStart1 = String.valueOf(equipmentNameStart + i);
            String equipmentName = equipmentNameBefore + equipmentNameStart1 + equipmentNameAfter;
            //编码判重
            EquipmentInfo EquipmentInfo = new EquipmentInfo();
            EquipmentInfo.setEquipmentCode(equipmentCode);
            List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
            if (EquipmentInfos.size() > 0) {
                return WebResponse.error(400, "设备编码已存在");
            }
            //名字判重
            EquipmentInfo EquipmentInfo1 = new EquipmentInfo();
            EquipmentInfo1.setEquipmentName(equipmentName);
            List<EquipmentInfo> EquipmentInfos1 = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo1);
            if (EquipmentInfos1.size() > 0) {
                return WebResponse.error(400, "设备名已存在");
            }
            //添加
            if (propertyByTypeAndTemplate.size() > 0) {
                for (GetPropertyByTypeAndTemplateVo getPropertyByTypeAndTemplateVo : propertyByTypeAndTemplate) {
                    EquipmentInfo equipmentInfo2 = new EquipmentInfo();
                    equipmentInfo2.setEquipmentName(equipmentName);
                    equipmentInfo2.setEquipmentCode(equipmentCode);
                    equipmentInfo2.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                    equipmentInfo2.setEquipmentPropertyCode(getPropertyByTypeAndTemplateVo.getEquipmentPropertyCode());
                    equipmentInfo2.setEquipmentType(equipmentType);
                    equipmentInfo2.setParentNodeCode(parentNodeCode);
                    //设置其余值为空
                    equipmentInfo2.setAlarmVal("");
                    equipmentInfo2.setControlVal("");
                    equipmentInfo2.setDataVal("");
                    equipmentInfo2.setKeyword("");
                    equipmentInfoDao.insertEquipmentInfo(equipmentInfo2);
                }
            } else {
                EquipmentInfo equipmentInfo2 = new EquipmentInfo();
                equipmentInfo2.setEquipmentName(equipmentName);
                equipmentInfo2.setEquipmentCode(equipmentCode);
                equipmentInfo2.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                equipmentInfo2.setEquipmentType(equipmentType);
                equipmentInfo2.setParentNodeCode(parentNodeCode);
                //设置其余值为空
                equipmentInfo2.setEquipmentPropertyCode("");
                equipmentInfo2.setAlarmVal("");
                equipmentInfo2.setControlVal("");
                equipmentInfo2.setDataVal("");
                equipmentInfo2.setKeyword("");
                equipmentInfoDao.insertEquipmentInfo(equipmentInfo2);
            }
        }

//        //key值判断重复
//        EquipmentInfo EquipmentInfo = new EquipmentInfo();
//        EquipmentInfo.setKeyword(keyword);
//        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
//        if (EquipmentInfos.size() > 0) {
//            return WebResponse.error(400, "key值已存在");
//        }

        return WebResponse.success();
    }

    @Override
    public WebResponse updateEquipmentInfo1(UpdateEquipmentInfo1Qo updateEquipmentInfo1Qo) {//待修改
        Integer id = updateEquipmentInfo1Qo.getId();
        String equipmentCode = updateEquipmentInfo1Qo.getEquipmentCode();
        String equipmentName = updateEquipmentInfo1Qo.getEquipmentName();
        String equipmentPropertyCode = updateEquipmentInfo1Qo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = updateEquipmentInfo1Qo.getEquipmentPropertyTemplateCode();
        String equipmentType = updateEquipmentInfo1Qo.getEquipmentType();
        String keyword = updateEquipmentInfo1Qo.getKeyword();
        String parentNodeCode = updateEquipmentInfo1Qo.getParentNodeCode();
        EquipmentInfo EquipmentInfo = equipmentInfoDao.selectByid(id);
        EquipmentInfo.setParentNodeCode(parentNodeCode);
        EquipmentInfo.setKeyword(keyword);
        EquipmentInfo.setEquipmentType(equipmentType);
        EquipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
        EquipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        EquipmentInfo.setEquipmentCode(equipmentCode);
        EquipmentInfo.setEquipmentName(equipmentName);
        equipmentInfoDao.updateEquipmentInfo(EquipmentInfo);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteEquipmentInfo1(DeleteEquipmentInfo1Qo deleteEquipmentInfo1Qo) {
        String keyWord = deleteEquipmentInfo1Qo.getKeyWord();
        String parentNodeCode = deleteEquipmentInfo1Qo.getParentNodeCode();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setKeyword(keyWord);
        equipmentInfo.setParentNodeCode(parentNodeCode);
        //删除
        equipmentInfoDao.deleteEquipmentInfo(equipmentInfo);
        return WebResponse.success();
    }

    @Override
    public WebResponse showEquipmentInfoByCondition(ShowEquipmentInfoByConditionQo showEquipmentInfoByConditionQo) {
        List<ShowAllEquipmentInfoVo> showAllEquipmentInfoVos = new ArrayList<>();

        String equipmentPropertyCode = showEquipmentInfoByConditionQo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = showEquipmentInfoByConditionQo.getEquipmentPropertyTemplateCode();
        String equipmentType = showEquipmentInfoByConditionQo.getEquipmentType();
        String parentNodeCode = showEquipmentInfoByConditionQo.getParentNodeCode();
        if (parentNodeCode == "全部") {//查询所有的表
            //获取所有的parentNodeCode
            List<ParentNode> parentNodes = parentNodeService.selectAll();
            if (parentNodes.size() > 0) {
                for (ParentNode parentNode : parentNodes) {
                    String parentNodeCode1 = parentNode.getParentNodeCode();
                    EquipmentInfo EquipmentInfo = new EquipmentInfo();
                    EquipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode == "" ? null : equipmentPropertyTemplateCode);
                    EquipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode == "" ? null : equipmentPropertyCode);
                    EquipmentInfo.setEquipmentType(equipmentType == "" ? null : equipmentType);
                    EquipmentInfo.setParentNodeCode(parentNodeCode1 == "" ? null : parentNodeCode);
                    List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
                    if (EquipmentInfos.size() > 0) {
                        for (EquipmentInfo EquipmentInfo2 : EquipmentInfos) {
                            ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();
                            showAllEquipmentInfoVo.setEquipmentCode(EquipmentInfo2.getEquipmentCode());
                            showAllEquipmentInfoVo.setEquipmentName(EquipmentInfo2.getEquipmentName());
                            showAllEquipmentInfoVo.setEquipmentPropertyCode(EquipmentInfo2.getEquipmentPropertyCode());
                            showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(EquipmentInfo2.getEquipmentPropertyTemplateCode());
                            showAllEquipmentInfoVo.setEquipmentType(EquipmentInfo2.getEquipmentType());
                            showAllEquipmentInfoVo.setKeyword(EquipmentInfo2.getKeyword());
                            showAllEquipmentInfoVo.setParentNodeCode(EquipmentInfo2.getParentNodeCode());
                            showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
                        }
                    }
                }
            }
        } else {
            EquipmentInfo EquipmentInfo = new EquipmentInfo();
            EquipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode == "" ? null : equipmentPropertyTemplateCode);
            EquipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode == "" ? null : equipmentPropertyCode);
            EquipmentInfo.setEquipmentType(equipmentType == "" ? null : equipmentType);
            EquipmentInfo.setParentNodeCode(parentNodeCode == "" ? null : parentNodeCode);
            List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
            if (EquipmentInfos.size() > 0) {
                for (EquipmentInfo EquipmentInfo2 : EquipmentInfos) {
                    ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();
                    showAllEquipmentInfoVo.setEquipmentCode(EquipmentInfo2.getEquipmentCode());
                    showAllEquipmentInfoVo.setEquipmentName(EquipmentInfo2.getEquipmentName());
                    showAllEquipmentInfoVo.setEquipmentPropertyCode(EquipmentInfo2.getEquipmentPropertyCode());
                    showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(EquipmentInfo2.getEquipmentPropertyTemplateCode());
                    showAllEquipmentInfoVo.setEquipmentType(EquipmentInfo2.getEquipmentType());
                    showAllEquipmentInfoVo.setKeyword(EquipmentInfo2.getKeyword());
                    showAllEquipmentInfoVo.setParentNodeCode(EquipmentInfo2.getParentNodeCode());
                    showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
                }
            }
        }

        return WebResponse.success(showAllEquipmentInfoVos);
    }

    @Override
    public WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo) {
        String dataValue = sendEquipmentRealtimeDataQo.getDataValue();
        String keyword = sendEquipmentRealtimeDataQo.getKeyword();
        String parentNodeCode = sendEquipmentRealtimeDataQo.getParentNodeCode();
        //对key值对应的设备表进行更新
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setKeyword(keyword);
        equipmentInfo.setParentNodeCode(parentNodeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            EquipmentInfo equipmentInfo1 = equipmentInfos.get(0);
            equipmentInfo1.setDataVal(dataValue);
            equipmentInfo1.setDataUpdate(new Date());
            equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
        }
        //todo:发送实时数据
        List<SensorData> dataList = new ArrayList<>();
        tcpApi.sendSensorData(dataList);
        return WebResponse.success();
    }

    @Override
    public WebResponse sendEventHistory(SendEventHistoryQo sendEventHistoryQo) {
        String eventCode = sendEventHistoryQo.getEventCode();
        String eventType = sendEventHistoryQo.getEventType();
        String eventValue = sendEventHistoryQo.getEventValue();
        String parentNodeCode = sendEventHistoryQo.getParentNodeCode();
        Integer equipmentPropertyType = sendEventHistoryQo.getEquipmentPropertyType();
        //todo:先对值进行校验
//        if(equipmentPropertyType==0){//为浮点型
//            String pattern = "(\\D*)(\\d+)(.*)";
//            if(){
//
//            }
//        }else if(equipmentPropertyType==1){//为整型,只能为0和1
//            if(){
//
//            }
//        }else{
//
//        }

        String keyword = sendEventHistoryQo.getKeyword();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        String alarmVal = "事件类型:" + eventType + ",事件码:" + eventCode + ",事件值:" + eventValue;
        equipmentInfo.setKeyword(keyword);
        equipmentInfo.setParentNodeCode(parentNodeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);

        if (equipmentInfos.size() > 0) {
            Date alarmUpdate = new Date();
            EquipmentInfo equipmentInfo1 = equipmentInfos.get(0);
            equipmentInfo1.setAlarmVal(alarmVal);
            equipmentInfo1.setAlarmUpdate(alarmUpdate);
            equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
            //event_history==>Message==>ByteBuf==>发送
            //todo:发送事件
            List<SensorEvent> eventList = new ArrayList<>();
            SensorEvent sensorEvent = new SensorEvent();
            sensorEvent.setKey(equipmentInfo1.getKeyword());
            sensorEvent.setEventCode(Short.decode(eventCode));
            //事件值类型
            sensorEvent.setEventDataType(Byte.decode(String.valueOf(equipmentPropertyType)));
            sensorEvent.setEventDataValue(eventValue);
            sensorEvent.setEventTime(alarmUpdate.getTime());
            //事件类型
            sensorEvent.setType(Byte.decode(eventType));
            sensorEvent.setOrgCode("");
            eventList.add(sensorEvent);
            tcpApi.sendSensorEvent(eventList);
        }

        return WebResponse.success();
    }

    @Override
    public WebResponse dealEventHistory(DealEventHistoryQo dealEventHistoryQo) {
        String keyword = dealEventHistoryQo.getKeyword();
        String alarmVal = dealEventHistoryQo.getAlarmVal();
        //获取事件值和事件编码
        String[] split = alarmVal.split(":");
        //事件码
        String s = split[2];
        String[] split1 = s.split(",");
        String eventCode = split1[0];
        //事件值
        String eventValue = split[3];
        String parentNodeCode = dealEventHistoryQo.getParentNodeCode();
        Integer equipmentPropertyType = dealEventHistoryQo.getEquipmentPropertyType();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setKeyword(keyword);
        equipmentInfo.setParentNodeCode(parentNodeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            Date alarmUpdate = new Date();
            EquipmentInfo equipmentInfo1 = equipmentInfos.get(0);
            equipmentInfo1.setAlarmVal("");
            equipmentInfo1.setAlarmUpdate(alarmUpdate);
            equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
            //todo:发送消息
            List<SensorEvent> eventList = new ArrayList<>();
            SensorEvent sensorEvent = new SensorEvent();
            sensorEvent.setKey(equipmentInfo1.getKeyword());
            sensorEvent.setEventCode(Short.decode(eventCode));
            //事件值类型
            sensorEvent.setEventDataType(Byte.decode(String.valueOf(equipmentPropertyType)));
            sensorEvent.setEventDataValue(eventValue);
            sensorEvent.setEventTime(alarmUpdate.getTime());
            //事件类型
            sensorEvent.setType(Byte.decode("2"));
            sensorEvent.setOrgCode("");
            eventList.add(sensorEvent);
            tcpApi.sendSensorEvent(eventList);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse getPropertyByTypeAndTemplate(GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo) {
        List<GetPropertyByTypeAndTemplateVo> propertyByTypeAndTemplate = monitorPropertyService.getPropertyByTypeAndTemplate(getPropertyByTypeAndTemplateQo);
        return WebResponse.success(propertyByTypeAndTemplate);
    }

    @Override
    public WebResponse getPropertyByTypeAndTemplateAndParentNode(GetPropertyByTypeAndTemplateAndParentNodeQo getPropertyByTypeAndTemplateAndParentNodeQo) {
        String equipmentPropertyTemplateCode = getPropertyByTypeAndTemplateAndParentNodeQo.getEquipmentPropertyTemplateCode();
        String equipmentTypeCode = getPropertyByTypeAndTemplateAndParentNodeQo.getEquipmentTypeCode();
        String parentNodeCode = getPropertyByTypeAndTemplateAndParentNodeQo.getParentNodeCode();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setParentNodeCode(parentNodeCode);
        equipmentInfo.setEquipmentType(equipmentTypeCode);
        equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
        Set<String> propertyCodeSet = new HashSet<>();
        List<GetPropertyByTypeAndTemplateAndParentNodeVo> getPropertyByTypeAndTemplateAndParentNodeVos = new ArrayList<>();
        if (equipmentInfos.size() > 0) {
            for (EquipmentInfo equipmentInfo1 : equipmentInfos) {
                propertyCodeSet.add(equipmentInfo1.getEquipmentPropertyCode());
            }
        }
        if (propertyCodeSet.size() > 0) {
            for (String string : propertyCodeSet) {
                GetPropertyByTypeAndTemplateAndParentNodeVo getPropertyByTypeAndTemplateAndParentNodeVo = new GetPropertyByTypeAndTemplateAndParentNodeVo();
                getPropertyByTypeAndTemplateAndParentNodeVo.setEquipmentPropertyCode(string);
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(string);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    MonitorProperty monitorProperty1 = monitorProperties.get(0);
                    getPropertyByTypeAndTemplateAndParentNodeVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                }
                getPropertyByTypeAndTemplateAndParentNodeVos.add(getPropertyByTypeAndTemplateAndParentNodeVo);
            }
        }
        return WebResponse.success(getPropertyByTypeAndTemplateAndParentNodeVos);
    }

}
