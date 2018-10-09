package com.agioe.tool.data.entity;

import lombok.Data;

@Data
public class Parent_node extends BaseEntity {
    /**
     * 上层节点编码
     */
    private String parent_node_code;
    /**
     * 上层节点名称
     */
    private String parent_node_name;
}
