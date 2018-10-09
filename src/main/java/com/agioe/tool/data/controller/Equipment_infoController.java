package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Equipment_info")
public class Equipment_infoController {
    @Autowired
    private Equipment_infoService equipment_infoService;

    /**
     * 展示所有设备
     *
     * @return
     */
    @RequestMapping(value = "/showAllEquipment_info", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllEquipment_info() {
        return equipment_infoService.showAllEquipment_info();
    }

    /**
     * 添加设备
     *
     * @param addEquipment_info1Qo
     * @return
     */
    @RequestMapping(value = "/addEquipment_info1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEquipment_info1(@RequestBody AddEquipment_info1Qo addEquipment_info1Qo) {
        return equipment_infoService.addEquipment_info1(addEquipment_info1Qo);
    }

    /**
     * 更新设备
     *
     * @param updateEquipment_info1Qo
     * @return
     */
    @RequestMapping(value = "/updateEquipment_info1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateEquipment_info1(@RequestBody UpdateEquipment_info1Qo updateEquipment_info1Qo) {
        return equipment_infoService.updateEquipment_info1(updateEquipment_info1Qo);
    }

    /**
     * 删除设备
     *
     * @param deleteEquipment_info1Qo
     * @return
     */
    @RequestMapping(value = "/deleteEquipment_info1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteEquipment_info1(@RequestBody DeleteEquipment_info1Qo deleteEquipment_info1Qo) {
        return equipment_infoService.deleteEquipment_info1(deleteEquipment_info1Qo);
    }


    /**
     * 条件查询设备
     *
     * @return
     */
    @RequestMapping(value = "/showEquipment_infoByCondition", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showEquipment_infoByCondition(@RequestBody ShowEquipment_infoByConditionQo showEquipment_infoByConditionQo) {
        return equipment_infoService.showEquipment_infoByCondition(showEquipment_infoByConditionQo);
    }


    /**
     * 发送实时数据
     *
     * @return
     */
    @RequestMapping(value = "/sendEquipment_realtime_data", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse sendEquipment_realtime_data(@RequestBody SendEquipment_realtime_dataQo sendEquipment_realtime_dataQo) {
        return equipment_infoService.sendEquipment_realtime_data(sendEquipment_realtime_dataQo);
    }

    /**
     * 发送事件
     *
     * @return
     */
    @RequestMapping(value = "/sendEvent_history", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse sendEvent_history(@RequestBody SendEvent_historyQo sendEvent_historyQo) {
        return equipment_infoService.sendEvent_history(sendEvent_historyQo);
    }

    /**
     * 解除事件
     */
    @RequestMapping(value = "/dealEvent_history", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse dealEvent_history(@RequestBody DealEvent_historyQo dealEvent_historyQo) {
        return equipment_infoService.dealEvent_history(dealEvent_historyQo);
    }

}
