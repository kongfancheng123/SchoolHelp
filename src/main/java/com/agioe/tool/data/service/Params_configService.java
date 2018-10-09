package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.UpdateOrAddParams_configQo;
import com.agioe.tool.data.entity.Params_config;
import com.agioe.tool.data.entity.WebResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Params_configService {
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

    /**
     * 新增或保存配置信息
     *
     * @param updateOrAddParams_configQo
     * @return
     */
    WebResponse updateOrAddParams_config(UpdateOrAddParams_configQo updateOrAddParams_configQo);
}
