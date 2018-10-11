package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.UpdateOrAddParamsConfigQo;
import com.agioe.tool.data.entity.ParamsConfig;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface ParamsConfigService {
    /**
     * 新增监控信息
     *
     * @param paramsConfig
     * @return
     */
    Integer insertParamsConfig(ParamsConfig paramsConfig);

    /**
     * 更新监控信息
     *
     * @param paramsConfig
     * @return
     */
    Integer updateParamsConfig(ParamsConfig paramsConfig);

    /**
     * 查找所有监控信息
     */
    List<ParamsConfig> selectAll();

    /**
     * 新增或保存配置信息
     *
     * @param updateOrAddParamsConfigQo
     * @return
     */
    WebResponse updateOrAddParamsConfig(UpdateOrAddParamsConfigQo updateOrAddParamsConfigQo);
}
