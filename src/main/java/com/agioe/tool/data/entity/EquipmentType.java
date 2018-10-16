package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备类型
 */
@Getter
@Setter
public class EquipmentType extends BaseEntity {
    /**
     * 设备类型编码
     */
    private String equipmentTypeCode;
    /**
     * 设备类型名
     */
    private String equipmentTypeName;
    /**
     * 缺省1
     */
    private String default1;
    /**
     * 缺省2
     */
    private String default2;
    /**
     * 缺省3
     */
    private String default3;
    /**
     * 缺省4
     */
    private Object default4;
}
