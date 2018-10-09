package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class UpdateParent_node1Qo {
    private Integer id;
    /**
     * 上层节点编码
     */
    private String parent_node_code;
    /**
     * 上层节点名称
     */
    private String parent_node_name;
}
