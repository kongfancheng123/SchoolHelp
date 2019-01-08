package com.serotonin.applicationService.impl;

import com.serotonin.BaseService.HelpService;
import com.serotonin.applicationService.HelpApplicationService;
import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2019/1/8.
 */
public class HelpApplicationServiceImpl implements HelpApplicationService {
    @Resource
    private HelpService helpService;

    @Override
    public WebResponse publishHelp(Help help) {
        //设置为待审核状态
        help.setPublishState(0);
        Integer integer = helpService.insertHelp(help);
        return WebResponse.success();
    }

    @Override
    public WebResponse cancelPublishHelp(Integer id) {
        Help help=new Help();
        help.setId(id);
        List<Help> helps = helpService.selectByHelp(help);
        if(helps.size()>0){
            Help help1 = helps.get(0);
            //改变帮助状态为发布人取消发布
            help1.setPublishState(3);
            helpService.updateHelp(help1);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse receiveHelp(Integer id) {
        Help help=new Help();
        help.setId(id);
        List<Help> helps = helpService.selectByHelp(help);
        if(helps.size()>0){
            Help help1 = helps.get(0);
            //改变帮助状态为接受状态
            help1.setPublishState(2);
            helpService.updateHelp(help1);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse finishHelp(Integer id) {
        Help help=new Help();
        help.setId(id);
        List<Help> helps = helpService.selectByHelp(help);
        if(helps.size()>0){
            Help help1 = helps.get(0);
            //改变帮助状态为完成状态
            help1.setPublishState(4);
            helpService.updateHelp(help1);
        }
        return WebResponse.success();
    }
}
