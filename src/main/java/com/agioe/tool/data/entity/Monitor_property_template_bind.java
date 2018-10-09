package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 信号模板与信号关联关系
 */
@Data
public class Monitor_property_template_bind extends BaseEntity {
    /**
     * 设备类型
     */
    private String equipment_type;
    /**
     * 信号模板编码
     */
    private String equipment_property_template_code;
    /**
     * 信号编码
     */
    private String equipment_property_code;
}
