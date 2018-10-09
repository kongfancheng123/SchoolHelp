package com.agioe.tool.data.entity;

import lombok.Data;

/**
 * 历史事件
 */
@Data
public class Event_history extends BaseEntity {
    /**
     * 事件编码
     */
    private String event_code;
    /**
     * 事件类型
     */
    private Integer event_type;
    /**
     * 事件值
     */
    private String event_value;
    /**
     * 事件状态
     * 0-未解除,1-已解除
     */
    private Integer event_state;
    /**
     * 关键字
     */
    private String keyword;
}
