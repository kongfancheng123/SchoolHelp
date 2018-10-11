package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.EquipmentType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentTypeDao {
    /**
     * 新增设备类型
     *
     * @param equipmentType
     * @return
     */
    Integer insertEquipmentType(@Param("equipmentType") EquipmentType equipmentType);

    /**
     * 删除设备类型
     *
     * @param equipmentTypeCode
     * @return
     */
    Integer deleteEquipmentType(String equipmentTypeCode);

    /**
     * 多条件查询设备类型
     *
     * @param equipmentType
     * @return
     */
    List<EquipmentType> selectByEquipmentType(@Param("equipmentType") EquipmentType equipmentType);

    /**
     * 更新设备类型
     *
     * @param equipmentType
     * @return
     */
    Integer updateEquipmentType(@Param("equipmentType") EquipmentType equipmentType);

    /**
     * 查找所有设备类型
     */
    List<EquipmentType> selectAll();

    /**
     * 根据id进行查找设备类型
     */
    EquipmentType selectByid(Integer id);
}
