package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.Equipment_history_data;

import java.util.List;

public interface Equipment_history_dataService {
    /**
     * 新增历史数据
     *
     * @param equipment_history_data
     * @return
     */
    Integer insertEquipment_history_data(Equipment_history_data equipment_history_data);

    /**
     * 删除历史数据
     *
     * @param id
     * @return
     */
    Integer deleteEquipment_history_data(Integer id);

    /**
     * 多条件查询历史数据
     *
     * @param equipment_history_data
     * @return
     */
    List<Equipment_history_data> selectByEquipment_history_data(Equipment_history_data equipment_history_data);

    /**
     * 更新历史数据
     *
     * @param equipment_history_data
     * @return
     */
    Integer updateEquipment_history_data(Equipment_history_data equipment_history_data);

    /**
     * 查找所有历史数据
     */
    List<Equipment_history_data> selectAll();

    /**
     * 根据id进行查找历史数据
     */
    Equipment_history_data selectByid(Integer id);
}
