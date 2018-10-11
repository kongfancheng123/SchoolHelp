package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class UpdateEquipmentInfo1Qo {
    private Integer id;
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 设备编码
     */
    private String equipmentCode;
    /**
     * 设备名称
     */
    private String equipmentName;
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
    /**
     * 关键字
     */
    private String keyword;
}
