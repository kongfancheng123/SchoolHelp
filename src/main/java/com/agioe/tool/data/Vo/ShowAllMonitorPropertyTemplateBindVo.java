package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllMonitorPropertyTemplateBindVo {
    /**
     * 关联id
     */
    private Integer id;
    /**
     * 设备类型名称
     */
    private String equipmentTypeName;
    /**
     * 设备类型编码
     */
    private String equipmentType;
    /**
     * 信号模板名称
     */
    private String equipmentPropertyTemplateName;
    /**
     * 信号模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 信号编码
     */
    private String equipmentPropertyCode;
    /**
     * 属性名称
     */
    private String equipmentPropertyName;
    /**
     * 信号类型
     */
    private Integer equipmentPropertyType;

}
