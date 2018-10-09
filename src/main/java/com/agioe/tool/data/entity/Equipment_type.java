package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备类型
 */
@Data
public class Equipment_type extends BaseEntity {
    /**
     * 设备类型编码
     */
    private String equipment_type_code;
    /**
     * 设备类型名
     */
    private String equipment_type_name;
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
