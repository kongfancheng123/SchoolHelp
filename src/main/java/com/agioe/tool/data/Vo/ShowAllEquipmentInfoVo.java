package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllEquipmentInfoVo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 上层节点名称
     */
    private String parentNodeName;
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
     * 设备类型名称
     */
    private String equipmentTypeName;
    /**
     * 信号模板编码
     */
    private String equipmentPropertyTemplateCode;
    /**
     * 信号模板编码
     */
    private String equipmentPropertyTemplateName;
    /**
     * 信号编码
     */
    private String equipmentPropertyCode;
    /**
     * 信号名称
     */
    private String equipmentPropertyName;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 信号类型
     */
    private Integer equipmentPropertyType;
    /**
     * 实时值
     * 监控信号类型为YC/YX时，实时值，可以为空
     */
    private String dataVal;
    /**
     * 数据更新时间
     */
    private String dataUpdate;
    /**
     * 上次报警信息
     * 对应监控信号最近一次的报警信息，格式：alarm_事件类型_事件码_事件值_事件时间，可以为空
     */
    private String alarmVal;
    /**
     * 报警更新时间
     */
    private String alarmUpdate;
    /**
     * 上次控制信息
     * 对应监控信号（YK/YT）最近一次的控制信息，格式：control_事件类型_控制类型_事件值_控制结果_操作时间，可以为空
     */
    private String controlVal;
    /**
     * 控制更新时间
     */
    private String controllerUpdate;
}
