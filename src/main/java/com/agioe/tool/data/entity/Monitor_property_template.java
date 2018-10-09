package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 监控信号模板
 */
@Data
public class Monitor_property_template extends BaseEntity {
    /**
     * 设备类型编码
     */
    private String equipment_type;
    /**
     * 监控信号模板编码
     */
    private String equipment_property_template_code;
    /**
     * 监控信号模板名称
     */
    private String equipment_property_template_name;
    /**
     * 监控信号模板描述
     */
    private String equipment_property_template_description;
}
