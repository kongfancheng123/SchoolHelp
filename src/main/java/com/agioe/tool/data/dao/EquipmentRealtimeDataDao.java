package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.EquipmentRealtimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRealtimeDataDao {
    /**
     * 新增实时数据
     *
     * @param equipmentRealtimeData
     * @return
     */
    Integer insertEquipmentRealtimeData(@Param("equipmentRealtimeData") EquipmentRealtimeData equipmentRealtimeData);

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
    List<EquipmentRealtimeData> selectByEquipmentRealtimeData(@Param("equipmentRealtimeData") EquipmentRealtimeData equipmentRealtimeData);

    /**
     * 更新实时数据
     *
     * @param equipmentRealtimeData
     * @return
     */
    Integer updateEquipmentRealtimeData(@Param("equipmentRealtimeData") EquipmentRealtimeData equipmentRealtimeData);

    /**
     * 查找所有实时数据
     */
    List<EquipmentRealtimeData> selectAll();

    /**
     * 根据id进行查找实时数据
     */
    EquipmentRealtimeData selectByid(Integer id);
}
