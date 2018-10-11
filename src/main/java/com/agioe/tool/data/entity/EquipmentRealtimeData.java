package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 设备实时数据
 */
@Data
public class EquipmentRealtimeData extends BaseEntity {
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
    /**
     * 关键字
     */
    private String keyword;
}
