package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.MonitorPropertyTemplateBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/MonitorPropertyTemplateBind")
public class MonitorPropertyTemplateBindController {
    @Autowired
    private MonitorPropertyTemplateBindService monitorPropertyTemplateBindService;

    /**
     * 展示所有监控模板关联
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitorPropertyTemplateBind", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitorPropertyTemplateBind() {
        return monitorPropertyTemplateBindService.showAllMonitorPropertyTemplateBind();
    }

    /**
     * 添加监控模板关联
     *
     * @param addMonitorPropertyTemplateBind1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitorPropertyTemplateBind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitorPropertyTemplateBind1(@RequestBody AddMonitorPropertyTemplateBind1Qo addMonitorPropertyTemplateBind1Qo) {
        return monitorPropertyTemplateBindService.addMonitorPropertyTemplateBind1(addMonitorPropertyTemplateBind1Qo);
    }

    /**
     * 更新监控模板关联
     *
     * @param updateMonitorPropertyTemplateBind1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitorPropertyTemplateBind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitorPropertyTemplateBind1(@RequestBody UpdateMonitorPropertyTemplateBind1Qo updateMonitorPropertyTemplateBind1Qo) {
        return monitorPropertyTemplateBindService.updateMonitorPropertyTemplateBind1(updateMonitorPropertyTemplateBind1Qo);
    }

    /**
     * 删除监控模板关联
     *
     * @param deleteMonitorPropertyTemplateBind1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitorPropertyTemplateBind1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitorPropertyTemplateBind1(@RequestBody DeleteMonitorPropertyTemplateBind1Qo deleteMonitorPropertyTemplateBind1Qo) {
        return monitorPropertyTemplateBindService.deleteMonitorPropertyTemplateBind1(deleteMonitorPropertyTemplateBind1Qo);
    }


    /**
     * 获取设备类型模板树
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getEquipmentTypeTemplateLink", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getEquipmentTypeTemplateLink() {
        return monitorPropertyTemplateBindService.getEquipmentTypeTemplateLink();
    }


}
