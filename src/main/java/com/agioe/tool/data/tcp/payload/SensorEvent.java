package com.agioe.tool.data.tcp.payload;

import lombok.Data;

/**
 * @author yshen
 * @since 2018/10/10
 */
@Data
public class SensorEvent {
    /**
     * 采集点号
     */
    private String key;

    /**
     * 事件码
     */
    private Short eventCode;

    /**
     * 类型
     * 0：报警，1：故障，2：故障解除，3：其它
     */
    private Byte type;
    /**
     * 事件值类型
     * 0-YC点，1-YX点，2-YC浮点型数组（如dts有报警值和位置），3-YC整型数组（局放连续性数据），4-字符串类型
     */
    private Byte eventDataType;
    /**
     * 事件值
     */
    private String eventDataValue;

    /**
     * 事件发生时间
     * 从1970年1月1日 00:00:00开始经过的秒数
     */
    private Long eventTime;

    /**
     * 所属组织Code
     */
    private String orgCode;
}
