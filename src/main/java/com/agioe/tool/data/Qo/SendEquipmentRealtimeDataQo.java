package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class SendEquipmentRealtimeDataQo {

    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 设备类型编码
     */
    private String equipmentType;
    /**
     * 模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 属性和值
     */
    private String[][] propertyCodeAndValue;
}
