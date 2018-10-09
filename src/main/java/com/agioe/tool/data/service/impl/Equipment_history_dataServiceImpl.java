package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.Equipment_history_dataDao;
import com.agioe.tool.data.entity.Equipment_history_data;
import com.agioe.tool.data.service.Equipment_history_dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Equipment_history_dataServiceImpl implements Equipment_history_dataService {
    @Autowired
    private Equipment_history_dataDao equipment_history_dataDao;

    @Override
    public Integer insertEquipment_history_data(Equipment_history_data equipment_history_data) {
        return equipment_history_dataDao.insertEquipment_history_data(equipment_history_data);
    }

    @Override
    public Integer deleteEquipment_history_data(Integer id) {
        return equipment_history_dataDao.deleteEquipment_history_data(id);
    }

    @Override
    public List<Equipment_history_data> selectByEquipment_history_data(Equipment_history_data equipment_history_data) {
        return equipment_history_dataDao.selectByEquipment_history_data(equipment_history_data);
    }

    @Override
    public Integer updateEquipment_history_data(Equipment_history_data equipment_history_data) {
        return equipment_history_dataDao.updateEquipment_history_data(equipment_history_data);
    }

    @Override
    public List<Equipment_history_data> selectAll() {
        return equipment_history_dataDao.selectAll();
    }

    @Override
    public Equipment_history_data selectByid(Integer id) {
        return equipment_history_dataDao.selectByid(id);
    }
}
