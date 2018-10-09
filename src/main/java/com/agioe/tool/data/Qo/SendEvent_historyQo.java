package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class SendEvent_historyQo {
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
}
