package com.agioe.tool.data.Vo;

import lombok.Data;

import java.util.List;

@Data
public class ShowPageEquipmentInfoVo {
    private List<ShowAllEquipmentInfoVo> showAllEquipmentInfoVos;
    private Integer countNums;
}
