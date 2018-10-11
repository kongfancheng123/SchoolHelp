package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class DealEventHistoryQo {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 报警信息
     */
    private String alarmVal;
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 属性类型
     */
    private Integer equipmentPropertyType;

}
