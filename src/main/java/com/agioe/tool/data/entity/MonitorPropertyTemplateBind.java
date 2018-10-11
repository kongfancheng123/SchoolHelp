package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 信号模板与信号关联关系
 */
@Data
public class MonitorPropertyTemplateBind extends BaseEntity {
    /**
     * 设备类型
     */
    private String equipmentType;
    /**
     * 信号模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 信号编码
     */
    private String equipmentPropertyCode;
}
