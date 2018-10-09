package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllMonitor_property_template_bindVo {
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
