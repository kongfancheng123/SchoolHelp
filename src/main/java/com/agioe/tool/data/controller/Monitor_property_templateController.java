package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Monitor_property_templateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/Monitor_property_template")
public class Monitor_property_templateController {
    @Autowired
    private Monitor_property_templateService monitor_property_templateService;

    /**
     * 展示所有监控模板
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitor_property_template", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitor_property_template() {
        return monitor_property_templateService.showAllMonitor_property_template();
    }

    /**
     * 添加监控模板
     *
     * @param addMonitor_property_template1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitor_property_template1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitor_property_template1(@RequestBody AddMonitor_property_template1Qo addMonitor_property_template1Qo) {
        return monitor_property_templateService.addMonitor_property_template1(addMonitor_property_template1Qo);
    }

    /**
     * 更新监控模板
     *
     * @param updateMonitor_property_template1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitor_property_template1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitor_property_template1(@RequestBody UpdateMonitor_property_template1Qo updateMonitor_property_template1Qo) {
        return monitor_property_templateService.updateMonitor_property_template1(updateMonitor_property_template1Qo);
    }

    /**
     * 删除监控模板
     *
     * @param deleteMonitor_property_template1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitor_property_template1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitor_property_template1(@RequestBody DeleteMonitor_property_template1Qo deleteMonitor_property_template1Qo) {
        return monitor_property_templateService.deleteMonitor_property_template1(deleteMonitor_property_template1Qo);
    }


}
