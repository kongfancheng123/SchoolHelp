package com.agioe.tool.data.entity;

import lombok.Data;

import java.util.Date;

/**
 * 参数配置
 */
@Data
public class ParamsConfig {
    /**
     * id
     */
    private Integer id;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private String port;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据使能,0和1
     */
    private Integer dataEnable;
    /**
     * 报警使能,0和1
     */
    private Integer alarmEnable;
    /**
     * 上送周期
     */
    private Integer feedCycle;
    /**
     * 日志等级,1  2  3
     */
    private Integer logLevel;
    /**
     * 版本信息
     */
    private String version;
    /**
     * 创建时间
     */
    private Date createDate;
}
