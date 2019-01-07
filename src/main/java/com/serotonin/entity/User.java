package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/27.
 */
@Getter
@Setter
public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码 加解密业务
     */
    private String password;
    /**
     * 用户状态  0为不启用,1为启用
     */
    private Integer userState;
    /**
     * 用户账户金额
     */
    private Double accountBalance;
}
