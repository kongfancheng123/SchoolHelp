package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class AddMonitor_property1Qo {
    /**
     * 信号编码
     */
    private String equipment_property_code;
    /**
     * 信号名称
     */
    private String equipment_property_name;
    /**
     * 信号类型
     * 1-YC、2-YX、3-YK、4-YT、5-EXPLAN
     */
    private Integer equipment_property_type;
}
