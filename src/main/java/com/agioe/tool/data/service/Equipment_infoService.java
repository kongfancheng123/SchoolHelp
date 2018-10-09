package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Equipment_infoService {
    /**
     * 新增设备信息
     *
     * @param equipment_info
     * @return
     */
    Integer insertEquipment_info(Equipment_info equipment_info);

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
    List<Equipment_info> selectByEquipment_info(Equipment_info equipment_info);

    /**
     * 更新设备信息
     *
     * @param equipment_info
     * @return
     */
    Integer updateEquipment_info(Equipment_info equipment_info);

    /**
     * 查找所有设备信息
     */
    List<Equipment_info> selectAll();

    /**
     * 根据id进行查找设备信息
     */
    Equipment_info selectByid(Integer id);


    /**
     * 展示设备
     *
     * @return
     */
    WebResponse showAllEquipment_info();

    /**
     * 新增设备
     *
     * @param addEquipment_info1Qo
     * @return
     */
    WebResponse addEquipment_info1(AddEquipment_info1Qo addEquipment_info1Qo);

    /**
     * 更新设备
     *
     * @param updateEquipment_info1Qo
     * @return
     */
    WebResponse updateEquipment_info1(UpdateEquipment_info1Qo updateEquipment_info1Qo);

    /**
     * 删除设备
     */
    WebResponse deleteEquipment_info1(DeleteEquipment_info1Qo deleteEquipment_info1Qo);

    /**
     * 条件查询设备
     *
     * @param showEquipment_infoByConditionQo
     * @return
     */
    WebResponse showEquipment_infoByCondition(ShowEquipment_infoByConditionQo showEquipment_infoByConditionQo);

    /**
     * 发送实时数据
     *
     * @param sendEquipment_realtime_dataQo
     * @return
     */
    WebResponse sendEquipment_realtime_data(SendEquipment_realtime_dataQo sendEquipment_realtime_dataQo);


}
