package com.serotonin.managementController;

import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.HelpManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/4.
 */
@RestController
@RequestMapping(value = "/web/management/help")
public class HelpController {
    @Resource
    private HelpManagementService helpManagementService;

    /**
     * 获取帮帮忙列表
     */
    @RequestMapping(value = "/getHelps", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getHelps() {
        return helpManagementService.getHelps();
    }

    /**
     * 添加帮帮忙
     */
    @RequestMapping(value = "/addHelp", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addHelp(@RequestBody Help help) {
        return helpManagementService.addHelp(help);
    }

    /**
     * 删除帮帮忙
     */
    @RequestMapping(value = "/deleteHelp", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteHelp(@RequestParam Integer id) {
        return helpManagementService.deleteHelp(id);
    }

    /**
     * 更新帮帮忙
     */
    @RequestMapping(value = "/updateHelp", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateHelp(@RequestBody Help help) {
        return helpManagementService.updateHelp(help);
    }
}
