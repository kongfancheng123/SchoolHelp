package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 监控信号模板
 */
@Data
public class MonitorPropertyTemplate extends BaseEntity {
    /**
     * 设备类型编码
     */
    private String equipmentType;
    /**
     * 监控信号模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 监控信号模板名称
     */
    private String equipmentPropertyTemplateName;
    /**
     * 监控信号模板描述
     */
    private String equipmentPropertyTemplateDescription;
}
