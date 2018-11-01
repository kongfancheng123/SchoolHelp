package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateVo;
import com.agioe.tool.data.Vo.ShowAllMonitorPropertyVo;
import com.agioe.tool.data.dao.MonitorPropertyDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.page.PageBean;
import com.agioe.tool.data.service.*;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorPropertyServiceImpl implements MonitorPropertyService {
    @Autowired
    private MonitorPropertyDao monitorPropertyDao;

    @Autowired
    private MonitorPropertyTemplateBindService monitorPropertyTemplateBindService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ParentNodeService parentNodeService;

    @Autowired
    private ExcelService excelService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Integer insertMonitorProperty(MonitorProperty monitorProperty) {
        return monitorPropertyDao.insertMonitorProperty(monitorProperty);
    }

    @Override
    public Integer deleteMonitorProperty(String equipmentPropertyCode) {
        return monitorPropertyDao.deleteMonitorProperty(equipmentPropertyCode);
    }

    @Override
    public List<MonitorProperty> selectByMonitorProperty(MonitorProperty monitorProperty) {
        return monitorPropertyDao.selectByMonitorProperty(monitorProperty);
    }

    @Override
    public Integer updateMonitorProperty(MonitorProperty monitorProperty) {
        return monitorPropertyDao.updateMonitorProperty(monitorProperty);
    }

    @Override
    public List<MonitorProperty> selectAll() {
        return monitorPropertyDao.selectAll();
    }

    @Override
    public List<GetPropertyByTypeAndTemplateVo> getPropertyByTypeAndTemplate(GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo) {
        List<GetPropertyByTypeAndTemplateVo> getPropertyByTypeAndTemplateVos = new ArrayList<>();
        String equipmentPropertyTemplateCode = getPropertyByTypeAndTemplateQo.getEquipmentPropertyTemplateCode();
        String equipmentTypeCode = getPropertyByTypeAndTemplateQo.getEquipmentTypeCode();
        MonitorPropertyTemplateBind monitorPropertyTemplateBind = new MonitorPropertyTemplateBind();
        monitorPropertyTemplateBind.setEquipmentType(equipmentTypeCode);
        monitorPropertyTemplateBind.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindService.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        if (monitorPropertyTemplateBinds.size() > 0) {
            for (MonitorPropertyTemplateBind monitorPropertyTemplateBind1 : monitorPropertyTemplateBinds) {
                GetPropertyByTypeAndTemplateVo getPropertyByTypeAndTemplateVo = new GetPropertyByTypeAndTemplateVo();
                String equipmentPropertyCode = monitorPropertyTemplateBind1.getEquipmentPropertyCode();
                getPropertyByTypeAndTemplateVo.setEquipmentPropertyCode(equipmentPropertyCode);
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                List<MonitorProperty> monitorProperties = monitorPropertyDao.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    MonitorProperty monitorProperty1 = monitorProperties.get(0);
                    String equipmentPropertyName = monitorProperty1.getEquipmentPropertyName();
                    getPropertyByTypeAndTemplateVo.setEquipmentPropertyName(equipmentPropertyName);
                }
                getPropertyByTypeAndTemplateVos.add(getPropertyByTypeAndTemplateVo);
            }
        }
        logger.info("条件获取属性");
        return getPropertyByTypeAndTemplateVos;
    }

    @Override
    public MonitorProperty selectByid(Integer id) {
        return monitorPropertyDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitorProperty() {
        List<ShowAllMonitorPropertyVo> showAllMonitorPropertyVos = new ArrayList<>();
        List<MonitorProperty> monitorProperties = monitorPropertyDao.selectAll();
        if (monitorProperties.size() > 0) {
            for (MonitorProperty monitorProperty : monitorProperties) {
                ShowAllMonitorPropertyVo showAllMonitorPropertyVo = new ShowAllMonitorPropertyVo();
                showAllMonitorPropertyVo.setEquipmentPropertyCode(monitorProperty.getEquipmentPropertyCode());
                showAllMonitorPropertyVo.setEquipmentPropertyName(monitorProperty.getEquipmentPropertyName());
                showAllMonitorPropertyVo.setEquipmentPropertyType(monitorProperty.getEquipmentPropertyType());
                showAllMonitorPropertyVos.add(showAllMonitorPropertyVo);
            }
        }
        logger.info("获取所有属性");
        return WebResponse.success(showAllMonitorPropertyVos);
    }

    @Override
    public WebResponse showPageMonitorProperty(PageQo pageQo) {
        Integer pageNow = pageQo.getPageNow();
        Integer pageSize = pageQo.getPageSize();
        Integer countNums = monitorPropertyDao.selectAll().size();
        List<ShowAllMonitorPropertyVo> showAllMonitorPropertyVos = new ArrayList<>();
        PageHelper.startPage(pageNow, pageSize);
        List<MonitorProperty> monitorProperties = monitorPropertyDao.selectAll();
        if (monitorProperties.size() > 0) {
            for (MonitorProperty monitorProperty : monitorProperties) {
                ShowAllMonitorPropertyVo showAllMonitorPropertyVo = new ShowAllMonitorPropertyVo();
                showAllMonitorPropertyVo.setEquipmentPropertyCode(monitorProperty.getEquipmentPropertyCode());
                showAllMonitorPropertyVo.setEquipmentPropertyName(monitorProperty.getEquipmentPropertyName());
                showAllMonitorPropertyVo.setEquipmentPropertyType(monitorProperty.getEquipmentPropertyType());
                showAllMonitorPropertyVos.add(showAllMonitorPropertyVo);
            }
        }
        PageBean<ShowAllMonitorPropertyVo> pageData = new PageBean<>(pageNow, pageSize, countNums);
        pageData.setItems(showAllMonitorPropertyVos);
        logger.info("分页获取属性");
        return WebResponse.success(pageData);
    }

    @Override
    public WebResponse addMonitorProperty1(AddMonitorProperty1Qo addMonitorProperty1Qo) {
        String equipmentPropertyCode = addMonitorProperty1Qo.getEquipmentPropertyCode();
        String equipmentPropertyName = addMonitorProperty1Qo.getEquipmentPropertyName();
        Integer equipmentPropertyType = addMonitorProperty1Qo.getEquipmentPropertyType();
        //编码判重
        MonitorProperty monitorProperty = new MonitorProperty();
        monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
        List<MonitorProperty> monitorProperties = monitorPropertyDao.selectByMonitorProperty(monitorProperty);
        if (monitorProperties.size() > 0) {
            logger.error("设备编码已存在");
            return WebResponse.error(400, "编码已存在");
        }
        //名称判重
        MonitorProperty monitorProperty1 = new MonitorProperty();
        monitorProperty1.setEquipmentPropertyName(equipmentPropertyName);
        List<MonitorProperty> monitorProperties1 = monitorPropertyDao.selectByMonitorProperty(monitorProperty1);
        if (monitorProperties1.size() > 0) {
            logger.error("监控信息名已存在");
            return WebResponse.error(400, "监控信息名已存在");
        }
        //添加
        MonitorProperty monitorProperty2 = new MonitorProperty();
        monitorProperty2.setEquipmentPropertyName(equipmentPropertyName);
        monitorProperty2.setEquipmentPropertyCode(equipmentPropertyCode);
        monitorProperty2.setEquipmentPropertyType(equipmentPropertyType);
        monitorPropertyDao.insertMonitorProperty(monitorProperty2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitorProperty1(UpdateMonitorProperty1Qo updateMonitorProperty1Qo) {
        String equipmentPropertyCode = updateMonitorProperty1Qo.getEquipmentPropertyCode();
        String equipmentPropertyName = updateMonitorProperty1Qo.getEquipmentPropertyName();
//        Integer equipmentPropertyType = updateMonitorProperty1Qo.getEquipmentPropertyType();
        MonitorProperty monitorProperty = new MonitorProperty();
        monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
        List<MonitorProperty> monitorProperties = monitorPropertyDao.selectByMonitorProperty(monitorProperty);
        if (monitorProperties.size() > 0) {
            MonitorProperty monitorProperty1 = monitorProperties.get(0);
            //对名字做判断
            if (monitorProperty1.getEquipmentPropertyName().equals(equipmentPropertyName)) {
                return WebResponse.success();
            } else {
                //名字判重
                MonitorProperty monitorProperty2 = new MonitorProperty();
                monitorProperty2.setEquipmentPropertyName(equipmentPropertyName);
                List<MonitorProperty> monitorProperties1 = monitorPropertyDao.selectByMonitorProperty(monitorProperty2);
                if (monitorProperties1.size() > 0) {
                    logger.error("属性名称已存在");
                    return WebResponse.error(400, "属性名称已存在");
                } else {
                    monitorProperty1.setEquipmentPropertyName(equipmentPropertyName);
                    monitorPropertyDao.updateMonitorProperty(monitorProperty1);
                }
            }
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitorProperty1(DeleteMonitorProperty1Qo deleteMonitorProperty1Qo) {
        String equipmentPropertyCode = deleteMonitorProperty1Qo.getEquipmentPropertyCode();
        //判断与设备有无绑定关系
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
        //获取所有上层节点
        List<ParentNode> parentNodes = parentNodeService.selectAll();
        if (parentNodes.size() > 0) {
            for (ParentNode parentNode : parentNodes) {
                equipmentInfo.setParentNodeCode(parentNode.getParentNodeCode());
                List<EquipmentInfo> equipmentInfos = equipmentInfoService.selectByEquipmentInfo(equipmentInfo);
                if (equipmentInfos.size() > 0) {
                    logger.error("与设备存在绑定关系,删除失败");
                    return WebResponse.error(400, "与设备存在绑定关系,删除失败");
                }
            }
        }
        //判断与属性模板是否有绑定关系
        MonitorPropertyTemplateBind monitorPropertyTemplateBind = new MonitorPropertyTemplateBind();
        monitorPropertyTemplateBind.setEquipmentPropertyCode(equipmentPropertyCode);
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindService.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        if (monitorPropertyTemplateBinds.size() > 0) {
            logger.error("与属性模板有绑定关系,删除失败");
            return WebResponse.error(400, "与属性模板有绑定关系,删除失败");
        }
        //删除
        monitorPropertyDao.deleteMonitorProperty(equipmentPropertyCode);
        logger.info("成功删除属性");
        return WebResponse.success();
    }

    @Override
    public WebResponse exportExcelMonitorProperty() throws Exception {
        String filePath = "D://excel//temp.xls";
        String title = "设备监控信号表";
        Integer colunmNumber = 4;
        List<MonitorProperty> monitorProperties = monitorPropertyDao.selectAll();
        String[][] strings = new String[monitorProperties.size() + 1][4];
        strings[0][0] = "序号";
        strings[0][1] = "属性编码";
        strings[0][2] = "属性名称";
        strings[0][3] = "属性类型";
        for (int c = 1; c < strings.length; c++) {
            strings[c][0] = String.valueOf(c);
            strings[c][1] = monitorProperties.get(c - 1).getEquipmentPropertyCode();
            strings[c][2] = monitorProperties.get(c - 1).getEquipmentPropertyName();
            Integer equipmentPropertyType = monitorProperties.get(c - 1).getEquipmentPropertyType();
            if (equipmentPropertyType == 0) {
                strings[c][3] = "遥测";
            } else if (equipmentPropertyType == 1) {
                strings[c][3] = "遥信";
            } else if (equipmentPropertyType == 2) {
                strings[c][3] = "遥控";
            } else if (equipmentPropertyType == 3) {
                strings[c][3] = "遥调";
            } else {
                strings[c][3] = "说明";
            }
        }
        excelService.exportExcel(filePath, strings, title, colunmNumber);
        return WebResponse.success("http://192.168.52.50:8099/excel/temp.xls");
    }

    @Override
    public WebResponse importExcelMonitorProperty(ImportExcelMonitorPropertyQo importExcelMonitorPropertyQo) throws Exception {
        String filePath = importExcelMonitorPropertyQo.getFilePath();
        String[][] strings = excelService.importExcel(filePath);
        if (strings.length > 0) {
            for (String[] strings1 : strings) {
                String equipmentPropertyCode = strings1[0];
                String equipmentPropertyName = strings1[1];
                String equipmentPropertyTypeName = strings1[2];
                Integer equipmentPropertyType = 0;
                if (equipmentPropertyTypeName.equals("遥测")) {
                    equipmentPropertyType = 0;
                } else if (equipmentPropertyTypeName.equals("遥信")) {
                    equipmentPropertyType = 1;
                } else if (equipmentPropertyTypeName.equals("遥控")) {
                    equipmentPropertyType = 2;
                } else if (equipmentPropertyTypeName.equals("遥调")) {
                    equipmentPropertyType = 3;
                } else if (equipmentPropertyTypeName.equals("说明")) {
                    equipmentPropertyType = 4;
                } else {
                    equipmentPropertyType = 0;
                }
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                monitorProperty.setEquipmentPropertyName(equipmentPropertyName);
                monitorProperty.setEquipmentPropertyType(equipmentPropertyType);
                //todo:进行原数据的判断
                monitorPropertyDao.insertMonitorProperty(monitorProperty);
            }
        }
        return WebResponse.success();
    }


}
