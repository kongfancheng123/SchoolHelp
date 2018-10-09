package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.Equipment_realtime_dataDao;
import com.agioe.tool.data.entity.Equipment_realtime_data;
import com.agioe.tool.data.service.Equipment_history_dataService;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Equipment_realtime_dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Equipment_realtime_dataServiceImpl implements Equipment_realtime_dataService {
    @Autowired
    private Equipment_realtime_dataDao equipment_realtime_dataDao;
    @Autowired
    private Equipment_history_dataService equipment_history_dataService;
    @Autowired
    private Equipment_infoService equipment_infoService;

    @Override
    public Integer insertEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data) {
        return equipment_realtime_dataDao.insertEquipment_realtime_data(equipment_realtime_data);
    }

    @Override
    public Integer deleteEquipment_realtime_data(Integer id) {
        return equipment_realtime_dataDao.deleteEquipment_realtime_data(id);
    }

    @Override
    public List<Equipment_realtime_data> selectByEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data) {
        return equipment_realtime_dataDao.selectByEquipment_realtime_data(equipment_realtime_data);
    }

    @Override
    public Integer updateEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data) {
        return equipment_realtime_dataDao.updateEquipment_realtime_data(equipment_realtime_data);
    }

    @Override
    public List<Equipment_realtime_data> selectAll() {
        return equipment_realtime_dataDao.selectAll();
    }

    @Override
    public Equipment_realtime_data selectByid(Integer id) {
        return equipment_realtime_dataDao.selectByid(id);
    }


}
