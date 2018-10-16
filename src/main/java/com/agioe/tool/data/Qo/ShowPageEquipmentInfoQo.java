package com.agioe.tool.data.Qo;

import lombok.Data;

@Data
public class ShowPageEquipmentInfoQo {
    private String parentNodeCode;
    private Integer pageSize;
    private Integer pageNow;
}
