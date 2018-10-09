package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddEquipment_typeQo;
import com.agioe.tool.data.Qo.DeleteEquipment_type1Qo;
import com.agioe.tool.data.Qo.UpdateEquipment_type1Qo;
import com.agioe.tool.data.Vo.ShowAllEquipment_typeVo;
import com.agioe.tool.data.dao.Equipment_typeDao;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.Equipment_type;
import com.agioe.tool.data.entity.Monitor_property_template;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Equipment_typeService;
import com.agioe.tool.data.service.Monitor_property_templateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Equipment_typeServiceImpl implements Equipment_typeService {
    @Autowired
    private Equipment_typeDao equipment_typeDao;

    @Autowired
    private Monitor_property_templateService monitor_property_templateService;

    @Autowired
    private Equipment_infoService equipment_infoService;

    @Override
    public Integer insertEquipment_type(Equipment_type equipment_type) {
        return equipment_typeDao.insertEquipment_type(equipment_type);
    }

    @Override
    public Integer deleteEquipment_type(Integer id) {
        return equipment_typeDao.deleteEquipment_type(id);
    }

    @Override
    public List<Equipment_type> selectByEquipment_type(Equipment_type equipment_type) {
        return equipment_typeDao.selectByEquipment_type(equipment_type);
    }

    @Override
    public Integer updateEquipment_type(Equipment_type equipment_type) {
        return equipment_typeDao.updateEquipment_type(equipment_type);
    }

    @Override
    public List<Equipment_type> selectAll() {
        return equipment_typeDao.selectAll();
    }

    @Override
    public Equipment_type selectByid(Integer id) {
        return equipment_typeDao.selectByid(id);
    }

    @Override
    public WebResponse showAllEquipment_type() {
        List<ShowAllEquipment_typeVo> showAllEquipment_typeVos = new ArrayList<>();
        List<Equipment_type> equipment_types = equipment_typeDao.selectAll();
        if (equipment_types.size() > 0) {
            for (Equipment_type equipment_type : equipment_types) {
                ShowAllEquipment_typeVo showAllEquipment_typeVo = new ShowAllEquipment_typeVo();
                showAllEquipment_typeVo.setEquipment_type_code(equipment_type.getEquipment_type_code());
                showAllEquipment_typeVo.setEquipment_type_name(equipment_type.getEquipment_type_name());
                showAllEquipment_typeVos.add(showAllEquipment_typeVo);
            }
        }
        return WebResponse.success(showAllEquipment_typeVos);
    }

    @Override
    public WebResponse addEquipment_type1(AddEquipment_typeQo addEquipment_typeQo) {
        String equipment_type_code = addEquipment_typeQo.getEquipment_type_code();
        String equipment_type_name = addEquipment_typeQo.getEquipment_type_name();

        Equipment_type equipment_type = new Equipment_type();
        equipment_type.setEquipment_type_code(equipment_type_code);
        List<Equipment_type> equipment_types = equipment_typeDao.selectByEquipment_type(equipment_type);
        if (equipment_types.size() > 0) {
            return WebResponse.error(400, "编码已存在");
        }

        Equipment_type equipment_type1 = new Equipment_type();
        equipment_type1.setEquipment_type_name(equipment_type_name);
        List<Equipment_type> equipment_types1 = equipment_typeDao.selectByEquipment_type(equipment_type1);
        if (equipment_types1.size() > 0) {
            return WebResponse.error(400, "设备类型已存在");
        }

        Equipment_type equipment_type2 = new Equipment_type();
        equipment_type2.setEquipment_type_name(equipment_type_name);
        equipment_type2.setEquipment_type_code(equipment_type_code);
        equipment_typeDao.insertEquipment_type(equipment_type2);
        return WebResponse.success();

    }

    @Override
    public WebResponse updateEquipment_type1(UpdateEquipment_type1Qo updateEquipment_type1Qo) {
        Integer id = updateEquipment_type1Qo.getId();
        String equipment_type_code = updateEquipment_type1Qo.getEquipment_type_code();
        String equipment_type_name = updateEquipment_type1Qo.getEquipment_type_name();
        //根据id进行查询
        Equipment_type equipment_type = equipment_typeDao.selectByid(id);
        equipment_type.setEquipment_type_code(equipment_type_code);
        equipment_type.setEquipment_type_name(equipment_type_name);
        //更新
        equipment_typeDao.updateEquipment_type(equipment_type);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteEquipment_type1(DeleteEquipment_type1Qo deleteEquipment_type1Qo) {
        Integer id = deleteEquipment_type1Qo.getId();
        Equipment_type equipment_type = equipment_typeDao.selectByid(id);
        String equipment_type_code = equipment_type.getEquipment_type_code();
        //确认是否与模板信息有绑定
        Monitor_property_template monitor_property_template = new Monitor_property_template();
        monitor_property_template.setEquipment_type(equipment_type_code);
        List<Monitor_property_template> monitor_property_templates = monitor_property_templateService.selectByMonitor_property_template(monitor_property_template);
        if (monitor_property_templates.size() > 0) {
            return WebResponse.error(400, "有绑定关系,删除失败");
        }
        //确认是否是设备有关联
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setEquipment_type(equipment_type_code);
        List<Equipment_info> equipment_infos = equipment_infoService.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            return WebResponse.error(400, "有绑定关系,删除失败");
        }
        //删除
        equipment_typeDao.deleteEquipment_type(id);
        return WebResponse.success();
    }
}
