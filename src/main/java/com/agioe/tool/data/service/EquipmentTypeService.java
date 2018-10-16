package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface EquipmentTypeService {
    /**
     * 新增设备类型
     *
     * @param equipmentType
     * @return
     */
    Integer insertEquipmentType(EquipmentType equipmentType);

    /**
     * 删除设备类型
     *
     * @param equipment_type_code
     * @return
     */
    Integer deleteEquipmentType(String equipment_type_code);

    /**
     * 多条件查询设备类型
     *
     * @param equipmentType
     * @return
     */
    List<EquipmentType> selectByEquipmentType(EquipmentType equipmentType);

    /**
     * 更新设备类型
     *
     * @param equipmentType
     * @return
     */
    Integer updateEquipmentType(EquipmentType equipmentType);

    /**
     * 查找所有设备类型
     */
    List<EquipmentType> selectAll();

    /**
     * 根据id进行查找设备类型
     */
    EquipmentType selectByid(Integer id);

    /**
     * 展示所有设备类型
     *
     * @return
     */
    WebResponse showAllEquipmentType();

    /**
     * 分页展示设备类型
     *
     * @param pageQo
     * @return
     */
    WebResponse showPageEquipmentType(PageQo pageQo);

    /**
     * 新增加设备类型
     *
     * @param addEquipmentTypeQo
     * @return
     */
    WebResponse addEquipmentType1(AddEquipmentTypeQo addEquipmentTypeQo);

    /**
     * 更新设备类型
     *
     * @param updateEquipmentType1Qo
     * @return
     */
    WebResponse updateEquipmentType1(UpdateEquipmentType1Qo updateEquipmentType1Qo);

    /**
     * 删除设备类型
     */
    WebResponse deleteEquipmentType1(DeleteEquipmentType1Qo deleteEquipmentType1Qo);

    /**
     * 导出设备类型表
     *
     * @param exportExcelEquipmentTypeQo
     * @return
     * @throws Exception
     */
    WebResponse exportExcelEquipmentType(ExportExcelEquipmentTypeQo exportExcelEquipmentTypeQo) throws Exception;

    /**
     * 导入设备类型表
     *
     * @param importExcelEquipmentTypeQo
     * @return
     * @throws Exception
     */
    WebResponse importExcelEquipmentType(ImportExcelEquipmentTypeQo importExcelEquipmentTypeQo) throws Exception;
}