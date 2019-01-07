package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/7.
 */
@Getter
@Setter
public class UserPermission {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 权限id
     */
    private Integer permissionId;
}
