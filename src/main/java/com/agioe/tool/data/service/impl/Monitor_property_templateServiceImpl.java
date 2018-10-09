package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template1Qo;
import com.agioe.tool.data.Vo.ShowAllMonitor_property_templateVo;
import com.agioe.tool.data.dao.Monitor_property_templateDao;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.Monitor_property_template;
import com.agioe.tool.data.entity.Monitor_property_template_bind;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Monitor_property_templateService;
import com.agioe.tool.data.service.Monitor_property_template_bindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Monitor_property_templateServiceImpl implements Monitor_property_templateService {
    @Autowired
    private Monitor_property_templateDao monitor_property_templateDao;

    @Autowired
    private Equipment_infoService equipment_infoService;

    @Autowired
    private Monitor_property_template_bindService monitor_property_template_bindService;

    @Override
    public Integer insertMonitor_property_template(Monitor_property_template monitor_property_template) {
        return monitor_property_templateDao.insertMonitor_property_template(monitor_property_template);
    }

    @Override
    public Integer deleteMonitor_property_template(Integer id) {
        return monitor_property_templateDao.deleteMonitor_property_template(id);
    }

    @Override
    public List<Monitor_property_template> selectByMonitor_property_template(Monitor_property_template monitor_property_template) {
        return monitor_property_templateDao.selectByMonitor_property_template(monitor_property_template);
    }

    @Override
    public Integer updateMonitor_property_template(Monitor_property_template monitor_property_template) {
        return monitor_property_templateDao.updateMonitor_property_template(monitor_property_template);
    }

    @Override
    public List<Monitor_property_template> selectAll() {
        return monitor_property_templateDao.selectAll();
    }

    @Override
    public Monitor_property_template selectByid(Integer id) {
        return monitor_property_templateDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitor_property_template() {
        List<ShowAllMonitor_property_templateVo> showAllMonitor_property_templateVos = new ArrayList<>();
        List<Monitor_property_template> monitor_property_templates = monitor_property_templateDao.selectAll();
        if (monitor_property_templates.size() > 0) {
            for (Monitor_property_template monitor_property_template : monitor_property_templates) {
                ShowAllMonitor_property_templateVo showAllMonitor_property_templateVo = new ShowAllMonitor_property_templateVo();
                showAllMonitor_property_templateVo.setEquipment_property_template_code(monitor_property_template.getEquipment_property_template_code());
                showAllMonitor_property_templateVo.setEquipment_property_template_name(monitor_property_template.getEquipment_property_template_name());
                showAllMonitor_property_templateVo.setEquipment_type(monitor_property_template.getEquipment_type());
                showAllMonitor_property_templateVo.setEquipment_property_template_description(monitor_property_template.getEquipment_property_template_description());
                showAllMonitor_property_templateVos.add(showAllMonitor_property_templateVo);
            }
        }
        return WebResponse.success(showAllMonitor_property_templateVos);
    }

    @Override
    public WebResponse addMonitor_property_template1(AddMonitor_property_template1Qo addMonitor_property_template1Qo) {
        String equipment_property_template_code = addMonitor_property_template1Qo.getEquipment_property_template_code();
        String equipment_property_template_description = addMonitor_property_template1Qo.getEquipment_property_template_description();
        String equipment_property_template_name = addMonitor_property_template1Qo.getEquipment_property_template_name();
        String equipment_type = addMonitor_property_template1Qo.getEquipment_type();
        //模板编码判重
        Monitor_property_template monitor_property_template = new Monitor_property_template();
        monitor_property_template.setEquipment_property_template_code(equipment_property_template_code);
        List<Monitor_property_template> monitor_property_templates = monitor_property_templateDao.selectByMonitor_property_template(monitor_property_template);
        if (monitor_property_templates.size() > 0) {
            return WebResponse.error(400, "模板编码已存在");
        }
        //模板名称判重
        Monitor_property_template monitor_property_template1 = new Monitor_property_template();
        monitor_property_template1.setEquipment_property_template_name(equipment_property_template_name);
        List<Monitor_property_template> monitor_property_templates1 = monitor_property_templateDao.selectByMonitor_property_template(monitor_property_template1);
        if (monitor_property_templates1.size() > 0) {
            return WebResponse.error(400, "模板名称已存在");
        }
        //添加
        Monitor_property_template monitor_property_template2 = new Monitor_property_template();
        monitor_property_template2.setEquipment_property_template_name(equipment_property_template_name);
        monitor_property_template2.setEquipment_property_template_code(equipment_property_template_code);
        monitor_property_template2.setEquipment_property_template_description(equipment_property_template_description);
        monitor_property_template2.setEquipment_type(equipment_type);
        monitor_property_templateDao.insertMonitor_property_template(monitor_property_template2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitor_property_template1(UpdateMonitor_property_template1Qo updateMonitor_property_template1Qo) {
        Integer id = updateMonitor_property_template1Qo.getId();
        String equipment_property_template_code = updateMonitor_property_template1Qo.getEquipment_property_template_code();
        String equipment_property_template_description = updateMonitor_property_template1Qo.getEquipment_property_template_description();
        String equipment_property_template_name = updateMonitor_property_template1Qo.getEquipment_property_template_name();
        String equipment_type = updateMonitor_property_template1Qo.getEquipment_type();
        Monitor_property_template monitor_property_template = monitor_property_templateDao.selectByid(id);
        monitor_property_template.setEquipment_type(equipment_type);
        monitor_property_template.setEquipment_property_template_description(equipment_property_template_description);
        monitor_property_template.setEquipment_property_template_code(equipment_property_template_code);
        monitor_property_template.setEquipment_property_template_name(equipment_property_template_name);
        monitor_property_templateDao.updateMonitor_property_template(monitor_property_template);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitor_property_template1(DeleteMonitor_property_template1Qo deleteMonitor_property_template1Qo) {
        Integer id = deleteMonitor_property_template1Qo.getId();
        Monitor_property_template monitor_property_template = monitor_property_templateDao.selectByid(id);
        String equipment_property_template_code = monitor_property_template.getEquipment_property_template_code();
        //判断与设备有无绑定关系
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setEquipment_property_template_code(equipment_property_template_code);
        List<Equipment_info> equipment_infos = equipment_infoService.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            return WebResponse.error(400, "与设备存在绑定关系,删除失败");
        }
        //判断与监控信息有无绑定关系
        Monitor_property_template_bind monitor_property_template_bind = new Monitor_property_template_bind();
        monitor_property_template_bind.setEquipment_property_template_code(equipment_property_template_code);
        List<Monitor_property_template_bind> monitor_property_template_binds = monitor_property_template_bindService.selectByMonitor_property_template_bind(monitor_property_template_bind);
        if (monitor_property_template_binds.size() > 0) {
            return WebResponse.error(400, "与监控信息存在绑定关系,删除失败");
        }
        //删除
        monitor_property_templateDao.deleteMonitor_property_template(id);
        return WebResponse.success();
    }
}
