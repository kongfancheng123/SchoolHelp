package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class UpdateEquipmentInfo1Qo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 设备编码
     */
    private String equipmentCode;
    /**
     * 关键字
     */
    private String keyword;
}
