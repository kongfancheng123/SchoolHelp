package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class ShowEquipment_infoByConditionQo {
    /**
     * 上层节点编码
     */
    private String parent_node_code;
    /**
     * 设备类型
     */
    private String equipment_type;
    /**
     * 设备监控模板编码
     */
    private String equipment_property_template_code;
    /**
     * 设备信号编码
     */
    private String equipment_property_code;
}
