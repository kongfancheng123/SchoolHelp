package com.agioe.tool.data.tcp.payload;

import lombok.Data;
import lombok.ToString;

/**
 * @author yshen
 * @since 2018/10/10
 */
@Data
@ToString
public class ControlParameter {
    /**
     * 控制点编码
     */
    private String key;
    /**
     * 控制命令类型
     */
    private Byte cmdType;
    /**
     * 控制方式
     */
    private Byte controlType;
    /**
     * 控制方式值
     */
    private Short controlTypeVal;
    /**
     * 控制动作值
     */
    private String controlActionVal;
    /**
     * 反馈信息（来自采集服务）
     */
    private Byte feedback;
}
