package com.agioe.tool.data.entity;

import lombok.Data;

@Data
public class ParentNode extends BaseEntity {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 上层节点名称
     */
    private String parentNodeName;
}
