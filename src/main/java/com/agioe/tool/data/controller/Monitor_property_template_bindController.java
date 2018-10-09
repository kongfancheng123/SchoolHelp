package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template_bind1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Monitor_property_template_bindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/Monitor_property_template_bind")
public class Monitor_property_template_bindController {
    @Autowired
    private Monitor_property_template_bindService monitor_property_template_bindService;

    /**
     * 展示所有监控模板关联
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitor_property_template_bind", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitor_property_template_bind() {
        return monitor_property_template_bindService.showAllMonitor_property_template_bind();
    }

    /**
     * 添加监控模板关联
     *
     * @param addMonitor_property_template_bind1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitor_property_template_bind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitor_property_template_bind1(@RequestBody AddMonitor_property_template_bind1Qo addMonitor_property_template_bind1Qo) {
        return monitor_property_template_bindService.addMonitor_property_template_bind1(addMonitor_property_template_bind1Qo);
    }

    /**
     * 更新监控模板关联
     *
     * @param updateMonitor_property_template_bind1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitor_property_template_bind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitor_property_template_bind1(@RequestBody UpdateMonitor_property_template_bind1Qo updateMonitor_property_template_bind1Qo) {
        return monitor_property_template_bindService.updateMonitor_property_template_bind1(updateMonitor_property_template_bind1Qo);
    }

    /**
     * 删除监控模板关联
     *
     * @param deleteMonitor_property_template_bind1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitor_property_template_bind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitor_property_template_bind1(@RequestBody DeleteMonitor_property_template_bind1Qo deleteMonitor_property_template_bind1Qo) {
        return monitor_property_template_bindService.deleteMonitor_property_template_bind1(deleteMonitor_property_template_bind1Qo);
    }


    /**
     * 获取设备类型模板树
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getEquipment_type_template_link", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getEquipment_type_template_link() {
        return monitor_property_template_bindService.getEquipment_type_template_link();
    }


}
