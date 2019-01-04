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
}
