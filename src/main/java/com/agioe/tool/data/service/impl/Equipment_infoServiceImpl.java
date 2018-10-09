package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.ShowAllEquipment_infoVo;
import com.agioe.tool.data.dao.Equipment_infoDao;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.Equipment_realtime_data;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Equipment_realtime_dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Equipment_infoServiceImpl implements Equipment_infoService {
    @Autowired
    private Equipment_infoDao equipment_infoDao;
    @Autowired
    private Equipment_realtime_dataService equipment_realtime_dataService;

    @Override
    public Integer insertEquipment_info(Equipment_info equipment_info) {
        return equipment_infoDao.insertEquipment_info(equipment_info);
    }

    @Override
    public Integer deleteEquipment_info(Integer id) {
        return equipment_infoDao.deleteEquipment_info(id);
    }

    @Override
    public List<Equipment_info> selectByEquipment_info(Equipment_info equipment_info) {
        return equipment_infoDao.selectByEquipment_info(equipment_info);
    }

    @Override
    public Integer updateEquipment_info(Equipment_info equipment_info) {
        return equipment_infoDao.updateEquipment_info(equipment_info);
    }

    @Override
    public List<Equipment_info> selectAll() {
        return equipment_infoDao.selectAll();
    }

    @Override
    public Equipment_info selectByid(Integer id) {
        return equipment_infoDao.selectByid(id);
    }

    @Override
    public WebResponse showAllEquipment_info() {
        List<ShowAllEquipment_infoVo> showAllEquipment_infoVos = new ArrayList<>();
        List<Equipment_info> equipment_infos = equipment_infoDao.selectAll();
        if (equipment_infos.size() > 0) {
            for (Equipment_info equipment_info : equipment_infos) {
                ShowAllEquipment_infoVo showAllEquipment_infoVo = new ShowAllEquipment_infoVo();
                showAllEquipment_infoVo.setEquipment_code(equipment_info.getEquipment_code());
                showAllEquipment_infoVo.setEquipment_name(equipment_info.getEquipment_name());
                showAllEquipment_infoVo.setEquipment_property_code(equipment_info.getEquipment_property_code());
                showAllEquipment_infoVo.setEquipment_property_template_code(equipment_info.getEquipment_property_template_code());
                showAllEquipment_infoVo.setEquipment_type(equipment_info.getEquipment_type());
                showAllEquipment_infoVo.setKeyword(equipment_info.getKeyword());
                showAllEquipment_infoVo.setParent_node_code(equipment_info.getParent_node_code());
                showAllEquipment_infoVos.add(showAllEquipment_infoVo);
            }
        }
        return WebResponse.success(showAllEquipment_infoVos);
    }

    @Override
    public WebResponse addEquipment_info1(AddEquipment_info1Qo addEquipment_info1Qo) {
        String equipment_code = addEquipment_info1Qo.getEquipment_code();
        String equipment_name = addEquipment_info1Qo.getEquipment_name();
        String equipment_property_code = addEquipment_info1Qo.getEquipment_property_code();
        String equipment_property_template_code = addEquipment_info1Qo.getEquipment_property_template_code();
        String equipment_type = addEquipment_info1Qo.getEquipment_type();
        String keyword = addEquipment_info1Qo.getKeyword();
        String parent_node_code = addEquipment_info1Qo.getParent_node_code();
//        //编码判重
//        Equipment_info equipment_info=new Equipment_info();
//        equipment_info.setEquipment_code(equipment_code);
//        List<Equipment_info> equipment_infos = equipment_infoDao.selectByEquipment_info(equipment_info);
//        if (equipment_infos.size()>0){
//            return WebResponse.error(400,"设备编码已存在");
//        }
//        //名字判重
//        Equipment_info equipment_info1=new Equipment_info();
//        equipment_info1.setEquipment_name(equipment_name);
//        List<Equipment_info> equipment_infos1 = equipment_infoDao.selectByEquipment_info(equipment_info1);
//        if(equipment_infos1.size()>0){
//            return WebResponse.error(400,"设备名已存在");
//        }
        //key值判断重复
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setKeyword(keyword);
        List<Equipment_info> equipment_infos = equipment_infoDao.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            return WebResponse.error(400, "key值已存在");
        }
        //添加
        Equipment_info equipment_info2 = new Equipment_info();
        equipment_info2.setEquipment_name(equipment_name);
        equipment_info2.setEquipment_code(equipment_code);
        equipment_info2.setEquipment_property_template_code(equipment_property_template_code);
        equipment_info2.setEquipment_property_code(equipment_property_code);
        equipment_info2.setEquipment_type(equipment_type);
        equipment_info2.setKeyword(keyword);
        equipment_info2.setParent_node_code(parent_node_code);
        //设置其余值为空
        equipment_info2.setAlarm_val("");
        equipment_info2.setControl_val("");
        equipment_info2.setData_val("");
        equipment_infoDao.insertEquipment_info(equipment_info2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateEquipment_info1(UpdateEquipment_info1Qo updateEquipment_info1Qo) {
        Integer id = updateEquipment_info1Qo.getId();
        String equipment_code = updateEquipment_info1Qo.getEquipment_code();
        String equipment_name = updateEquipment_info1Qo.getEquipment_name();
        String equipment_property_code = updateEquipment_info1Qo.getEquipment_property_code();
        String equipment_property_template_code = updateEquipment_info1Qo.getEquipment_property_template_code();
        String equipment_type = updateEquipment_info1Qo.getEquipment_type();
        String keyword = updateEquipment_info1Qo.getKeyword();
        String parent_node_code = updateEquipment_info1Qo.getParent_node_code();
        Equipment_info equipment_info = equipment_infoDao.selectByid(id);
        equipment_info.setParent_node_code(parent_node_code);
        equipment_info.setKeyword(keyword);
        equipment_info.setEquipment_type(equipment_type);
        equipment_info.setEquipment_property_code(equipment_property_code);
        equipment_info.setEquipment_property_template_code(equipment_property_template_code);
        equipment_info.setEquipment_code(equipment_code);
        equipment_info.setEquipment_name(equipment_name);
        equipment_infoDao.updateEquipment_info(equipment_info);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteEquipment_info1(DeleteEquipment_info1Qo deleteEquipment_info1Qo) {
        Integer id = deleteEquipment_info1Qo.getId();
        Equipment_info equipment_info = equipment_infoDao.selectByid(id);
        String equipment_code = equipment_info.getEquipment_code();
        //判断设备是否有实时值
        Equipment_realtime_data equipment_realtime_data = new Equipment_realtime_data();
        equipment_realtime_data.setEquipment_code(equipment_code);
        List<Equipment_realtime_data> equipment_realtime_data1 = equipment_realtime_dataService.selectByEquipment_realtime_data(equipment_realtime_data);
        if (equipment_realtime_data1.size() > 0) {
            return WebResponse.error(400, "设备存在实时值,删除失败");
        }
        //删除
        equipment_infoDao.deleteEquipment_info(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse showEquipment_infoByCondition(ShowEquipment_infoByConditionQo showEquipment_infoByConditionQo) {
        String equipment_property_code = showEquipment_infoByConditionQo.getEquipment_property_code();
        String equipment_property_template_code = showEquipment_infoByConditionQo.getEquipment_property_template_code();
        String equipment_type = showEquipment_infoByConditionQo.getEquipment_type();
        String parent_node_code = showEquipment_infoByConditionQo.getParent_node_code();
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setEquipment_property_template_code(equipment_property_template_code == "" ? null : equipment_property_template_code);
        equipment_info.setEquipment_property_code(equipment_property_code == "" ? null : equipment_property_code);
        equipment_info.setEquipment_type(equipment_type == "" ? null : equipment_type);
        equipment_info.setParent_node_code(parent_node_code == "" ? null : parent_node_code);
        List<Equipment_info> equipment_infos = equipment_infoDao.selectByEquipment_info(equipment_info);
        List<ShowAllEquipment_infoVo> showAllEquipment_infoVos = new ArrayList<>();
        if (equipment_infos.size() > 0) {
            for (Equipment_info equipment_info2 : equipment_infos) {
                ShowAllEquipment_infoVo showAllEquipment_infoVo = new ShowAllEquipment_infoVo();
                showAllEquipment_infoVo.setEquipment_code(equipment_info2.getEquipment_code());
                showAllEquipment_infoVo.setEquipment_name(equipment_info2.getEquipment_name());
                showAllEquipment_infoVo.setEquipment_property_code(equipment_info2.getEquipment_property_code());
                showAllEquipment_infoVo.setEquipment_property_template_code(equipment_info2.getEquipment_property_template_code());
                showAllEquipment_infoVo.setEquipment_type(equipment_info2.getEquipment_type());
                showAllEquipment_infoVo.setKeyword(equipment_info2.getKeyword());
                showAllEquipment_infoVo.setParent_node_code(equipment_info2.getParent_node_code());
                showAllEquipment_infoVos.add(showAllEquipment_infoVo);
            }
        }
        return WebResponse.success(showAllEquipment_infoVos);
    }

    @Override
    public WebResponse sendEquipment_realtime_data(SendEquipment_realtime_dataQo sendEquipment_realtime_dataQo) {
        String data_value = sendEquipment_realtime_dataQo.getData_value();
        String keyword = sendEquipment_realtime_dataQo.getKeyword();
        //对key值对应的设备表进行更新
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setKeyword(keyword);
        List<Equipment_info> equipment_infos = equipment_infoDao.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            Equipment_info equipment_info1 = equipment_infos.get(0);
            equipment_info1.setData_val(data_value);
            equipment_info1.setUpdate_time(new Date());
            equipment_infoDao.updateEquipment_info(equipment_info1);
        }
        //todo:发送实时数据
        return WebResponse.success();
    }


}
