package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Vo.EquipTypeVo1;
import com.agioe.tool.data.Vo.ShowAllMonitorPropertyTemplateBindVo;
import com.agioe.tool.data.Vo.TemplateVo;
import com.agioe.tool.data.dao.MonitorPropertyTemplateBindDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.service.EquipmentTypeService;
import com.agioe.tool.data.service.MonitorPropertyService;
import com.agioe.tool.data.service.MonitorPropertyTemplateBindService;
import com.agioe.tool.data.service.MonitorPropertyTemplateService;
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
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
                    showAllMonitorPropertyTemplateBindVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate1.getEquipmentPropertyTemplateName());
                }

                String equipmentType = monitorPropertyTemplateBind.getEquipmentType();
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
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
}
