package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Create by fchkong on 2018/11/15.
 */
@Getter
@Setter
public class HostInfo {
    /**
     * 主机编码
     */
    private String hostCode;
    /**
     * 主机名称
     */
    private String hostName;
    private Integer hostType;
    /**
     * 主机地址
     */
    private String hostAddr;
    private Integer dataProcessMode;
    private Integer isEnable;
    /**
     * 是否建立连接
     */
    private Integer isConn;
    /**
     * 更新时间
     */
    private Date updateTime;
    private Date paramsUpdateTime;
    /**
     * 主机端口号
     */
    private String default1;
    /**
     * 主机串口号
     */
    private String default2;
    private String default3;
}
