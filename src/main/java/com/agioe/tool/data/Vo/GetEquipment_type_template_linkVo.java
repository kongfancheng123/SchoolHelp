package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class GetEquipment_type_template_linkVo {
    /**
     * 设备类型集合
     */
    private List<Equip_typeVo1> equip_typeVo1s;
}
