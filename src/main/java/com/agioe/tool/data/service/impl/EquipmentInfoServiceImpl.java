package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateAndParentNodeVo;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateVo;
import com.agioe.tool.data.Vo.ShowAllEquipmentInfoVo;
import com.agioe.tool.data.dao.EquipmentInfoDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.service.*;
import com.agioe.tool.data.tcp.api.DefaultTcpApiInstance;
import com.agioe.tool.data.tcp.payload.SensorData;
import com.agioe.tool.data.tcp.payload.SensorEvent;
import com.agioe.tool.data.util.TimeUtil;
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
    private MonitorPropertyTemplateService monitorPropertyTemplateService;
    @Autowired
    private EquipmentTypeService equipmentTypeService;


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
    public List<EquipmentInfo> selectAll(ParentNode parentNode) {
        return equipmentInfoDao.selectAll(parentNode);
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
                List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectAll(parentNode);
                if (EquipmentInfos.size() > 0) {
                    for (EquipmentInfo equipmentInfo : EquipmentInfos) {
                        ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();

                        showAllEquipmentInfoVo.setEquipmentCode(equipmentInfo.getEquipmentCode());
                        showAllEquipmentInfoVo.setEquipmentName(equipmentInfo.getEquipmentName());

                        showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                        MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                        monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                        if (monitorPropertyTemplates.size() > 0) {
                            MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                            showAllEquipmentInfoVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                        }

                        showAllEquipmentInfoVo.setEquipmentType(equipmentInfo.getEquipmentType());
                        EquipmentType equipmentType = new EquipmentType();
                        equipmentType.setEquipmentTypeCode(equipmentInfo.getEquipmentType());
                        List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType);
                        if (equipmentTypes.size() > 0) {
                            EquipmentType equipmentType1 = equipmentTypes.get(0);
                            showAllEquipmentInfoVo.setEquipmentTypeName(equipmentType1.getEquipmentTypeName());
                        }

                        showAllEquipmentInfoVo.setKeyword(equipmentInfo.getKeyword());

                        showAllEquipmentInfoVo.setParentNodeCode(equipmentInfo.getParentNodeCode());
                        ParentNode parentNode1 = new ParentNode();
                        parentNode1.setParentNodeCode(equipmentInfo.getParentNodeCode());
                        List<ParentNode> parentNodes1 = parentNodeService.selectByParentNode(parentNode1);
                        if (parentNodes1.size() > 0) {
                            ParentNode parentNode2 = parentNodes1.get(0);
                            showAllEquipmentInfoVo.setParentNodeName(parentNode2.getParentNodeName());
                        }

                        showAllEquipmentInfoVo.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                        MonitorProperty monitorProperty = new MonitorProperty();
                        monitorProperty.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                        List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                        if (monitorProperties.size() > 0) {
                            MonitorProperty monitorProperty1 = monitorProperties.get(0);
                            showAllEquipmentInfoVo.setEquipmentPropertyType(monitorProperty1.getEquipmentPropertyType());
                            showAllEquipmentInfoVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                        }

                        Date alarmUpdate = equipmentInfo.getAlarmUpdate();
                        if (alarmUpdate != null) {
                            showAllEquipmentInfoVo.setAlarmUpdate(TimeUtil.format(alarmUpdate, "YYYY-MM-dd HH:mm:ss"));
                        }

                        showAllEquipmentInfoVo.setAlarmVal(equipmentInfo.getAlarmVal());

                        Date controllerUpdate = equipmentInfo.getControllerUpdate();
                        if (controllerUpdate != null) {
                            showAllEquipmentInfoVo.setControllerUpdate(TimeUtil.format(controllerUpdate, "YYYY-MM-dd HH:mm:ss"));
                        }
                        showAllEquipmentInfoVo.setControlVal(equipmentInfo.getControlVal());

                        Date dataUpdate = equipmentInfo.getDataUpdate();
                        if (dataUpdate != null) {
                            showAllEquipmentInfoVo.setDataUpdate(TimeUtil.format(dataUpdate, "YYYY-MM-dd HH:mm:ss"));
                        }
                        showAllEquipmentInfoVo.setDataVal(equipmentInfo.getDataVal());


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
            EquipmentInfo equipmentInfo = new EquipmentInfo();
            equipmentInfo.setEquipmentCode(equipmentCode);
            equipmentInfo.setParentNodeCode(parentNodeCode);
            List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
            if (equipmentInfos.size() > 0) {
                return WebResponse.error(400, "设备编码已存在");
            }
            //名字判重
            EquipmentInfo equipmentInfo1 = new EquipmentInfo();
            equipmentInfo1.setEquipmentName(equipmentName);
            equipmentInfo1.setParentNodeCode(parentNodeCode);
            List<EquipmentInfo> equipmentInfos1 = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo1);
            if (equipmentInfos1.size() > 0) {
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
    public WebResponse updateEquipmentInfo1(UpdateEquipmentInfo1Qo updateEquipmentInfo1Qo) {
        String equipmentCode = updateEquipmentInfo1Qo.getEquipmentCode();
        String keyword = updateEquipmentInfo1Qo.getKeyword();
        String parentNodeCode = updateEquipmentInfo1Qo.getParentNodeCode();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setEquipmentCode(equipmentCode);
        equipmentInfo.setParentNodeCode(parentNodeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            EquipmentInfo equipmentInfo1 = equipmentInfos.get(0);
            if (equipmentInfo1.getKeyword().equals(keyword)) {
                return WebResponse.success();
            } else {
                //keyword重复判断
                EquipmentInfo equipmentInfo2 = new EquipmentInfo();
                equipmentInfo2.setParentNodeCode(parentNodeCode);
                equipmentInfo2.setKeyword(keyword);
                List<EquipmentInfo> equipmentInfos1 = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo2);
                if (equipmentInfos1.size() > 0) {
                    return WebResponse.error(400, "keyword已存在");
                } else {
                    //进行更新
                    equipmentInfo1.setKeyword(keyword);
                    equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
                }
            }
        }
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
                        for (EquipmentInfo equipmentInfo : EquipmentInfos) {
                            ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();

                            showAllEquipmentInfoVo.setEquipmentCode(equipmentInfo.getEquipmentCode());
                            showAllEquipmentInfoVo.setEquipmentName(equipmentInfo.getEquipmentName());

                            showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                            MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                            monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                            List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                            if (monitorPropertyTemplates.size() > 0) {
                                MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                                showAllEquipmentInfoVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                            }

                            showAllEquipmentInfoVo.setEquipmentType(equipmentInfo.getEquipmentType());
                            EquipmentType equipmentType33 = new EquipmentType();
                            equipmentType33.setEquipmentTypeCode(equipmentInfo.getEquipmentType());
                            List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType33);
                            if (equipmentTypes.size() > 0) {
                                EquipmentType equipmentType1 = equipmentTypes.get(0);
                                showAllEquipmentInfoVo.setEquipmentTypeName(equipmentType1.getEquipmentTypeName());
                            }

                            showAllEquipmentInfoVo.setKeyword(equipmentInfo.getKeyword());

                            showAllEquipmentInfoVo.setParentNodeCode(equipmentInfo.getParentNodeCode());
                            ParentNode parentNode1 = new ParentNode();
                            parentNode1.setParentNodeCode(equipmentInfo.getParentNodeCode());
                            List<ParentNode> parentNodes1 = parentNodeService.selectByParentNode(parentNode1);
                            if (parentNodes1.size() > 0) {
                                ParentNode parentNode2 = parentNodes1.get(0);
                                showAllEquipmentInfoVo.setParentNodeName(parentNode2.getParentNodeName());
                            }

                            showAllEquipmentInfoVo.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                            MonitorProperty monitorProperty = new MonitorProperty();
                            monitorProperty.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                            List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                            if (monitorProperties.size() > 0) {
                                MonitorProperty monitorProperty1 = monitorProperties.get(0);
                                showAllEquipmentInfoVo.setEquipmentPropertyType(monitorProperty1.getEquipmentPropertyType());
                                showAllEquipmentInfoVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                            }

                            Date alarmUpdate = equipmentInfo.getAlarmUpdate();
                            if (alarmUpdate != null) {
                                showAllEquipmentInfoVo.setAlarmUpdate(TimeUtil.format(alarmUpdate, "YYYY-MM-dd HH:mm:ss"));
                            }

                            showAllEquipmentInfoVo.setAlarmVal(equipmentInfo.getAlarmVal());

                            Date controllerUpdate = equipmentInfo.getControllerUpdate();
                            if (controllerUpdate != null) {
                                showAllEquipmentInfoVo.setControllerUpdate(TimeUtil.format(controllerUpdate, "YYYY-MM-dd HH:mm:ss"));
                            }
                            showAllEquipmentInfoVo.setControlVal(equipmentInfo.getControlVal());

                            Date dataUpdate = equipmentInfo.getDataUpdate();
                            if (dataUpdate != null) {
                                showAllEquipmentInfoVo.setDataUpdate(TimeUtil.format(dataUpdate, "YYYY-MM-dd HH:mm:ss"));
                            }
                            showAllEquipmentInfoVo.setDataVal(equipmentInfo.getDataVal());


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
                for (EquipmentInfo equipmentInfo : EquipmentInfos) {
                    ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();

                    showAllEquipmentInfoVo.setEquipmentCode(equipmentInfo.getEquipmentCode());
                    showAllEquipmentInfoVo.setEquipmentName(equipmentInfo.getEquipmentName());

                    showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                    MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                    monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentInfo.getEquipmentPropertyTemplateCode());
                    List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                    if (monitorPropertyTemplates.size() > 0) {
                        MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                        showAllEquipmentInfoVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                    }

                    showAllEquipmentInfoVo.setEquipmentType(equipmentInfo.getEquipmentType());
                    EquipmentType equipmentType44 = new EquipmentType();
                    equipmentType44.setEquipmentTypeCode(equipmentInfo.getEquipmentType());
                    List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType44);
                    if (equipmentTypes.size() > 0) {
                        EquipmentType equipmentType1 = equipmentTypes.get(0);
                        showAllEquipmentInfoVo.setEquipmentTypeName(equipmentType1.getEquipmentTypeName());
                    }

                    showAllEquipmentInfoVo.setKeyword(equipmentInfo.getKeyword());

                    showAllEquipmentInfoVo.setParentNodeCode(equipmentInfo.getParentNodeCode());
                    ParentNode parentNode1 = new ParentNode();
                    parentNode1.setParentNodeCode(equipmentInfo.getParentNodeCode());
                    List<ParentNode> parentNodes1 = parentNodeService.selectByParentNode(parentNode1);
                    if (parentNodes1.size() > 0) {
                        ParentNode parentNode2 = parentNodes1.get(0);
                        showAllEquipmentInfoVo.setParentNodeName(parentNode2.getParentNodeName());
                    }

                    showAllEquipmentInfoVo.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                    MonitorProperty monitorProperty = new MonitorProperty();
                    monitorProperty.setEquipmentPropertyCode(equipmentInfo.getEquipmentPropertyCode());
                    List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                    if (monitorProperties.size() > 0) {
                        MonitorProperty monitorProperty1 = monitorProperties.get(0);
                        showAllEquipmentInfoVo.setEquipmentPropertyType(monitorProperty1.getEquipmentPropertyType());
                        showAllEquipmentInfoVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                    }

                    Date alarmUpdate = equipmentInfo.getAlarmUpdate();
                    if (alarmUpdate != null) {
                        showAllEquipmentInfoVo.setAlarmUpdate(TimeUtil.format(alarmUpdate, "YYYY-MM-dd HH:mm:ss"));
                    }

                    showAllEquipmentInfoVo.setAlarmVal(equipmentInfo.getAlarmVal());

                    Date controllerUpdate = equipmentInfo.getControllerUpdate();
                    if (controllerUpdate != null) {
                        showAllEquipmentInfoVo.setControllerUpdate(TimeUtil.format(controllerUpdate, "YYYY-MM-dd HH:mm:ss"));
                    }
                    showAllEquipmentInfoVo.setControlVal(equipmentInfo.getControlVal());

                    Date dataUpdate = equipmentInfo.getDataUpdate();
                    if (dataUpdate != null) {
                        showAllEquipmentInfoVo.setDataUpdate(TimeUtil.format(dataUpdate, "YYYY-MM-dd HH:mm:ss"));
                    }
                    showAllEquipmentInfoVo.setDataVal(equipmentInfo.getDataVal());


                    showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
                }
            }
        }

        return WebResponse.success(showAllEquipmentInfoVos);
    }

    @Override
    public WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo) {   //批量发送
        String parentNodeCode = sendEquipmentRealtimeDataQo.getParentNodeCode();
        String equipmentPropertyTemplateCode = sendEquipmentRealtimeDataQo.getEquipmentPropertyTemplateCode();
        String equipmentType = sendEquipmentRealtimeDataQo.getEquipmentType();
        String[][] propertyCodeAndValue = sendEquipmentRealtimeDataQo.getPropertyCodeAndValue();
        List<SensorData> dataList = new ArrayList<>();
        if (propertyCodeAndValue.length > 0) {
            for (String[] propertyCodeAndValue1 : propertyCodeAndValue) {
                String equipmentPropertyCode = propertyCodeAndValue1[0];
                String baseValue = propertyCodeAndValue1[1];
                String upAndDown = propertyCodeAndValue1[2];
                //todo:对数据类型作判断
                EquipmentInfo equipmentInfo = new EquipmentInfo();
                equipmentInfo.setParentNodeCode(parentNodeCode);
                equipmentInfo.setEquipmentType(equipmentType);
                equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                equipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
                List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
                if (equipmentInfos.size() > 0) {
                    for (EquipmentInfo equipmentInfo1 : equipmentInfos) {
                        //todo:获取随机的数据
                        String dataVal = baseValue + upAndDown;
                        Date dataUpdate = new Date();
                        equipmentInfo1.setDataVal(dataVal);
                        equipmentInfo1.setDataUpdate(dataUpdate);
                        equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
                        SensorData sensorData = new SensorData();
//                      sensorData.setKey();
//                      sensorData.setOrg();
//                      sensorData.setTime();
//                      sensorData.setType();
//                      sensorData.setVal();
                    }
                }
            }
        }
//        //todo:发送实时数据
//        DefaultTcpApiInstance defaultTcpApiInstance=DefaultTcpApiInstance.getInstance();
//        defaultTcpApiInstance.sendSensorData(dataList);
        return WebResponse.success();
    }

    @Override
    public WebResponse sendEventHistory(SendEventHistoryQo sendEventHistoryQo) {  //发送消息失败,保存数据成功
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
            DefaultTcpApiInstance defaultTcpApiInstance = DefaultTcpApiInstance.getInstance();
            defaultTcpApiInstance.sendSensorEvent(eventList);
        }

        return WebResponse.success();
    }

    @Override
    public WebResponse dealEventHistory(DealEventHistoryQo dealEventHistoryQo) { //发送消息失败,保存数据成功
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
            DefaultTcpApiInstance defaultTcpApiInstance = DefaultTcpApiInstance.getInstance();
            defaultTcpApiInstance.sendSensorEvent(eventList);
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

    @Override
    public WebResponse createKeyWord(CreateKeyWordQo createKeyWordQo) {
        Integer equipmentPropertyType = createKeyWordQo.getEquipmentPropertyType();
        Integer keyWordStart = createKeyWordQo.getKeyWordStart();
        String parentNodeCode = createKeyWordQo.getParentNodeCode();
        MonitorProperty monitorProperty = new MonitorProperty();
        monitorProperty.setEquipmentPropertyType(equipmentPropertyType);
        List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
        if (monitorProperties.size() > 0) {
            for (MonitorProperty monitorProperty1 : monitorProperties) {
                EquipmentInfo equipmentInfo = new EquipmentInfo();
                equipmentInfo.setParentNodeCode(parentNodeCode);
                equipmentInfo.setEquipmentPropertyCode(monitorProperty1.getEquipmentPropertyCode());
                List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
                if (equipmentInfos.size() > 0) {
                    for (EquipmentInfo equipmentInfo1 : equipmentInfos) {
                        String keyword = parentNodeCode + "_" + String.valueOf(keyWordStart) + "_" + monitorProperty1.getEquipmentPropertyCode();
                        equipmentInfo1.setKeyword(keyword);
                        equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
                        keyWordStart++;
                    }
                }
            }
        }
        return WebResponse.success();
    }

}
