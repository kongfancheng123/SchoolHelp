package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddEquipmentTypeQo;
import com.agioe.tool.data.Qo.DeleteEquipmentType1Qo;
import com.agioe.tool.data.Qo.UpdateEquipmentType1Qo;
import com.agioe.tool.data.Vo.ShowAllEquipmentTypeVo;
import com.agioe.tool.data.dao.EquipmentTypeDao;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.MonitorPropertyTemplate;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.EquipmentTypeService;
import com.agioe.tool.data.service.MonitorPropertyTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    @Autowired
    private EquipmentTypeDao equipmentTypeDao;

    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Override
    public Integer insertEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeDao.insertEquipmentType(equipmentType);
    }

    @Override
    public Integer deleteEquipmentType(String equipment_type_code) {
        return equipmentTypeDao.deleteEquipmentType(equipment_type_code);
    }

    @Override
    public List<EquipmentType> selectByEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeDao.selectByEquipmentType(equipmentType);
    }

    @Override
    public Integer updateEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeDao.updateEquipmentType(equipmentType);
    }

    @Override
    public List<EquipmentType> selectAll() {
        return equipmentTypeDao.selectAll();
    }

    @Override
    public EquipmentType selectByid(Integer id) {
        return equipmentTypeDao.selectByid(id);
    }

    @Override
    public WebResponse showAllEquipmentType() {
        List<ShowAllEquipmentTypeVo> showAllEquipmentTypeVos = new ArrayList<>();
        List<EquipmentType> equipmentTypes = equipmentTypeDao.selectAll();
        if (equipmentTypes.size() > 0) {
            for (EquipmentType equipmentType : equipmentTypes) {
                ShowAllEquipmentTypeVo showAllEquipmentTypeVo = new ShowAllEquipmentTypeVo();
                showAllEquipmentTypeVo.setEquipmentTypeCode(equipmentType.getEquipmentTypeCode());
                showAllEquipmentTypeVo.setEquipmentTypeName(equipmentType.getEquipmentTypeName());
                showAllEquipmentTypeVos.add(showAllEquipmentTypeVo);
            }
        }
        return WebResponse.success(showAllEquipmentTypeVos);
    }

    @Override
    public WebResponse addEquipmentType1(AddEquipmentTypeQo addEquipmentTypeQo) {
        String equipmentTypeCode = addEquipmentTypeQo.getEquipmentTypeCode();
        String equipmentTypeName = addEquipmentTypeQo.getEquipmentTypeName();

        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipmentTypeCode(equipmentTypeCode);
        List<EquipmentType> equipmentTypes = equipmentTypeDao.selectByEquipmentType(equipmentType);
        if (equipmentTypes.size() > 0) {
            return WebResponse.error(400, "编码已存在");
        }

        EquipmentType equipmentType1 = new EquipmentType();
        equipmentType1.setEquipmentTypeName(equipmentTypeName);
        List<EquipmentType> equipmentTypes1 = equipmentTypeDao.selectByEquipmentType(equipmentType1);
        if (equipmentTypes1.size() > 0) {
            return WebResponse.error(400, "设备类型已存在");
        }

        EquipmentType equipmentType2 = new EquipmentType();
        equipmentType2.setEquipmentTypeName(equipmentTypeName);
        equipmentType2.setEquipmentTypeCode(equipmentTypeCode);
        equipmentTypeDao.insertEquipmentType(equipmentType2);
        return WebResponse.success();

    }

    @Override
    public WebResponse updateEquipmentType1(UpdateEquipmentType1Qo updateEquipmentType1Qo) {
        String equipmentTypeCode = updateEquipmentType1Qo.getEquipmentTypeCode();
        String equipmentTypeName = updateEquipmentType1Qo.getEquipmentTypeName();
        //根据编码进行查询
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipmentTypeCode(equipmentTypeCode);
        List<EquipmentType> equipmentTypes = equipmentTypeDao.selectByEquipmentType(equipmentType);
        if (equipmentTypes.size() > 0) {
            EquipmentType equipmentType1 = equipmentTypes.get(0);
            equipmentType1.setEquipmentTypeName(equipmentTypeName);
            //更新
            equipmentTypeDao.updateEquipmentType(equipmentType1);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteEquipmentType1(DeleteEquipmentType1Qo deleteEquipmentType1Qo) {
        String equipmentTypeCode = deleteEquipmentType1Qo.getEquipmentTypeCode();
        //确认是否与模板信息有绑定
        MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
        monitorPropertyTemplate.setEquipmentType(equipmentTypeCode);
        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
        if (monitorPropertyTemplates.size() > 0) {
            return WebResponse.error(400, "有绑定关系,删除失败");
        }
        //确认是否是设备有关联
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setEquipmentType(equipmentTypeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoService.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            return WebResponse.error(400, "有绑定关系,删除失败");
        }
        //删除
        equipmentTypeDao.deleteEquipmentType(equipmentTypeCode);
        return WebResponse.success();
    }
}
