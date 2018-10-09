package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备监控信息
 */
@Data
public class Monitor_property extends BaseEntity {
    /**
     * 信号编码
     */
    private String equipment_property_code;
    /**
     * 信号名称
     */
    private String equipment_property_name;
    /**
     * 信号类型
     * 1-YC、2-YX、3-YK、4-YT、5-EXPLAN
     */
    private Integer equipment_property_type;
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
