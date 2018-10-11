package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class EquipTypeVo1 {
    /**
     * 设备类型编码
     */
    private String equipmentTypeCode;
    /**
     * 设备类型名称
     */
    private String equipmentTypeName;
    /**
     * 监控模板集合
     */
    private List<TemplateVo> templateLists;
}
