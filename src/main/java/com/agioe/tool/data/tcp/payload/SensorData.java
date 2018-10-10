package com.agioe.tool.data.tcp.payload;

import lombok.Data;

/**
 * @author yshen
 * @since 2018/10/10
 */
@Data
public class SensorData {

    /**
     * 采集点号
     */
    private String key;
    /**
     * 采集值
     */
    private String val;
    /**
     * 采集时间
     */
    private Long time;

    /**
     * 类型
     */
    private byte type;
    /**
     * 组织编码
     */
    private String org;
}
