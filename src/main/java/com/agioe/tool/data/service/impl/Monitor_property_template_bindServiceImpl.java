package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Vo.Equip_typeVo1;
import com.agioe.tool.data.Vo.GetEquipment_type_template_linkVo;
import com.agioe.tool.data.Vo.ShowAllMonitor_property_template_bindVo;
import com.agioe.tool.data.Vo.TemplateVo;
import com.agioe.tool.data.dao.Monitor_property_template_bindDao;
import com.agioe.tool.data.entity.Equipment_type;
import com.agioe.tool.data.entity.Monitor_property_template;
import com.agioe.tool.data.entity.Monitor_property_template_bind;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_typeService;
import com.agioe.tool.data.service.Monitor_property_templateService;
import com.agioe.tool.data.service.Monitor_property_template_bindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Monitor_property_template_bindServiceImpl implements Monitor_property_template_bindService {
    @Autowired
    private Monitor_property_template_bindDao monitor_property_template_bindDao;

    @Autowired
    private Equipment_typeService equipment_typeService;

    @Autowired
    private Monitor_property_templateService monitor_property_templateService;

    @Override
    public Integer insertMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind) {
        return monitor_property_template_bindDao.insertMonitor_property_template_bind(monitor_property_template_bind);
    }

    @Override
    public Integer deleteMonitor_property_template_bind(Integer id) {
        return monitor_property_template_bindDao.deleteMonitor_property_template_bind(id);
    }

    @Override
    public List<Monitor_property_template_bind> selectByMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind) {
        return monitor_property_template_bindDao.selectByMonitor_property_template_bind(monitor_property_template_bind);
    }

    @Override
    public Integer updateMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind) {
        return monitor_property_template_bindDao.updateMonitor_property_template_bind(monitor_property_template_bind);
    }

    @Override
    public List<Monitor_property_template_bind> selectAll() {
        return monitor_property_template_bindDao.selectAll();
    }

    @Override
    public Monitor_property_template_bind selectByid(Integer id) {
        return monitor_property_template_bindDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitor_property_template_bind() {
        List<ShowAllMonitor_property_template_bindVo> showAllMonitor_property_template_bindVos = new ArrayList<>();
        List<Monitor_property_template_bind> monitor_property_template_binds = monitor_property_template_bindDao.selectAll();
        if (monitor_property_template_binds.size() > 0) {
            for (Monitor_property_template_bind monitor_property_template_bind : monitor_property_template_binds) {
                ShowAllMonitor_property_template_bindVo showAllMonitor_property_template_bindVo = new ShowAllMonitor_property_template_bindVo();
                showAllMonitor_property_template_bindVo.setEquipment_property_code(monitor_property_template_bind.getEquipment_property_code());
                showAllMonitor_property_template_bindVo.setEquipment_property_template_code(monitor_property_template_bind.getEquipment_property_template_code());
                showAllMonitor_property_template_bindVo.setEquipment_type(monitor_property_template_bind.getEquipment_type());
                showAllMonitor_property_template_bindVos.add(showAllMonitor_property_template_bindVo);
            }
        }
        return WebResponse.success(showAllMonitor_property_template_bindVos);
    }

    @Override
    public WebResponse addMonitor_property_template_bind1(AddMonitor_property_template_bind1Qo addMonitor_property_template_bind1Qo) {
        String equipment_property_code = addMonitor_property_template_bind1Qo.getEquipment_property_code();
        String equipment_property_template_code = addMonitor_property_template_bind1Qo.getEquipment_property_template_code();
        String equipment_type = addMonitor_property_template_bind1Qo.getEquipment_type();
        Monitor_property_template_bind monitor_property_template_bind = new Monitor_property_template_bind();
        monitor_property_template_bind.setEquipment_property_code(equipment_property_code);
        monitor_property_template_bind.setEquipment_property_template_code(equipment_property_template_code);
        //判重
        List<Monitor_property_template_bind> monitor_property_template_binds = monitor_property_template_bindDao.selectByMonitor_property_template_bind(monitor_property_template_bind);
        if (monitor_property_template_binds.size() > 0) {
            return WebResponse.error(400, "绑定关系已存在");
        }
        //添加
        monitor_property_template_bind.setEquipment_type(equipment_type);
        monitor_property_template_bindDao.insertMonitor_property_template_bind(monitor_property_template_bind);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitor_property_template_bind1(UpdateMonitor_property_template_bind1Qo updateMonitor_property_template_bind1Qo) {
        Integer id = updateMonitor_property_template_bind1Qo.getId();
        String equipment_property_code = updateMonitor_property_template_bind1Qo.getEquipment_property_code();
        String equipment_property_template_code = updateMonitor_property_template_bind1Qo.getEquipment_property_template_code();
        String equipment_type = updateMonitor_property_template_bind1Qo.getEquipment_type();
        Monitor_property_template_bind monitor_property_template_bind = monitor_property_template_bindDao.selectByid(id);
        monitor_property_template_bind.setEquipment_property_template_code(equipment_property_template_code);
        monitor_property_template_bind.setEquipment_property_code(equipment_property_code);
        monitor_property_template_bind.setEquipment_type(equipment_type);
        monitor_property_template_bindDao.updateMonitor_property_template_bind(monitor_property_template_bind);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitor_property_template_bind1(DeleteMonitor_property_template_bind1Qo deleteMonitor_property_template_bind1Qo) {
        Integer id = deleteMonitor_property_template_bind1Qo.getId();
        monitor_property_template_bindDao.deleteMonitor_property_template_bind(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse getEquipment_type_template_link() {
        GetEquipment_type_template_linkVo getEquipment_type_template_linkVo = new GetEquipment_type_template_linkVo();
        List<Equip_typeVo1> equip_typeVo1s = new ArrayList<>();
        List<Equipment_type> equipment_types = equipment_typeService.selectAll();
        if (equipment_types.size() > 0) {
            for (Equipment_type equipment_type : equipment_types) {
                Equip_typeVo1 equip_typeVo1 = new Equip_typeVo1();
                equip_typeVo1.setEquipment_type_code(equipment_type.getEquipment_type_code());
                equip_typeVo1.setEquipment_type_name(equipment_type.getEquipment_type_name());
                //根据设备类型编码查询模板
                Monitor_property_template monitor_property_template = new Monitor_property_template();
                monitor_property_template.setEquipment_type(equipment_type.getEquipment_type_code());
                List<Monitor_property_template> monitor_property_templates = monitor_property_templateService.selectByMonitor_property_template(monitor_property_template);
                List<TemplateVo> templateLists = new ArrayList<>();
                if (monitor_property_templates.size() > 0) {
                    for (Monitor_property_template monitor_property_template1 : monitor_property_templates) {
                        TemplateVo templateVo = new TemplateVo();
                        templateVo.setEquipment_property_template_code(monitor_property_template1.getEquipment_property_template_code());
                        templateVo.setEquipment_property_template_name(monitor_property_template.getEquipment_property_template_name());
                        templateLists.add(templateVo);
                    }
                }
                equip_typeVo1.setTemplateLists(templateLists);
                equip_typeVo1s.add(equip_typeVo1);
            }
        }
        getEquipment_type_template_linkVo.setEquip_typeVo1s(equip_typeVo1s);
        return WebResponse.success(getEquipment_type_template_linkVo);
    }
}
