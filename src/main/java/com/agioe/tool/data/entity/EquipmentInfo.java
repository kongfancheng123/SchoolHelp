package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 设备信息表
 */
@Getter
@Setter
public class EquipmentInfo {
    /**
     * ID
     */
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
    /**
     * 实时值
     * 监控信号类型为YC/YX时，实时值，可以为空
     */
    private String dataVal;
    /**
     * 上次报警信息
     * 对应监控信号最近一次的报警信息，格式：alarm_事件类型_事件码_事件值_事件时间，可以为空
     */
    private String alarmVal;
    /**
     * 上次控制信息
     * 对应监控信号（YK/YT）最近一次的控制信息，格式：control_事件类型_控制类型_事件值_控制结果_操作时间，可以为空
     */
    private String controlVal;
    /**
     * 表更新时间
     */
    private Date updateTime;
    /**
     * 参数更新时间
     */
    private Date paramsUpdate;
    /**
     * 数据更新时间
     */
    private Date dataUpdate;
    /**
     * 报警更新时间
     */
    private Date alarmUpdate;
    /**
     * 控制更新时间
     */
    private Date controllerUpdate;

}
