package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
