package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.ParamsConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParamsConfigDao {
    /**
     * 新增监控信息
     *
     * @param paramsConfig
     * @return
     */
    Integer insertParamsConfig(@Param("paramsConfig") ParamsConfig paramsConfig);

    /**
     * 更新监控信息
     *
     * @param paramsConfig
     * @return
     */
    Integer updateParamsConfig(@Param("paramsConfig") ParamsConfig paramsConfig);

    /**
     * 查找所有监控信息
     */
    List<ParamsConfig> selectAll();
}
