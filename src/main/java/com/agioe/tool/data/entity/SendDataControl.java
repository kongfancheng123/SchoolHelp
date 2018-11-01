package com.agioe.tool.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/10/31.
 */
@Getter
@Setter
public class SendDataControl {
    private Integer id;
    private String parentNodeCode;
    private String equipmentTypeCode;
    private Integer controlVal;
}
