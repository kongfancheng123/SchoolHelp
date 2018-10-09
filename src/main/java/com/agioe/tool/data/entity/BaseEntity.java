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
    private Date create_date;
    /**
     * 创建人
     */
    private Integer create_uid;
    /**
     * 修改日期
     */
    private Date write_date;
    /**
     * 修改人
     */
    private Integer write_uid;
}
