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
    /**
     * 帮忙金额
     */
    private Double helpMoney;
    /**
     * 发布人qq
     */
    private String qq;
    /**
     * 发布人电话
     */
    private String phoneNumber;
    /**
     * 发布人微信
     */
    private String weChat;
    /**
     * 发布状态
     * 0为待审核,1为已发布,2为已接受,3为发布人取消
     */
    private Integer publishState;
}
