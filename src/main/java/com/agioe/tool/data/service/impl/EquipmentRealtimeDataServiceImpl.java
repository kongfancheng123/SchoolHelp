package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.EquipmentRealtimeDataDao;
import com.agioe.tool.data.entity.EquipmentRealtimeData;
import com.agioe.tool.data.service.EquipmentHistoryDataService;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.EquipmentRealtimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentRealtimeDataServiceImpl implements EquipmentRealtimeDataService {
    @Autowired
    private EquipmentRealtimeDataDao equipmentRealtimeDataDao;
    @Autowired
    private EquipmentHistoryDataService equipmentHistoryDataService;
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Override
    public Integer insertEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData) {
        return equipmentRealtimeDataDao.insertEquipmentRealtimeData(equipmentRealtimeData);
    }

    @Override
    public Integer deleteEquipmentRealtimeData(Integer id) {
        return equipmentRealtimeDataDao.deleteEquipmentRealtimeData(id);
    }

    @Override
    public List<EquipmentRealtimeData> selectByEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData) {
        return equipmentRealtimeDataDao.selectByEquipmentRealtimeData(equipmentRealtimeData);
    }

    @Override
    public Integer updateEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData) {
        return equipmentRealtimeDataDao.updateEquipmentRealtimeData(equipmentRealtimeData);
    }

    @Override
    public List<EquipmentRealtimeData> selectAll() {
        return equipmentRealtimeDataDao.selectAll();
    }

    @Override
    public EquipmentRealtimeData selectByid(Integer id) {
        return equipmentRealtimeDataDao.selectByid(id);
    }


}
