package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备实时数据
 */
@Data
public class Equipment_realtime_data extends BaseEntity {
    /**
     * 数据编码
     */
    private String data_code;
    /**
     * 数据名称
     */
    private String data_name;
    /**
     * 数据值
     */
    private String data_value;
    /**
     * 设备编码
     */
    private String equipment_code;
    /**
     * 关键字
     */
    private String keyword;
}
