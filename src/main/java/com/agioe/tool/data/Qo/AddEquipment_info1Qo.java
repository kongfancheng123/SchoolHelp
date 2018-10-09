package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class AddEquipment_info1Qo {
    /**
     * 上层节点编码
     */
    private String parent_node_code;
    /**
     * 设备编码
     */
    private String equipment_code;
    /**
     * 设备名称
     */
    private String equipment_name;
    /**
     * 设备类型
     */
    private String equipment_type;
    /**
     * 信号模板编码
     */
    private String equipment_property_template_code;
    /**
     * 信号编码
     */
    private String equipment_property_code;
    /**
     * 关键字
     */
    private String keyword;
}
