package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class SendEquipmentRealtimeDataQo {

    /**
     * 数据值
     */
    private String dataValue;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
}
