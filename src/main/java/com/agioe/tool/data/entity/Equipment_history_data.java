package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备历史数据
 */
@Data
public class Equipment_history_data extends BaseEntity {
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
}
