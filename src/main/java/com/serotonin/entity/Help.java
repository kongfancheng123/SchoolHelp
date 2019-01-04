package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/28.
 */
@Getter
@Setter
public class Help {
    /**
     * 帮帮忙id
     */
    private Integer id;
    /**
     * 发布人id
     */
    private Integer publishId;
    /**
     * 接收人id
     */
    private Integer accepterId;
    /**
     * 需求描述
     */
    private String requirementDesc;
}
