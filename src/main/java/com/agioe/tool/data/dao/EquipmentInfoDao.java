package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.CreateTableParam;
import com.agioe.tool.data.entity.EquipmentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentInfoDao {
    /**
     * 新增设备信息
     *
     * @param equipmentInfo
     * @return
     */
    Integer insertEquipmentInfo(@Param("equipmentInfo") EquipmentInfo equipmentInfo);

    /**
     * 删除设备信息
     *
     * @param id
     * @return
     */
    Integer deleteEquipmentInfo(Integer id);

    /**
     * 多条件查询设备信息
     *
     * @param equipmentInfo
     * @return
     */
    List<EquipmentInfo> selectByEquipmentInfo(@Param("equipmentInfo") EquipmentInfo equipmentInfo);

    /**
     * 更新设备信息
     *
     * @param equipmentInfo
     * @return
     */
    Integer updateEquipmentInfo(@Param("equipmentInfo") EquipmentInfo equipmentInfo);

    /**
     * 查找所有设备信息
     */
    List<EquipmentInfo> selectAll();

    /**
     * 根据id进行查找设备信息
     */
    EquipmentInfo selectByid(Integer id);

    /**
     * 建表
     */
    Integer createTable(@Param("createTableParam") CreateTableParam createTableParam);
}
