package com.serotonin.applicationController;

import com.serotonin.applicationService.HelpApplicationService;
import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;
import org.omg.CORBA.INTERNAL;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/8.
 */
@RestController
@RequestMapping(value = "/web/application/help")
public class HelpApplicationController {

    @Resource
    private HelpApplicationService helpApplicationService;

    /**
     * 发布帮助
     *
     * @return
     */
    @RequestMapping(value = "/publishHelp", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse publishHelp(@RequestBody Help help) {
        return helpApplicationService.publishHelp(help);
    }

    /**
     *  取消发布帮助
     */
    @RequestMapping(value = "/cancelPublishHelp", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse cancelPublishHelp(@RequestBody Integer id) {
        return helpApplicationService.cancelPublishHelp(id);
    }

    /**
     * 接受帮助
     */
    @RequestMapping(value = "/receiveHelp", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse receiveHelp(@RequestBody Integer id) {
        return helpApplicationService.receiveHelp(id);
    }

    /**
     * 确认完成帮助
     */
    @RequestMapping(value = "/finishHelp", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse finishHelp(@RequestBody Integer id) {
        return helpApplicationService.finishHelp(id);
    }
}
