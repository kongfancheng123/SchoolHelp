package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class GetEquipmentTypeTemplateLinkVo {
    /**
     * 设备类型集合
     */
    private List<EquipTypeVo1> equipTypeVo1s;
}
