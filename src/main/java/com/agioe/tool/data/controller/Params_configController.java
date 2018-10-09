package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.UpdateOrAddParams_configQo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Params_configService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Params_config")
public class Params_configController {
    @Autowired
    private Params_configService params_configService;

    /**
     * 新增或保存配置信息
     *
     * @param updateOrAddParams_configQo
     * @return
     */
    @RequestMapping(value = "/updateOrAddParams_config", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateOrAddParams_config(@RequestBody UpdateOrAddParams_configQo updateOrAddParams_configQo) {
        return params_configService.updateOrAddParams_config(updateOrAddParams_configQo);
    }

}
