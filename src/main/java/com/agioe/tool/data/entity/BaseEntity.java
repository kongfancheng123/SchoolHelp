package com.agioe.tool.data.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yshen
 * @since 2018/9/30
 */
@Data
public abstract class BaseEntity {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建人
     */
    private Integer createUid;
    /**
     * 修改日期
     */
    private Date writeDate;
    /**
     * 修改人
     */
    private Integer writeUid;
}
