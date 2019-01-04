package com.serotonin.managementQoAndVo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/3.
 */
@Setter
@Getter
public class GetSchoolDistrictVo {
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
    /**
     * 学校名称
     */
    private String schoolName;
}
