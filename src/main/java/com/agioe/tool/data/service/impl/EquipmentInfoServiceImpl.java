package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.*;
import com.agioe.tool.data.dao.EquipmentInfoDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.page.PageBean;
import com.agioe.tool.data.service.*;
import com.agioe.tool.data.singleton.TcpApiSingleton;
import com.agioe.tool.data.tcp.api.DefaultTcpApiInstance;
import com.agioe.tool.data.tcp.payload.SensorEvent;
import com.agioe.tool.data.thread.SendEquipmentRealtimeDataThread;
import com.agioe.tool.data.util.TimeUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

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
    @Autowired
    private ParamsConfigService paramsConfigService;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private SendControlService sendControlService;


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
    public WebResponse showPageEquipmentInfo(PageQo pageQo) {
        Integer pageNow = pageQo.getPageNow();
        Integer pageSize = pageQo.getPageSize();
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
        Integer countNums = showAllEquipmentInfoVos.size();
        List<ShowAllEquipmentInfoVo> showAllEquipmentInfoPageVos = new ArrayList<>();
        if (pageNow * pageSize <= countNums) {
            for (int i = (pageNow - 1) * pageSize; i < pageSize * pageNow; i++) {
                ShowAllEquipmentInfoVo showAllEquipmentInfoVo = showAllEquipmentInfoVos.get(i);
                showAllEquipmentInfoPageVos.add(showAllEquipmentInfoVo);
            }
        } else {
            for (int i = (pageNow - 1) * pageSize; i < countNums; i++) {
                ShowAllEquipmentInfoVo showAllEquipmentInfoVo = showAllEquipmentInfoVos.get(i);
                showAllEquipmentInfoPageVos.add(showAllEquipmentInfoVo);
            }
        }
        ShowPageEquipmentInfoVo showPageEquipmentInfoVo = new ShowPageEquipmentInfoVo();
        showPageEquipmentInfoVo.setCountNums(countNums);
        showPageEquipmentInfoVo.setShowAllEquipmentInfoVos(showAllEquipmentInfoPageVos);
        return WebResponse.success(showPageEquipmentInfoVo);
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
        String keywordOld = updateEquipmentInfo1Qo.getKeywordOld();
        String keywordNew = updateEquipmentInfo1Qo.getKeyword();
        String parentNodeCode = updateEquipmentInfo1Qo.getParentNodeCode();
        if (keywordOld.equals(keywordNew)) {
            return WebResponse.success();
        } else {
            //keyword重复判断
            EquipmentInfo equipmentInfo = new EquipmentInfo();
            equipmentInfo.setKeyword(keywordNew);
            equipmentInfo.setParentNodeCode(parentNodeCode);
            List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
            if (equipmentInfos.size() > 0) {
                return WebResponse.error(400, "keyword已存在");
            } else {
                //进行更新
                EquipmentInfo equipmentInfo1 = new EquipmentInfo();
                equipmentInfo1.setKeyword(keywordOld);
                equipmentInfo1.setParentNodeCode(parentNodeCode);
                List<EquipmentInfo> equipmentInfos1 = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo1);
                if (equipmentInfos1.size() > 0) {
                    EquipmentInfo equipmentInfo2 = equipmentInfos1.get(0);
                    equipmentInfo2.setKeyword(keywordNew);
                    equipmentInfo2.setParentNodeCode(parentNodeCode);
                    equipmentInfoDao.updateEquipmentInfo(equipmentInfo2);
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
        Integer pageNow = showEquipmentInfoByConditionQo.getPageNow();
        Integer pageSize = showEquipmentInfoByConditionQo.getPageSize();
        ParentNode parentNode5555 = new ParentNode();
        parentNode5555.setParentNodeCode(parentNodeCode);
        Integer countNums = equipmentInfoDao.selectAll(parentNode5555).size();
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
            PageHelper.startPage(pageNow, pageSize);
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
        PageBean<ShowAllEquipmentInfoVo> pageData = new PageBean<>(pageNow, pageSize, countNums);
        pageData.setItems(showAllEquipmentInfoVos);
        return WebResponse.success(pageData);
    }

    @Override
    public WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo, String ip) throws Exception {
        //批量发送
        String parentNodeCode = sendEquipmentRealtimeDataQo.getParentNodeCode();
        String equipmentPropertyTemplateCode = sendEquipmentRealtimeDataQo.getEquipmentPropertyTemplateCode();
        String equipmentType = sendEquipmentRealtimeDataQo.getEquipmentType();
        String[][] propertyCodeAndValue = sendEquipmentRealtimeDataQo.getPropertyCodeAndValue();
        //todo:获取上送周期并进行睡眠
        Integer feedCycle = 0;
        List<ParamsConfig> paramsConfigs = paramsConfigService.selectAll();
        if (paramsConfigs.size() > 0) {
            feedCycle = paramsConfigs.get(0).getFeedCycle();
        }
        if (propertyCodeAndValue.length > 0) {
//            //todo:启动线程
//            SendEquipmentRealtimeDataThread sendEquipmentRealtimeDataThread=new SendEquipmentRealtimeDataThread(propertyCodeAndValue,monitorPropertyService,parentNodeCode,equipmentType,equipmentPropertyTemplateCode,equipmentInfoDao,feedCycle,countDownLatch);
//            Thread thread=new Thread(sendEquipmentRealtimeDataThread);
//            thread.setName("sendEquipmentRealtimeDataThread");
//            thread.start();
//            countDownLatch.await();
//            System.out.println(Thread.currentThread().getName());
//            Thread.currentThread().join();
//            Thread.currentThread().start();
            for (String[] propertyCodeAndValue1 : propertyCodeAndValue) {
//                System.out.println(propertyCodeAndValue1.length);
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
                //todo:对数据类型作判断
                if (equipmentPropertyType == 0) {
                    boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(baseValue).find();
                    boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(baseValue).find();
                    if (!(isInt || isDouble)) {
                        return WebResponse.error(400, "基础值类型错误");
                    } else {
                        boolean isInt1 = Pattern.compile("^-?[1-9]\\d*$").matcher(upAndDown).find();
                        boolean isDouble1 = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(upAndDown).find();
                        if (!(isInt1 || isDouble1)) {
                            return WebResponse.error(400, "浮动值类型错误");
                        }
                    }

                } else if (equipmentPropertyType == 1) {
                    //判断是否为0或者1
                    boolean is0Or1 = baseValue.equals("0") || baseValue.equals("1");
                    boolean is0Or2 = upAndDown.equals("0") || upAndDown.equals("1");
                    if (!(is0Or1 && is0Or2)) {
                        return WebResponse.error(400, "参数类型错误,基础值和浮动只能为0或者1");
                    }
                } else {
                    return WebResponse.error(400, "属性类型错误");
                }
                EquipmentInfo equipmentInfo = new EquipmentInfo();
                equipmentInfo.setParentNodeCode(parentNodeCode);
                equipmentInfo.setEquipmentType(equipmentType);
                equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                equipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
                List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
//                if (equipmentInfos.size() > 0) {
////                    ThreadSingleton.getSendEquipmentRealtimeDataThread()
//                    SendEquipmentRealtimeDataThread sendEquipmentRealtimeDataThread=new SendEquipmentRealtimeDataThread(equipmentInfos,baseValue,upAndDown,
//                            equipmentPropertyType,feedCycle,equipmentInfoDao);
//                    Thread thread=new Thread(sendEquipmentRealtimeDataThread);
//                    thread.setName("sendEquipmentRealtimeDataThread");
//                    thread.start();
//                    for (EquipmentInfo equipmentInfo1 : equipmentInfos) {
//                        String dataVal = "";
//                        List<SensorData> dataList = new ArrayList<>();
//                        Double baseValueDouble = Double.valueOf(baseValue);
//                        Double upAndDownDouble = Double.valueOf(upAndDown);
//                        Double minBaseValue = baseValueDouble - upAndDownDouble;
//                        //获取信号类型
//                        //todo:获取随机的数据
//                        Random random = new Random();
//                        Double dataValDouble1 = random.nextDouble() * upAndDownDouble * 2 + minBaseValue;
//                        //todo:保留两位小数
//                        BigDecimal b = new BigDecimal(dataValDouble1);
//                        Double dataValDouble = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                        if (equipmentPropertyType == 0) {//信号类型为0,浮点数
//                            dataVal = String.valueOf(dataValDouble);
//                        } else if (equipmentPropertyType == 1) {//信号类型为1,只能为0或者1
//                            int i = random.nextInt(2);
//                            dataVal = String.valueOf(i);
//                        } else {
//                            return WebResponse.error(400, "属性类型错误,发送失败");
//                        }
//                        Date dataUpdate = new Date();
//                        equipmentInfo1.setDataVal(dataVal);
//                        equipmentInfo1.setDataUpdate(dataUpdate);
//                        equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
//                        SensorData sensorData = new SensorData();
//                        sensorData.setKey(equipmentInfo1.getKeyword());
//                        sensorData.setOrg("");
//                        sensorData.setTime(dataUpdate.getTime());
//                        sensorData.setType(Byte.decode(String.valueOf(equipmentPropertyType)));
//                        sensorData.setVal(dataVal);
//                        dataList.add(sensorData);
//                        //todo:发送实时数据
//                        DefaultTcpApiInstance defaultTcpApiInstance = TcpApiSingleton.getDefaultTcpApiInstance();
//                        defaultTcpApiInstance.sendSensorData(dataList);
//                        Thread.currentThread().sleep(feedCycle);
//                    }
//                }
            }
        }
        Random random = new Random();
        Integer dataValInteger = random.nextInt(100);
        String threadName = "sendEquipmentRealtimeDataThread:" + String.valueOf(ip);
        if (propertyCodeAndValue.length > 0) {
            //todo:启动线程
            SendEquipmentRealtimeDataThread sendEquipmentRealtimeDataThread = new SendEquipmentRealtimeDataThread(propertyCodeAndValue, monitorPropertyService, parentNodeCode, equipmentType, equipmentPropertyTemplateCode, equipmentInfoDao, feedCycle);
            Thread thread = new Thread(sendEquipmentRealtimeDataThread);
            //获取0-100的正整数
            thread.setName(threadName);
            thread.start();
        }
        //更改控制值
//        List<SendControl> sendControls = sendControlService.selectOne(ip);
//        if (sendControls.size() > 0) {
//            SendControl sendControl = sendControls.get(0);
//            sendControl.setControlVal(0);
//            sendControlService.updateSendControl(sendControl);
//        } else {
//            //先添加,在查询
//            SendControl sendControl = new SendControl();
//            sendControl.setControlVal(0);
//            sendControl.setIp(ip);
//            sendControlService.insertSendControl(sendControl);
//        }
        SendControlVal sendControlVal = SendControlVal.getSendControlVal();
        sendControlVal.setVal(0);
        return WebResponse.success();
    }

    @Override
    public WebResponse stopSendEquipmentRealtimeData(String ip) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (Thread thread : lstThreads) {
            if (thread.getName().equals("sendEquipmentRealtimeDataThread:" + ip)) {
                thread.interrupt();
                System.out.println("停止了线程:" + thread.getName());
            }

        }
        //更改控制值
//        List<SendControl> sendControls = sendControlService.selectOne(ip);
//        if (sendControls.size() > 0) {
//            SendControl sendControl = sendControls.get(0);
//            sendControl.setControlVal(1);
//            sendControlService.updateSendControl(sendControl);
//        } else {
//            //先添加,在查询
//            SendControl sendControl = new SendControl();
//            sendControl.setControlVal(1);
//            sendControl.setIp(ip);
//            sendControlService.insertSendControl(sendControl);
//        }
        SendControlVal sendControlVal = SendControlVal.getSendControlVal();
        sendControlVal.setVal(1);
        return WebResponse.success();
    }

    @Override
    public WebResponse sendEventHistory(SendEventHistoryQo sendEventHistoryQo) {
        String eventCode = sendEventHistoryQo.getEventCode();
        String eventType = sendEventHistoryQo.getEventType();
        //获取事件类型值
        String eventTypeValue = "";
        if (eventType.equals("0")) {
            eventTypeValue = "报警";
        } else {
            eventTypeValue = "故障";
        }
        String eventValue = sendEventHistoryQo.getEventValue();
        String parentNodeCode = sendEventHistoryQo.getParentNodeCode();
        Integer equipmentPropertyType = sendEventHistoryQo.getEquipmentPropertyType();
        //todo:先对值进行校验
        if (equipmentPropertyType == 0) {//为浮点型
            boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(eventCode).find();
            if (!isInt) {
                return WebResponse.error(400, "事件码类型错误,只能为整数");
            } else {
                boolean isInt1 = Pattern.compile("^-?[1-9]\\d*$").matcher(eventValue).find();
                boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(eventValue).find();
                if (!(isInt1 || isDouble)) {
                    return WebResponse.error(400, "事件值类型错误,只能为整数或浮点数");
                }
            }
        } else if (equipmentPropertyType == 1) {//为整型,只能为0和1
            boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(eventCode).find();
            if (!isInt) {
                return WebResponse.error(400, "事件码类型错误,只能为整数");
            } else {
                //判断是否为0或者1
                boolean is0Or1 = eventValue.equals("0") || eventValue.equals("1");
                if (!is0Or1) {
                    return WebResponse.error(400, "事件值错误,只能为0或者1");
                }
            }
        } else {
            return WebResponse.error(400, "属性类型错误");
        }

        String keyword = sendEventHistoryQo.getKeyword();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        String alarmVal = "事件类型:" + eventTypeValue + ",事件码:" + eventCode + ",事件值:" + eventValue;
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
            DefaultTcpApiInstance defaultTcpApiInstance = TcpApiSingleton.getDefaultTcpApiInstance();
            defaultTcpApiInstance.sendSensorEvent(eventList);
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
            DefaultTcpApiInstance defaultTcpApiInstance = TcpApiSingleton.getDefaultTcpApiInstance();
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
                String equipmentPropertyCode555 = equipmentInfo1.getEquipmentPropertyCode();
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode555);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    MonitorProperty monitorProperty1 = monitorProperties.get(0);
                    if (monitorProperty1.getEquipmentPropertyType().equals(0) || monitorProperty1.getEquipmentPropertyType().equals(1)) {
                        propertyCodeSet.add(equipmentInfo1.getEquipmentPropertyCode());
                    }
                }
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
        String keywordAfter = "";
        if (equipmentPropertyType == 0) {
            keywordAfter = "YC";
        } else if (equipmentPropertyType == 1) {
            keywordAfter = "YX";
        } else if (equipmentPropertyType == 2) {
            keywordAfter = "YK";
        } else if (equipmentPropertyType == 3) {
            keywordAfter = "YT";
        } else {
            keywordAfter = "EXPLAN";
        }
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
                        String keyword = parentNodeCode + "_" + String.valueOf(keyWordStart) + "_" + keywordAfter;
                        equipmentInfo1.setKeyword(keyword);
                        equipmentInfoDao.updateEquipmentInfo(equipmentInfo1);
                        keyWordStart++;
                    }
                }
            }
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse exportExcelEquipmentInfo(ExportExcelEquipmentInfoQo exportExcelEquipmentInfoQo) throws Exception {
        String parentNodeCode1 = exportExcelEquipmentInfoQo.getParentNodeCode();
        String filePath = "D://excel//temp.xls";
        String title = "设备信息表";
        Integer colunmNumber = 9;
        ParentNode parentNode33 = new ParentNode();
        parentNode33.setParentNodeCode(parentNodeCode1);
        List<EquipmentInfo> equipmentInfoList = equipmentInfoDao.selectAll(parentNode33);
        String[][] strings = new String[equipmentInfoList.size() + 1][9];
        strings[0][0] = "序号";
        strings[0][1] = "上层节点";
        strings[0][2] = "设备编码";
        strings[0][3] = "设备名称";
        strings[0][4] = "设备类型";
        strings[0][5] = "设备信号模板";
        strings[0][6] = "监控信号类型";
        strings[0][7] = "设备信号";
        strings[0][8] = "设备点号";
        for (int c = 1; c < strings.length; c++) {
            strings[c][0] = String.valueOf(c);
            String parentNodeCode = equipmentInfoList.get(c - 1).getParentNodeCode();
            //获取上层节点名称
            ParentNode parentNode = new ParentNode();
            parentNode.setParentNodeCode(parentNodeCode);
            List<ParentNode> parentNodes1 = parentNodeService.selectByParentNode(parentNode);
            if (parentNodes1.size() > 0) {
                strings[c][1] = parentNodes1.get(0).getParentNodeName();
            }
            strings[c][2] = equipmentInfoList.get(c - 1).getEquipmentCode();
            strings[c][3] = equipmentInfoList.get(c - 1).getEquipmentName();
            //获取设备类型
            String equipmentType = equipmentInfoList.get(c - 1).getEquipmentType();
            EquipmentType equipmentType1 = new EquipmentType();
            equipmentType1.setEquipmentTypeCode(equipmentType);
            List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
            if (equipmentTypes.size() > 0) {
                strings[c][4] = equipmentTypes.get(0).getEquipmentTypeName();
            }
            //获取模板
            String equipmentPropertyTemplateCode = equipmentInfoList.get(c - 1).getEquipmentPropertyTemplateCode();
            MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
            monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
            List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
            if (monitorPropertyTemplates.size() > 0) {
                strings[c][5] = monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateName();
            }
            //获取信号
            String equipmentPropertyCode = equipmentInfoList.get(c - 1).getEquipmentPropertyCode();
            MonitorProperty monitorProperty = new MonitorProperty();
            monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
            List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
            if (monitorProperties.size() > 0) {
                Integer equipmentPropertyType = monitorProperties.get(0).getEquipmentPropertyType();
                if (equipmentPropertyType == 0) {
                    strings[c][6] = "遥测";
                } else if (equipmentPropertyType == 1) {
                    strings[c][6] = "遥信";
                } else if (equipmentPropertyType == 2) {
                    strings[c][6] = "遥控";
                } else if (equipmentPropertyType == 3) {
                    strings[c][6] = "遥调";
                } else {
                    strings[c][6] = "说明";
                }
                strings[c][7] = monitorProperties.get(0).getEquipmentPropertyName();
            }
            //获取keyword
            strings[c][8] = equipmentInfoList.get(c - 1).getKeyword();
        }
        excelService.exportExcel(filePath, strings, title, colunmNumber);
        return WebResponse.success("http://192.168.52.50:8099/excel/temp.xls");
    }

    @Override
    public WebResponse importExcelEquipmentInfo(ImportExcelEquipmentInfoQo importExcelEquipmentInfoQo) throws Exception {
        String filePath = importExcelEquipmentInfoQo.getFilePath();
        String[][] strings = excelService.importExcel(filePath);
        if (strings.length > 0) {
            for (String[] strings1 : strings) {
                String equipmentCode = strings1[1];
                String equipmentName = strings1[2];
                String equipmentTypeName = strings1[3];
                String equipmentTypeCode = "";
                //获取设备类型编码
                EquipmentType equipmentType = new EquipmentType();
                equipmentType.setEquipmentTypeName(equipmentTypeName);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType);
                if (equipmentTypes.size() > 0) {
                    equipmentTypeCode = equipmentTypes.get(0).getEquipmentTypeCode();
                }
                String equipmentPropertyTemplateName = strings1[4];
                String equipmentPropertyTemplateCode = "";
                //获取模板编码
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateName(equipmentPropertyTemplateName);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    equipmentPropertyTemplateCode = monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateCode();
                }
                String equipmentPropertyName = strings1[6];
                String equipmentPropertyCode = "";
                //获取属性编码
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyName(equipmentPropertyName);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    equipmentPropertyCode = monitorProperties.get(0).getEquipmentPropertyCode();
                }
                String keyword = strings1[7];
                //获取上层节点编码
                String[] split = keyword.split("_");
                String parentNodeCode = split[0];
                ParentNode parentNode = new ParentNode();
                parentNode.setParentNodeCode(parentNodeCode);
                List<ParentNode> parentNodes = parentNodeService.selectByParentNode(parentNode);
                if (parentNodes.size() > 0) {
                    EquipmentInfo equipmentInfo = new EquipmentInfo();
                    equipmentInfo.setParentNodeCode(parentNodeCode);
                    equipmentInfo.setKeyword(keyword);
                    equipmentInfo.setEquipmentName(equipmentName);
                    equipmentInfo.setEquipmentCode(equipmentCode);
                    equipmentInfo.setEquipmentType(equipmentTypeCode);
                    equipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
                    equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                    equipmentInfo.setAlarmVal("");
                    equipmentInfo.setControlVal("");
                    equipmentInfo.setDataVal("");
                    equipmentInfoDao.insertEquipmentInfo(equipmentInfo);
                }
            }

        }
        return WebResponse.success();
    }

    @Override
    public WebResponse getSendControlVal() {
//        List<SendControl> sendControls = sendControlService.selectOne("192.168.52.50");
//        if (sendControls.size() > 0) {
//            return WebResponse.success(sendControls.get(0));
//        } else {
            //先添加,在查询
        SendControlVal sendControlVal = SendControlVal.getSendControlVal();
        SendControl sendControl = new SendControl();
        sendControl.setControlVal(sendControlVal.getVal());
        sendControl.setIp("192.168.52.50");
        sendControlService.insertSendControl(sendControl);
        return WebResponse.success(sendControl);
//        }
    }

    @Override
    public WebResponse getDealEventHistoryValue(GetDealEventHistoryValueQo getDealEventHistoryValueQo) {
        String keyword = getDealEventHistoryValueQo.getKeyword();
        String parentNodeCode = getDealEventHistoryValueQo.getParentNodeCode();
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setParentNodeCode(parentNodeCode);
        equipmentInfo.setKeyword(keyword);
        List<EquipmentInfo> equipmentInfos = equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
        GetDealEventHistoryValueVo getDealEventHistoryValueVo = new GetDealEventHistoryValueVo();
        if (equipmentInfos.size() > 0) {
            EquipmentInfo equipmentInfo1 = equipmentInfos.get(0);
            String alarmVal = equipmentInfo1.getAlarmVal();
            String[] split = alarmVal.split(",");
            String eventType = "";
            String eventCode = "";
            String eventValue = "";
            if (split.length > 0) {
                eventType = split[0].split(":")[1];
                eventCode = split[1].split(":")[1];
                eventValue = split[2].split(":")[1];
            }
            getDealEventHistoryValueVo.setEventCode(eventCode);
            getDealEventHistoryValueVo.setEventType(eventType);
            getDealEventHistoryValueVo.setEventValue(eventValue);
        }
        return WebResponse.success(getDealEventHistoryValueVo);
    }


}
