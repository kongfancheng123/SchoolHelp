package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class GetDealEventHistoryValueQo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * keyword
     */
    private String keyword;
}
