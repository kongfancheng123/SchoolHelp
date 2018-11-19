package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Create by fchkong on 2018/11/19.
 */
@Setter
@Getter
public class HisEvent {
    private Integer id;
    /**
     * 设备编码
     */
    private String devCode;
    /**
     * 设备类型
     */
    private Integer devType;
    /**
     * 事件类型 0:报警  1:故障
     */
    private Integer eventType;
    /**
     * 报警故障子类型
     */
    private Integer eventSubType;
    /**
     * 事件值
     */
    private Double eventData;
    /**
     * 1:确认  0:未确认
     */
    private Integer isConfirm;
    /**
     * 事件发生事件
     */
    private Date eventTime;
    /**
     * 事件描述
     */
    private String eventDetail;
    /**
     * 确认人员
     */
    private String confirmOperator;
    /**
     * 确认描述
     */
    private String confirmDetail;
    /**
     * 确认时间
     */
    private Date confirmTime;
    /**
     * key
     */
    private String lowMachineKey;
    /**
     * 是否同步到主机
     */
    private Integer isSync;
    /**
     * 事件更新时间
     */
    private Date updateTime;
}
