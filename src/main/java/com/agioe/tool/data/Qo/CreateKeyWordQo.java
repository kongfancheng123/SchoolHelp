package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class CreateKeyWordQo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 属性类型
     */
    private Integer equipmentPropertyType;
    /**
     * keyword起始值
     */
    private Integer keyWordStart;
}
