package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.Equipment_realtime_data;

import java.util.List;

public interface Equipment_realtime_dataService {
    /**
     * 新增实时数据
     *
     * @param equipment_realtime_data
     * @return
     */
    Integer insertEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data);

    /**
     * 删除实时数据
     *
     * @param id
     * @return
     */
    Integer deleteEquipment_realtime_data(Integer id);

    /**
     * 多条件查询实时数据
     *
     * @param equipment_realtime_data
     * @return
     */
    List<Equipment_realtime_data> selectByEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data);

    /**
     * 更新实时数据
     *
     * @param equipment_realtime_data
     * @return
     */
    Integer updateEquipment_realtime_data(Equipment_realtime_data equipment_realtime_data);

    /**
     * 查找所有实时数据
     */
    List<Equipment_realtime_data> selectAll();

    /**
     * 根据id进行查找实时数据
     */
    Equipment_realtime_data selectByid(Integer id);


}
