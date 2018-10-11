package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.EquipmentHistoryDataDao;
import com.agioe.tool.data.entity.EquipmentHistoryData;
import com.agioe.tool.data.service.EquipmentHistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentHistoryDataServiceImpl implements EquipmentHistoryDataService {
    @Autowired
    private EquipmentHistoryDataDao equipmentHistoryDataDao;

    @Override
    public Integer insertEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData) {
        return equipmentHistoryDataDao.insertEquipmentHistoryData(equipmentHistoryData);
    }

    @Override
    public Integer deleteEquipmentHistoryData(Integer id) {
        return equipmentHistoryDataDao.deleteEquipmentHistoryData(id);
    }

    @Override
    public List<EquipmentHistoryData> selectByEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData) {
        return equipmentHistoryDataDao.selectByEquipmentHistoryData(equipmentHistoryData);
    }

    @Override
    public Integer updateEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData) {
        return equipmentHistoryDataDao.updateEquipmentHistoryData(equipmentHistoryData);
    }

    @Override
    public List<EquipmentHistoryData> selectAll() {
        return equipmentHistoryDataDao.selectAll();
    }

    @Override
    public EquipmentHistoryData selectByid(Integer id) {
        return equipmentHistoryDataDao.selectByid(id);
    }
}
