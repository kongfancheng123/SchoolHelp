package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitor_property1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Monitor_propertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/Monitor_property")
public class Monitor_propertyController {
    @Autowired
    private Monitor_propertyService monitor_propertyService;

    /**
     * 展示所有监控信息
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitor_property", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitor_property() {
        return monitor_propertyService.showAllMonitor_property();
    }

    /**
     * 添加监控信息
     *
     * @param addMonitor_property1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitor_property1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitor_property1(@RequestBody AddMonitor_property1Qo addMonitor_property1Qo) {
        return monitor_propertyService.addMonitor_property1(addMonitor_property1Qo);
    }

    /**
     * 更新监控信息
     *
     * @param updateMonitor_property1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitor_property1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitor_property1(@RequestBody UpdateMonitor_property1Qo updateMonitor_property1Qo) {
        return monitor_propertyService.updateMonitor_property1(updateMonitor_property1Qo);
    }

    /**
     * 删除监控信息
     *
     * @param deleteMonitor_property1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitor_property1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitor_property1(@RequestBody DeleteMonitor_property1Qo deleteMonitor_property1Qo) {
        return monitor_propertyService.deleteMonitor_property1(deleteMonitor_property1Qo);
    }


}
