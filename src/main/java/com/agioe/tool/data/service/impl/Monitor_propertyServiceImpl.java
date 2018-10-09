package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddMonitor_property1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property1Qo;
import com.agioe.tool.data.Vo.ShowAllMonitor_propertyVo;
import com.agioe.tool.data.dao.Monitor_propertyDao;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.Monitor_property;
import com.agioe.tool.data.entity.Monitor_property_template_bind;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Monitor_propertyService;
import com.agioe.tool.data.service.Monitor_property_template_bindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Monitor_propertyServiceImpl implements Monitor_propertyService {
    @Autowired
    private Monitor_propertyDao monitor_propertyDao;

    @Autowired
    private Monitor_property_template_bindService monitor_property_template_bindService;

    @Autowired
    private Equipment_infoService equipment_infoService;

    @Override
    public Integer insertMonitor_property(Monitor_property monitor_property) {
        return monitor_propertyDao.insertMonitor_property(monitor_property);
    }

    @Override
    public Integer deleteMonitor_property(Integer id) {
        return monitor_propertyDao.deleteMonitor_property(id);
    }

    @Override
    public List<Monitor_property> selectByMonitor_property(Monitor_property monitor_property) {
        return monitor_propertyDao.selectByMonitor_property(monitor_property);
    }

    @Override
    public Integer updateMonitor_property(Monitor_property monitor_property) {
        return monitor_propertyDao.updateMonitor_property(monitor_property);
    }

    @Override
    public List<Monitor_property> selectAll() {
        return monitor_propertyDao.selectAll();
    }

    @Override
    public Monitor_property selectByid(Integer id) {
        return monitor_propertyDao.selectByid(id);
    }

    @Override
    public WebResponse showAllMonitor_property() {
        List<ShowAllMonitor_propertyVo> showAllMonitor_propertyVos = new ArrayList<>();
        List<Monitor_property> monitor_properties = monitor_propertyDao.selectAll();
        if (monitor_properties.size() > 0) {
            for (Monitor_property monitor_property : monitor_properties) {
                ShowAllMonitor_propertyVo showAllMonitor_propertyVo = new ShowAllMonitor_propertyVo();
                showAllMonitor_propertyVo.setEquipment_property_code(monitor_property.getEquipment_property_code());
                showAllMonitor_propertyVo.setEquipment_property_name(monitor_property.getEquipment_property_name());
                showAllMonitor_propertyVo.setEquipment_property_type(monitor_property.getEquipment_property_type());
                showAllMonitor_propertyVos.add(showAllMonitor_propertyVo);
            }
        }
        return WebResponse.success(showAllMonitor_propertyVos);
    }

    @Override
    public WebResponse addMonitor_property1(AddMonitor_property1Qo addMonitor_property1Qo) {
        String equipment_property_code = addMonitor_property1Qo.getEquipment_property_code();
        String equipment_property_name = addMonitor_property1Qo.getEquipment_property_name();
        Integer equipment_property_type = addMonitor_property1Qo.getEquipment_property_type();
        //编码判重
        Monitor_property monitor_property = new Monitor_property();
        monitor_property.setEquipment_property_code(equipment_property_code);
        List<Monitor_property> monitor_properties = monitor_propertyDao.selectByMonitor_property(monitor_property);
        if (monitor_properties.size() > 0) {
            return WebResponse.error(400, "编码已存在");
        }
        //名称判重
        Monitor_property monitor_property1 = new Monitor_property();
        monitor_property1.setEquipment_property_name(equipment_property_name);
        List<Monitor_property> monitor_properties1 = monitor_propertyDao.selectByMonitor_property(monitor_property1);
        if (monitor_properties1.size() > 0) {
            return WebResponse.error(400, "监控信息名已存在");
        }
        //添加
        Monitor_property monitor_property2 = new Monitor_property();
        monitor_property2.setEquipment_property_name(equipment_property_name);
        monitor_property2.setEquipment_property_code(equipment_property_code);
        monitor_property2.setEquipment_property_type(equipment_property_type);
        monitor_propertyDao.insertMonitor_property(monitor_property2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateMonitor_property1(UpdateMonitor_property1Qo updateMonitor_property1Qo) {
        Integer id = updateMonitor_property1Qo.getId();
        String equipment_property_code = updateMonitor_property1Qo.getEquipment_property_code();
        String equipment_property_name = updateMonitor_property1Qo.getEquipment_property_name();
        Integer equipment_property_type = updateMonitor_property1Qo.getEquipment_property_type();
        Monitor_property monitor_property = monitor_propertyDao.selectByid(id);
        monitor_property.setEquipment_property_type(equipment_property_type);
        monitor_property.setEquipment_property_code(equipment_property_code);
        monitor_property.setEquipment_property_name(equipment_property_name);
        monitor_propertyDao.updateMonitor_property(monitor_property);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteMonitor_property1(DeleteMonitor_property1Qo deleteMonitor_property1Qo) {
        Integer id = deleteMonitor_property1Qo.getId();
        Monitor_property monitor_property = monitor_propertyDao.selectByid(id);
        String equipment_property_code = monitor_property.getEquipment_property_code();
        //判断与设备有无绑定关系
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setEquipment_property_code(equipment_property_code);
        List<Equipment_info> equipment_infos = equipment_infoService.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            return WebResponse.error(400, "与设备信息有绑定关系,删除失败");
        }
        //判断与属性模板是否有绑定关系
        Monitor_property_template_bind monitor_property_template_bind = new Monitor_property_template_bind();
        monitor_property_template_bind.setEquipment_property_code(equipment_property_code);
        List<Monitor_property_template_bind> monitor_property_template_binds = monitor_property_template_bindService.selectByMonitor_property_template_bind(monitor_property_template_bind);
        if (monitor_property_template_binds.size() > 0) {
            return WebResponse.error(400, "与属性模板有绑定关系,删除失败");
        }
        //删除
        monitor_propertyDao.deleteMonitor_property(id);
        return WebResponse.success();
    }
}
