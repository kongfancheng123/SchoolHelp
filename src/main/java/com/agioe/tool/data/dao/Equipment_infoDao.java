package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Equipment_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Equipment_infoDao {
    /**
     * 新增设备信息
     *
     * @param equipment_info
     * @return
     */
    Integer insertEquipment_info(@Param("equipment_info") Equipment_info equipment_info);

    /**
     * 删除设备信息
     *
     * @param id
     * @return
     */
    Integer deleteEquipment_info(Integer id);

    /**
     * 多条件查询设备信息
     *
     * @param equipment_info
     * @return
     */
    List<Equipment_info> selectByEquipment_info(@Param("equipment_info") Equipment_info equipment_info);

    /**
     * 更新设备信息
     *
     * @param equipment_info
     * @return
     */
    Integer updateEquipment_info(@Param("equipment_info") Equipment_info equipment_info);

    /**
     * 查找所有设备信息
     */
    List<Equipment_info> selectAll();

    /**
     * 根据id进行查找设备信息
     */
    Equipment_info selectByid(Integer id);
}
