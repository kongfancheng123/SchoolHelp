package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class UpdateMonitorProperty1Qo {
    /**
     * 信号编码
     */
    private String equipmentPropertyCode;
    /**
     * 信号名称
     */
    private String equipmentPropertyName;
    /**
     * 信号类型
     * 1-YC、2-YX、3-YK、4-YT、5-EXPLAN
     */
    private Integer equipmentPropertyType;
}
