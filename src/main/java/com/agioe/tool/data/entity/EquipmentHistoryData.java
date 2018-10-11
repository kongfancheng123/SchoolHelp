package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备历史数据
 */
@Data
public class EquipmentHistoryData extends BaseEntity {
    /**
     * 数据编码
     */
    private String dataCode;
    /**
     * 数据名称
     */
    private String dataName;
    /**
     * 数据值
     */
    private String dataValue;
    /**
     * 设备编码
     */
    private String equipmentCode;
}
