package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitorProperty1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorProperty1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorProperty1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.MonitorPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/MonitorProperty")
public class MonitorPropertyController {
    @Autowired
    private MonitorPropertyService monitorPropertyService;

    /**
     * 展示所有监控信息
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitorProperty", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitorProperty() {
        return monitorPropertyService.showAllMonitorProperty();
    }

    /**
     * 添加监控信息
     *
     * @param addMonitorProperty1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitorProperty1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitorProperty1(@RequestBody AddMonitorProperty1Qo addMonitorProperty1Qo) {
        return monitorPropertyService.addMonitorProperty1(addMonitorProperty1Qo);
    }

    /**
     * 更新监控信息
     *
     * @param updateMonitorProperty1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitorProperty1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitorProperty1(@RequestBody UpdateMonitorProperty1Qo updateMonitorProperty1Qo) {
        return monitorPropertyService.updateMonitorProperty1(updateMonitorProperty1Qo);
    }

    /**
     * 删除监控信息
     *
     * @param deleteMonitorProperty1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitorProperty1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitorProperty1(@RequestBody DeleteMonitorProperty1Qo deleteMonitorProperty1Qo) {
        return monitorPropertyService.deleteMonitorProperty1(deleteMonitorProperty1Qo);
    }


}
