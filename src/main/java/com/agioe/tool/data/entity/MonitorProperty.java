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
     * 0-YC、1-YX、2-YK、3-YT、4-EXPLAN
     */
    /**
     * 事件值类型
     * 0-YC点，1-YX点，2-YC浮点型数组（如dts有报警值和位置），3-YC整型数组（局放连续性数据），4-字符串类型
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
