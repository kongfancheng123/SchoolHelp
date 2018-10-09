package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddEquipment_typeQo;
import com.agioe.tool.data.Qo.DeleteEquipment_type1Qo;
import com.agioe.tool.data.Qo.UpdateEquipment_type1Qo;
import com.agioe.tool.data.entity.Equipment_type;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Equipment_typeService {
    /**
     * 新增设备类型
     *
     * @param equipment_type
     * @return
     */
    Integer insertEquipment_type(Equipment_type equipment_type);

    /**
     * 删除设备类型
     *
     * @param equipment_type_code
     * @return
     */
    Integer deleteEquipment_type(String equipment_type_code);

    /**
     * 多条件查询设备类型
     *
     * @param equipment_type
     * @return
     */
    List<Equipment_type> selectByEquipment_type(Equipment_type equipment_type);

    /**
     * 更新设备类型
     *
     * @param equipment_type
     * @return
     */
    Integer updateEquipment_type(Equipment_type equipment_type);

    /**
     * 查找所有设备类型
     */
    List<Equipment_type> selectAll();

    /**
     * 根据id进行查找设备类型
     */
    Equipment_type selectByid(Integer id);

    /**
     * 展示所有设备类型
     *
     * @return
     */
    WebResponse showAllEquipment_type();

    /**
     * 新增加设备类型
     *
     * @param addEquipment_typeQo
     * @return
     */
    WebResponse addEquipment_type1(AddEquipment_typeQo addEquipment_typeQo);

    /**
     * 更新设备类型
     *
     * @param updateEquipment_type1Qo
     * @return
     */
    WebResponse updateEquipment_type1(UpdateEquipment_type1Qo updateEquipment_type1Qo);

    /**
     * 删除设备类型
     */
    WebResponse deleteEquipment_type1(DeleteEquipment_type1Qo deleteEquipment_type1Qo);
}
