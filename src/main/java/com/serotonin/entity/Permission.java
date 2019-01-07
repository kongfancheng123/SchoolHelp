package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/7.
 */
@Getter
@Setter
public class Permission {
    /**
     * 权限id
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String permissionName;
}
