package com.serotonin.managementService.impl;

import com.serotonin.BaseService.HelpService;
import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.HelpManagementService;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/4.
 */
public class HelpManagementServiceImpl implements HelpManagementService {
    @Resource
    private HelpService helpService;

    @Override
    public WebResponse getHelps() {
        return null;
    }

    @Override
    public WebResponse addHelp(Help help) {
        return null;
    }

    @Override
    public WebResponse deleteHelp(Integer id) {
        return null;
    }

    @Override
    public WebResponse updateHelp(Help help) {
        return null;
    }
}
