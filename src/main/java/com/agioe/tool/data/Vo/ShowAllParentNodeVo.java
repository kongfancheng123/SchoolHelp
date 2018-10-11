package com.agioe.tool.data.Vo;

import lombok.Data;

@Data
public class ShowAllParentNodeVo {
    /**
     * 上层节点编码
     */
    private String parentNodeCode;
    /**
     * 上层节点名称
     */
    private String parentNodeName;
}
