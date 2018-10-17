package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class UpdateEquipmentInfo1Qo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 新关键字
     */
    private String keyword;
    /**
     * 原关键字
     */
    private String keywordOld;
}
