package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class Equip_typeVo1 {
    /**
     * 设备类型编码
     */
    private String equipment_type_code;
    /**
     * 设备类型名称
     */
    private String equipment_type_name;
    /**
     * 监控模板集合
     */
    private List<TemplateVo> templateLists;
}
