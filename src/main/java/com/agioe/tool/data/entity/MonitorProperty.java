package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备监控信息
 */
@Data
public class MonitorProperty extends BaseEntity {
    /**
     * 信号编码
     */
    private String equipmentPropertyCode;
    /**
     * 信号名称
     */
    private String equipmentPropertyName;
    /**
     * 信号类型
     * 1-YC、2-YX、3-YK、4-YT、5-EXPLAN
     */
    private Integer equipmentPropertyType;
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
