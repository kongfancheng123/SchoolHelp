package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/28.
 */
@Setter
@Getter
public class School {
    /**
     * 学校id
     */
    private Integer Id;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 市id
     */
    private Integer cityId;
}
