package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.Vo.ShowAllMonitorPropertyTemplateVo;
import com.agioe.tool.data.dao.MonitorPropertyTemplateDao;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.EquipmentTypeService;
import com.agioe.tool.data.service.MonitorPropertyTemplateBindService;
import com.agioe.tool.data.service.MonitorPropertyTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorPropertyTemplateServiceImpl implements MonitorPropertyTemplateService {
    @Autowired
    private MonitorPropertyTemplateDao monitorPropertyTemplateDao;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private MonitorPropertyTemplateBindService monitorPropertyTemplateBindService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @Override
    public Integer insertMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate) {
        return monitorPropertyTemplateDao.insertMonitorPropertyTemplate(monitorPropertyTemplate);
    }

    @Override
    public Integer deleteMonitorPropertyTemplate(String equipment_property_template_code) {
        return monitorPropertyTemplateDao.deleteMonitorPropertyTemplate(equipment_property_template_code);
    }

    @Override
    public List<MonitorPropertyTemplate> selectByMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate) {
        return monitorPropertyTemplateDao.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
    }

    @Override
    public Integer updateMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate) {
        return monitorPropertyTemplateDao.updateMonitorPropertyTemplate(monitorPropertyTemplate);
    }

    @Override
    public List<MonitorPropertyTemplate> selectAll() {
        return monitorPropertyTemplateDao.selectAll();
    }

    @Override
    public MonitorPropertyTemplate selectByid(Integer id) {
        return monitorPropertyTemplateDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitorPropertyTemplate() {
        List<ShowAllMonitorPropertyTemplateVo> showAllMonitorPropertyTemplateVos = new ArrayList<>();
        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateDao.selectAll();
        if (monitorPropertyTemplates.size() > 0) {
            for (MonitorPropertyTemplate monitorPropertyTemplate : monitorPropertyTemplates) {
                ShowAllMonitorPropertyTemplateVo showAllMonitorPropertyTemplateVo = new ShowAllMonitorPropertyTemplateVo();
                showAllMonitorPropertyTemplateVo.setEquipmentPropertyTemplateCode(monitorPropertyTemplate.getEquipmentPropertyTemplateCode());
                showAllMonitorPropertyTemplateVo.setEquipmentPropertyTemplateName(monitorPropertyTemplate.getEquipmentPropertyTemplateName());
                String equipmentType = monitorPropertyTemplate.getEquipmentType();
                //根据编码进行查找名字
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
                if (equipmentTypes.size() > 0) {
                    EquipmentType equipmentType2 = equipmentTypes.get(0);
                    showAllMonitorPropertyTemplateVo.setEquipmentTypeName(equipmentType2.getEquipmentTypeName());
                }
                showAllMonitorPropertyTemplateVo.setEquipmentPropertyTemplateDescription(monitorPropertyTemplate.getEquipmentPropertyTemplateDescription());
                showAllMonitorPropertyTemplateVos.add(showAllMonitorPropertyTemplateVo);
            }
        }
        return WebResponse.success(showAllMonitorPropertyTemplateVos);
    }

    @Override
    public WebResponse addMonitorPropertyTemplate1(AddMonitorPropertyTemplate1Qo addMonitorPropertyTemplate1Qo) {
        String equipmentPropertyTemplateCode = addMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateCode();
        String equipmentPropertyTemplateDescription = addMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateDescription();
        String equipmentPropertyTemplateName = addMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateName();
        String equipmentType = addMonitorPropertyTemplate1Qo.getEquipmentType();
        //模板编码判重
        MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
        monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateDao.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
        if (monitorPropertyTemplates.size() > 0) {
            return WebResponse.error(400, "模板编码已存在");
        }
        //模板名称判重
        MonitorPropertyTemplate monitorPropertyTemplate1 = new MonitorPropertyTemplate();
        monitorPropertyTemplate1.setEquipmentPropertyTemplateName(equipmentPropertyTemplateName);
        List<MonitorPropertyTemplate> monitorPropertyTemplates1 = monitorPropertyTemplateDao.selectByMonitorPropertyTemplate(monitorPropertyTemplate1);
        if (monitorPropertyTemplates1.size() > 0) {
            return WebResponse.error(400, "模板名称已存在");
        }
        //添加
        MonitorPropertyTemplate monitorPropertyTemplate2 = new MonitorPropertyTemplate();
        monitorPropertyTemplate2.setEquipmentPropertyTemplateName(equipmentPropertyTemplateName);
        monitorPropertyTemplate2.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        monitorPropertyTemplate2.setEquipmentPropertyTemplateDescription(equipmentPropertyTemplateDescription);
        monitorPropertyTemplate2.setEquipmentType(equipmentType);
        monitorPropertyTemplateDao.insertMonitorPropertyTemplate(monitorPropertyTemplate2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitorPropertyTemplate1(UpdateMonitorPropertyTemplate1Qo updateMonitorPropertyTemplate1Qo) {
        String equipmentPropertyTemplateCode = updateMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateCode();
        String equipmentPropertyTemplateDescription = updateMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateDescription();
        String equipmentPropertyTemplateName = updateMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateName();
        String equipmentType = updateMonitorPropertyTemplate1Qo.getEquipmentType();

        MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
        monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateDao.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
        if (monitorPropertyTemplates.size() > 0) {
            MonitorPropertyTemplate monitorPropertyTemplate1 = monitorPropertyTemplates.get(0);
            monitorPropertyTemplate1.setEquipmentType(equipmentType);
            monitorPropertyTemplate1.setEquipmentPropertyTemplateDescription(equipmentPropertyTemplateDescription);
            monitorPropertyTemplate1.setEquipmentPropertyTemplateName(equipmentPropertyTemplateName);
            monitorPropertyTemplateDao.updateMonitorPropertyTemplate(monitorPropertyTemplate1);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitorPropertyTemplate1(DeleteMonitorPropertyTemplate1Qo deleteMonitorPropertyTemplate1Qo) {
        String equipmentPropertyTemplateCode = deleteMonitorPropertyTemplate1Qo.getEquipmentPropertyTemplateCode();
        //判断与设备有无绑定关系
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoService.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            return WebResponse.error(400, "与设备存在绑定关系,删除失败");
        }
        //判断与监控信息有无绑定关系
        MonitorPropertyTemplateBind monitorPropertyTemplateBind = new MonitorPropertyTemplateBind();
        monitorPropertyTemplateBind.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindService.selectByMonitorPropertyTemplateBind(monitorPropertyTemplateBind);
        if (monitorPropertyTemplateBinds.size() > 0) {
            return WebResponse.error(400, "与监控信息存在绑定关系,删除失败");
        }
        //删除
        monitorPropertyTemplateDao.deleteMonitorPropertyTemplate(equipmentPropertyTemplateCode);
        return WebResponse.success();
    }
}
