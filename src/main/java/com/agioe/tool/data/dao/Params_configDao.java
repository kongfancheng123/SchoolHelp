package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Params_config;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Params_configDao {
    /**
     * 新增监控信息
     *
     * @param params_config
     * @return
     */
    Integer insertParams_config(@Param("params_config") Params_config params_config);

    /**
     * 更新监控信息
     *
     * @param params_config
     * @return
     */
    Integer updateParams_config(@Param("params_config") Params_config params_config);

    /**
     * 查找所有监控信息
     */
    List<Params_config> selectAll();
}
