package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.EquipTypeVo1;
import com.agioe.tool.data.Vo.ShowAllMonitorPropertyTemplateBindVo;
import com.agioe.tool.data.Vo.TemplateVo;
import com.agioe.tool.data.dao.MonitorPropertyTemplateBindDao;
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
public class MonitorPropertyTemplateBindServiceImpl implements MonitorPropertyTemplateBindService {
    @Autowired
    private MonitorPropertyTemplateBindDao monitorPropertyTemplateBindDao;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;

    @Autowired
    private MonitorPropertyService monitorPropertyService;

    @Autowired
    private ExcelService excelService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Integer insertMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind) {
        return monitorPropertyTemplateBindDao.insertMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
    }

    @Override
    public Integer deleteMonitorPropertyTemplateBind(Integer id) {
        return monitorPropertyTemplateBindDao.deleteMonitorPropertyTemplateBind(id);
    }

    @Override
    public List<MonitorPropertyTemplateBind> selectByMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind) {
        return monitorPropertyTemplateBindDao.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
    }

    @Override
    public Integer updateMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind) {
        return monitorPropertyTemplateBindDao.updateMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
    }

    @Override
    public List<MonitorPropertyTemplateBind> selectAll() {
        return monitorPropertyTemplateBindDao.selectAll();
    }

    @Override
    public MonitorPropertyTemplateBind selectByid(Integer id) {
        return monitorPropertyTemplateBindDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitorPropertyTemplateBind() {
        List<ShowAllMonitorPropertyTemplateBindVo> showAllMonitor_propertyTemplateBindVos = new ArrayList<>();
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindDao.selectAll();
        if (monitorPropertyTemplateBinds.size() > 0) {
            for (MonitorPropertyTemplateBind monitorPropertyTemplateBind : monitorPropertyTemplateBinds) {
                ShowAllMonitorPropertyTemplateBindVo showAllMonitorPropertyTemplateBindVo = new ShowAllMonitorPropertyTemplateBindVo();
                String equipmentPropertyCode = monitorPropertyTemplateBind.getEquipmentPropertyCode();
                showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyCode(equipmentPropertyCode);
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    MonitorProperty monitorProperty1 = monitorProperties.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyType(monitorProperty1.getEquipmentPropertyType());
                }

                String equipmentPropertyTemplateCode = monitorPropertyTemplateBind.getEquipmentPropertyTemplateCode();
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                }

                String equipmentType = monitorPropertyTemplateBind.getEquipmentType();
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
                showAllMonitorPropertyTemplateBindVo.setEquipmentType(equipmentType);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
                if (equipmentTypes.size() > 0) {
                    EquipmentType equipmentType2 = equipmentTypes.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentTypeName(equipmentType2.getEquipmentTypeName());
                }

                showAllMonitorPropertyTemplateBindVo.setId(monitorPropertyTemplateBind.getId());

                showAllMonitor_propertyTemplateBindVos.add(showAllMonitorPropertyTemplateBindVo);
            }
        }
        return WebResponse.success(showAllMonitor_propertyTemplateBindVos);
    }

    @Override
    public WebResponse showPageMonitorPropertyTemplateBind(ShowPageMonitorPropertyTemplateBindQo showPageMonitorPropertyTemplateBindQo) {
        Integer pageNow = showPageMonitorPropertyTemplateBindQo.getPageNow();
        Integer pageSize = showPageMonitorPropertyTemplateBindQo.getPageSize();
        String equipmentPropertyTemplateCode1 = showPageMonitorPropertyTemplateBindQo.getEquipmentPropertyTemplateCode();
        MonitorPropertyTemplateBind monitorPropertyTemplateBind111=new MonitorPropertyTemplateBind();
        monitorPropertyTemplateBind111.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode1);
        Integer countNums = monitorPropertyTemplateBindDao.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind111).size();
        List<ShowAllMonitorPropertyTemplateBindVo> showAllMonitor_propertyTemplateBindVos = new ArrayList<>();
        PageHelper.startPage(pageNow, pageSize);
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindDao.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind111);
        if (monitorPropertyTemplateBinds.size() > 0) {
            for (MonitorPropertyTemplateBind monitorPropertyTemplateBind : monitorPropertyTemplateBinds) {
                ShowAllMonitorPropertyTemplateBindVo showAllMonitorPropertyTemplateBindVo = new ShowAllMonitorPropertyTemplateBindVo();
                String equipmentPropertyCode = monitorPropertyTemplateBind.getEquipmentPropertyCode();
                showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyCode(equipmentPropertyCode);
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                if (monitorProperties.size() > 0) {
                    MonitorProperty monitorProperty1 = monitorProperties.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyName(monitorProperty1.getEquipmentPropertyName());
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyType(monitorProperty1.getEquipmentPropertyType());
                }

                String equipmentPropertyTemplateCode = monitorPropertyTemplateBind.getEquipmentPropertyTemplateCode();
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                }

                String equipmentType = monitorPropertyTemplateBind.getEquipmentType();
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
                showAllMonitorPropertyTemplateBindVo.setEquipmentType(equipmentType);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
                if (equipmentTypes.size() > 0) {
                    EquipmentType equipmentType2 = equipmentTypes.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentTypeName(equipmentType2.getEquipmentTypeName());
                }

                showAllMonitorPropertyTemplateBindVo.setId(monitorPropertyTemplateBind.getId());

                showAllMonitor_propertyTemplateBindVos.add(showAllMonitorPropertyTemplateBindVo);
            }
        }
        PageBean<ShowAllMonitorPropertyTemplateBindVo> pageData = new PageBean<>(pageNow, pageSize, countNums);
        pageData.setItems(showAllMonitor_propertyTemplateBindVos);
        return WebResponse.success(pageData);
    }

    @Override
    public WebResponse addMonitorPropertyTemplateBind1(AddMonitorPropertyTemplateBind1Qo addMonitorPropertyTemplateBind1Qo) {
        String equipmentPropertyCode = addMonitorPropertyTemplateBind1Qo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = addMonitorPropertyTemplateBind1Qo.getEquipmentPropertyTemplateCode();
        String equipmentType = addMonitorPropertyTemplateBind1Qo.getEquipmentType();
        MonitorPropertyTemplateBind monitorPropertyTemplateBind = new MonitorPropertyTemplateBind();
        monitorPropertyTemplateBind.setEquipmentPropertyCode(equipmentPropertyCode);
        monitorPropertyTemplateBind.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        //判重
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindDao.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        if (monitorPropertyTemplateBinds.size() > 0) {
            logger.error("关联模板添加失败");
            return WebResponse.error(400, "绑定关系已存在");
        }
        //添加
        monitorPropertyTemplateBind.setEquipmentType(equipmentType);
        monitorPropertyTemplateBindDao.insertMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitorPropertyTemplateBind1(UpdateMonitorPropertyTemplateBind1Qo updateMonitorPropertyTemplateBind1Qo) {
        Integer id = updateMonitorPropertyTemplateBind1Qo.getId();
        String equipmentPropertyCode = updateMonitorPropertyTemplateBind1Qo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = updateMonitorPropertyTemplateBind1Qo.getEquipmentPropertyTemplateCode();
        String equipmentType = updateMonitorPropertyTemplateBind1Qo.getEquipmentType();
        MonitorPropertyTemplateBind monitorPropertyTemplateBind = monitorPropertyTemplateBindDao.selectByid(id);
        monitorPropertyTemplateBind.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        monitorPropertyTemplateBind.setEquipmentPropertyCode(equipmentPropertyCode);
        monitorPropertyTemplateBind.setEquipmentType(equipmentType);
        monitorPropertyTemplateBindDao.updateMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        logger.info("更新关联模板");
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitorPropertyTemplateBind1(DeleteMonitorPropertyTemplateBind1Qo deleteMonitorPropertyTemplateBind1Qo) {
        Integer id = deleteMonitorPropertyTemplateBind1Qo.getId();
        monitorPropertyTemplateBindDao.deleteMonitorPropertyTemplateBind(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse getEquipmentTypeTemplateLink() {
//        GetEquipmentTypeTemplateLinkVo getEquipmentTypeTemplateLinkVo = new GetEquipmentTypeTemplateLinkVo();
//        EquipTypeVo1ListVo equipTypeVo1ListVo=new EquipTypeVo1ListVo();
        List<EquipTypeVo1> equipTypeVo1s = new ArrayList<>();
        List<EquipmentType> equipmentTypes = equipmentTypeService.selectAll();
        if (equipmentTypes.size() > 0) {
            for (EquipmentType equipmentType : equipmentTypes) {
                EquipTypeVo1 equipTypeVo1 = new EquipTypeVo1();
                equipTypeVo1.setCode(equipmentType.getEquipmentTypeCode());
                equipTypeVo1.setName(equipmentType.getEquipmentTypeName());
                //根据设备类型编码查询模板
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentType(equipmentType.getEquipmentTypeCode());
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                List<TemplateVo> templateLists = new ArrayList<>();
                if (monitorPropertyTemplates.size() > 0) {
                    for (MonitorPropertyTemplate monitorPropertyTemplate1 : monitorPropertyTemplates) {
                        TemplateVo templateVo = new TemplateVo();
                        templateVo.setCode(monitorPropertyTemplate1.getEquipmentPropertyTemplateCode());
                        templateVo.setName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                        templateLists.add(templateVo);
                    }
                }
                equipTypeVo1.setChildren(templateLists);
                equipTypeVo1s.add(equipTypeVo1);
            }
        }
//        equipTypeVo1ListVo.setEquipTypeVo1List(equipTypeVo1s);
//        getEquipmentTypeTemplateLinkVo.setChildren(equipTypeVo1s);
        return WebResponse.success(equipTypeVo1s);
    }

    @Override
    public WebResponse exportExcelMonitorPropertyTemplateBind() throws Exception {
        String filePath = "D://excel//temp.xls";
        String title = "监控信号模板关联表";
        Integer colunmNumber = 6;
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindDao.selectAll();
        String[][] strings = new String[monitorPropertyTemplateBinds.size() + 1][6];
        strings[0][0] = "序号";
        strings[0][1] = "设备类型";
        strings[0][2] = "模板名称";
        strings[0][3] = "属性编码";
        strings[0][4] = "属性名称";
        strings[0][5] = "属性类型";
        for (int c = 1; c < strings.length; c++) {
            strings[c][0] = String.valueOf(c);
            //获取设备类型名称
            String equipmentType = monitorPropertyTemplateBinds.get(c - 1).getEquipmentType();
            EquipmentType equipmentType1 = new EquipmentType();
            equipmentType1.setEquipmentTypeCode(equipmentType);
            List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
            if (equipmentTypes.size() > 0) {
                strings[c][1] = equipmentTypes.get(0).getEquipmentTypeName();
            }
            //获取模板名称
            String equipmentPropertyTemplateCode = monitorPropertyTemplateBinds.get(c - 1).getEquipmentPropertyTemplateCode();
            MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
            monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
            List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
            if (monitorPropertyTemplates.size() > 0) {
                strings[c][2] = monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateName();
            }
            strings[c][3] = monitorPropertyTemplateBinds.get(c - 1).getEquipmentPropertyCode();
            //获取属性名称
            String equipmentPropertyCode = monitorPropertyTemplateBinds.get(c - 1).getEquipmentPropertyCode();
            MonitorProperty monitorProperty = new MonitorProperty();
            monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
            List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
            if (monitorProperties.size() > 0) {
                strings[c][4] = monitorProperties.get(0).getEquipmentPropertyName();
                Integer equipmentPropertyType = monitorProperties.get(0).getEquipmentPropertyType();
                if (equipmentPropertyType == 0) {
                    strings[c][5] = "遥测";
                } else if (equipmentPropertyType == 1) {
                    strings[c][5] = "遥信";
                } else if (equipmentPropertyType == 2) {
                    strings[c][5] = "遥控";
                } else if (equipmentPropertyType == 3) {
                    strings[c][5] = "遥调";
                } else {
                    strings[c][5] = "说明";
                }
            }
        }
        excelService.exportExcel(filePath, strings, title, colunmNumber);
        return WebResponse.success("http://192.168.52.50:8099/excel/temp.xls");
    }

    @Override
    public WebResponse importExcelMonitorPropertyTemplateBind(ImportExcelMonitorPropertyTemplateBindQo importExcelMonitorPropertyTemplateBindQo) throws Exception {
        String filePath = importExcelMonitorPropertyTemplateBindQo.getFilePath();
        String[][] strings = excelService.importExcel(filePath);
        if (strings.length > 0) {
            for (String[] strings1 : strings) {
                String equipmentTypeName = strings1[0];
                String equipmentTypeCode = "";
                //获取设备类型编码
                EquipmentType equipmentType = new EquipmentType();
                equipmentType.setEquipmentTypeName(equipmentTypeName);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType);
                if (equipmentTypes.size() > 0) {
                    equipmentTypeCode = equipmentTypes.get(0).getEquipmentTypeCode();
                }
                String equipmentPropertyTemplateName = strings1[1];
                String equipmentPropertyTemplateCode = "";
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateName(equipmentPropertyTemplateName);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    equipmentPropertyTemplateCode = monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateCode();
                }
                String equipmentPropertyCode = strings1[2];
                MonitorPropertyTemplateBind monitorPropertyTemplateBind = new MonitorPropertyTemplateBind();
                monitorPropertyTemplateBind.setEquipmentType(equipmentTypeCode);
                monitorPropertyTemplateBind.setEquipmentPropertyCode(equipmentPropertyCode);
                monitorPropertyTemplateBind.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                monitorPropertyTemplateBindDao.insertMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
            }
        }
        return WebResponse.success();
    }
}
