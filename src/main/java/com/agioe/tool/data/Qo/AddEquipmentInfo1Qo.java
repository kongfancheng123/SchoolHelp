package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class AddEquipmentInfo1Qo {
    /**
     * 设备编码前缀
     */
    private String equipmentCodeBefore;
    /**
     * 设备编码起始值
     */
    private Integer equipmentCodeStart;
    /**
     * 设备名称前缀
     */
    private String equipmentNameBefore;
    /**
     * 设备名称起始值
     */
    private Integer equipmentNameStart;
    /**
     * 设备名称后缀
     */
    private String equipmentNameAfter;
    /**
     * 设备类型
     */
    private String equipmentType;
    /**
     * 信号模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 设备数量
     */
    private Integer equipmentNumber;
}
