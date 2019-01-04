package com.serotonin.managementQoAndVo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/3.
 */
@Getter
@Setter
public class GetSchoolVo {
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
    /**
     * 市名称
     */
    private String cityName;
}
