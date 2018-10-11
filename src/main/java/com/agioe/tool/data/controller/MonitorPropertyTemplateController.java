package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorPropertyTemplate1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.MonitorPropertyTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/MonitorPropertyTemplate")
public class MonitorPropertyTemplateController {
    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;

    /**
     * 展示所有监控模板
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitorPropertyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitorPropertyTemplate() {
        return monitorPropertyTemplateService.showAllMonitorPropertyTemplate();
    }

    /**
     * 添加监控模板
     *
     * @param addMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitorPropertyTemplate1(@RequestBody AddMonitorPropertyTemplate1Qo addMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.addMonitorPropertyTemplate1(addMonitorPropertyTemplate1Qo);
    }

    /**
     * 更新监控模板
     *
     * @param updateMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitorPropertyTemplate1(@RequestBody UpdateMonitorPropertyTemplate1Qo updateMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.updateMonitorPropertyTemplate1(updateMonitorPropertyTemplate1Qo);
    }

    /**
     * 删除监控模板
     *
     * @param deleteMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitorPropertyTemplate1(@RequestBody DeleteMonitorPropertyTemplate1Qo deleteMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.deleteMonitorPropertyTemplate1(deleteMonitorPropertyTemplate1Qo);
    }


}
