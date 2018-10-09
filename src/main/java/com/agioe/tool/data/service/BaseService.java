package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.TestInfo;

import java.util.List;

/**
 * @author yshen
 * @since 2018/9/30
 */
public interface BaseService {


    List<TestInfo> selectAll();
}
