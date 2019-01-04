package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/28.
 */
@Getter
@Setter
public class College {
    /**
     * 学院id
     */
    private Integer id;
    /**
     * 学院名称
     */
    private String collegeName;
    /**
     * 学校id
     */
    private Integer schoolId;
}
