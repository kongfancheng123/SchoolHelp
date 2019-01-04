package com.serotonin.managementService.impl;

import com.serotonin.BaseService.HelpService;
import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.HelpManagementService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/4.
 */
public class HelpManagementServiceImpl implements HelpManagementService {
    @Resource
    private HelpService helpService;

    @Override
    public WebResponse getHelps() {
        List<Help> helps = helpService.selectAll();
        return WebResponse.success(helps);
    }

    @Override
    public WebResponse addHelp(Help help) {
        //todo:判断重复
        Integer integer = helpService.insertHelp(help);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteHelp(Integer id) {
        //todo:判断绑定关系
        helpService.deleteHelp(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateHelp(Help help) {
        helpService.updateHelp(help);
        return WebResponse.success();
    }
}
