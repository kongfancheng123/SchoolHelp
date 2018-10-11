package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.EquipmentRealtimeData;

import java.util.List;

public interface EquipmentRealtimeDataService {
    /**
     * 新增实时数据
     *
     * @param equipmentRealtimeData
     * @return
     */
    Integer insertEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData);

    /**
     * 删除实时数据
     *
     * @param id
     * @return
     */
    Integer deleteEquipmentRealtimeData(Integer id);

    /**
     * 多条件查询实时数据
     *
     * @param equipmentRealtimeData
     * @return
     */
    List<EquipmentRealtimeData> selectByEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData);

    /**
     * 更新实时数据
     *
     * @param equipmentRealtimeData
     * @return
     */
    Integer updateEquipmentRealtimeData(EquipmentRealtimeData equipmentRealtimeData);

    /**
     * 查找所有实时数据
     */
    List<EquipmentRealtimeData> selectAll();

    /**
     * 根据id进行查找实时数据
     */
    EquipmentRealtimeData selectByid(Integer id);


}
