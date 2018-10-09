package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllMonitor_property_templateVo {
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
