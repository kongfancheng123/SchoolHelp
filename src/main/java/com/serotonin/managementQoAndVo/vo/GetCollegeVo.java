package com.serotonin.managementQoAndVo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/3.
 */
@Getter
@Setter
public class GetCollegeVo {
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
    /**
     * 学校名称
     */
    private String schoolName;
}
