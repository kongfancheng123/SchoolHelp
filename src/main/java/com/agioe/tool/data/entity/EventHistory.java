package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 历史事件
 */
@Getter
@Setter
public class EventHistory extends BaseEntity {
    /**
     * 事件编码
     */
    private String eventCode;
    /**
     * 事件类型
     */
    private Integer eventType;
    /**
     * 事件值
     */
    private String eventValue;
    /**
     * 事件状态
     * 0-未解除,1-已解除
     */
    private Integer eventState;
    /**
     * 关键字
     */
    private String keyword;
}
