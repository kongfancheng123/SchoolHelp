package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.UpdateOrAddParamsConfigQo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.ParamsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ParamsConfig")
public class ParamsConfigController {
    @Autowired
    private ParamsConfigService paramsConfigService;

    /**
     * 新增或保存配置信息
     *
     * @param updateOrAddParamsConfigQo
     * @return
     */
    @RequestMapping(value = "/updateOrAddParamsConfig", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateOrAddParamsConfig(@RequestBody UpdateOrAddParamsConfigQo updateOrAddParamsConfigQo) {
        return paramsConfigService.updateOrAddParamsConfig(updateOrAddParamsConfigQo);
    }

}
