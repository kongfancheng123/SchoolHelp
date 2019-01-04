package com.serotonin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Create by fchkong on 2018/12/28.
 */
@Getter
@Setter
public class City {
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
}
