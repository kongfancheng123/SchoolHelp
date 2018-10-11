package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllMonitorPropertyTemplateVo {
    /**
     * 设备类型名称
     */
    private String equipmentTypeName;
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
