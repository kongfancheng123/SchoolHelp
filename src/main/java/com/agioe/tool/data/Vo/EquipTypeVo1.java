package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class EquipTypeVo1 {
    /**
     * 设备类型编码
     */
    private String code;
    /**
     * 设备类型名称
     */
    private String name;
    /**
     * 监控模板集合
     */
    private List<TemplateVo> children;
}
