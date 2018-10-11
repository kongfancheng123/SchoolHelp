package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.EquipmentHistoryData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentHistoryDataDao {
    /**
     * 新增历史数据
     *
     * @param equipmentHistoryData
     * @return
     */
    Integer insertEquipmentHistoryData(@Param("equipmentHistoryData") EquipmentHistoryData equipmentHistoryData);

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
    List<EquipmentHistoryData> selectByEquipmentHistoryData(@Param("equipmentHistoryData") EquipmentHistoryData equipmentHistoryData);

    /**
     * 更新历史数据
     *
     * @param equipmentHistoryData
     * @return
     */
    Integer updateEquipmentHistoryData(@Param("equipmentHistoryData") EquipmentHistoryData equipmentHistoryData);

    /**
     * 查找所有历史数据
     */
    List<EquipmentHistoryData> selectAll();

    /**
     * 根据id进行查找历史数据
     */
    EquipmentHistoryData selectByid(Integer id);
}
