package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Equipment_type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Equipment_typeDao {
    /**
     * 新增设备类型
     *
     * @param equipment_type
     * @return
     */
    Integer insertEquipment_type(@Param("equipment_type") Equipment_type equipment_type);

    /**
     * 删除设备类型
     *
     * @param id
     * @return
     */
    Integer deleteEquipment_type(Integer id);

    /**
     * 多条件查询设备类型
     *
     * @param equipment_type
     * @return
     */
    List<Equipment_type> selectByEquipment_type(@Param("equipment_type") Equipment_type equipment_type);

    /**
     * 更新设备类型
     *
     * @param equipment_type
     * @return
     */
    Integer updateEquipment_type(@Param("equipment_type") Equipment_type equipment_type);

    /**
     * 查找所有设备类型
     */
    List<Equipment_type> selectAll();

    /**
     * 根据id进行查找设备类型
     */
    Equipment_type selectByid(Integer id);
}
