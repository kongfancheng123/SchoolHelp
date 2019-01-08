package com.serotonin.managementQoAndVo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/8.
 */
@Setter
@Getter
public class GetUserPermissionVo {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 权限名
     */
    private String permissionName;
}
