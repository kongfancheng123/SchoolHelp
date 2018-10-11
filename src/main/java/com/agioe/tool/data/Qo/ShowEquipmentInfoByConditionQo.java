package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class ShowEquipmentInfoByConditionQo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 设备类型
     */
    private String equipmentType;
    /**
     * 设备监控模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 设备信号编码
     */
    private String equipmentPropertyCode;
}
