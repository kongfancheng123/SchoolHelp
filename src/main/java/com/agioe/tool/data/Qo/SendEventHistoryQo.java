package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class SendEventHistoryQo {
    /**
     * 事件编码
     */
    private String eventCode;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件值
     */
    private String eventValue;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
}
