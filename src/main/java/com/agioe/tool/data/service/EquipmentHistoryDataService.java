package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.EquipmentHistoryData;

import java.util.List;

public interface EquipmentHistoryDataService {
    /**
     * 新增历史数据
     *
     * @param equipmentHistoryData
     * @return
     */
    Integer insertEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData);

    /**
     * 删除历史数据
     *
     * @param id
     * @return
     */
    Integer deleteEquipmentHistoryData(Integer id);

    /**
     * 多条件查询历史数据
     *
     * @param equipmentHistoryData
     * @return
     */
    List<EquipmentHistoryData> selectByEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData);

    /**
     * 更新历史数据
     *
     * @param equipmentHistoryData
     * @return
     */
    Integer updateEquipmentHistoryData(EquipmentHistoryData equipmentHistoryData);

    /**
     * 查找所有历史数据
     */
    List<EquipmentHistoryData> selectAll();

    /**
     * 根据id进行查找历史数据
     */
    EquipmentHistoryData selectByid(Integer id);
}
