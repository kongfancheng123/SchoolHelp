package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/28.
 */
@Getter
@Setter
public class SchoolDistrict {
    /**
     * 校区id
     */
    private Integer id;
    /**
     * 校区名称
     */
    private String schoolDistrictName;
    /**
     * 学校id
     */
    private Integer schoolId;
}
