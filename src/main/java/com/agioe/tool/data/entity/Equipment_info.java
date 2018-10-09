package com.agioe.tool.data.entity;

import lombok.Data;

import java.util.Date;

/**
 * 设备信息表
 */
@Data
public class Equipment_info {
    /**
     * ID
     */
    private Integer id;
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
    /**
     * 实时值
     * 监控信号类型为YC/YX时，实时值，可以为空
     */
    private String data_val;
    /**
     * 上次报警信息
     * 对应监控信号最近一次的报警信息，格式：alarm_事件类型_事件码_事件值_事件时间，可以为空
     */
    private String alarm_val;
    /**
     * 上次控制信息
     * 对应监控信号（YK/YT）最近一次的控制信息，格式：control_事件类型_控制类型_事件值_控制结果_操作时间，可以为空
     */
    private String control_val;
    /**
     * 表更新时间
     */
    private Date update_time;
    /**
     * 参数更新时间
     */
    private Date params_update;

}
