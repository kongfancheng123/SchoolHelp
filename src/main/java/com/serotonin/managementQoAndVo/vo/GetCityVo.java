package com.serotonin.managementQoAndVo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2019/1/3.
 */
@Getter
@Setter
public class GetCityVo {
    /**
     * 市id
     */
    private Integer id;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 省份id
     */
    private Integer provinceId;
    /**
     * 省份名称
     */
    private String provinceName;
}
