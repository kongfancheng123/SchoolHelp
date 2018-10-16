package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/EquipmentInfo")
public class EquipmentInfoController {
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    /**
     * 展示所有设备
     *
     * @return
     */
    @RequestMapping(value = "/showAllEquipmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllEquipmentInfo() {
        return equipmentInfoService.showAllEquipmentInfo();
    }

    /**
     * 分页展示设备
     *
     * @return
     */
    @RequestMapping(value = "/showPageEquipmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageEquipmentInfo(@RequestBody PageQo pageQo) {
        return equipmentInfoService.showPageEquipmentInfo(pageQo);
    }

    /**
     * 添加设备
     *
     * @param addEquipmentInfo1Qo
     * @return
     */
    @RequestMapping(value = "/addEquipmentInfo1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEquipmentInfo1(@RequestBody AddEquipmentInfo1Qo addEquipmentInfo1Qo) {
        return equipmentInfoService.addEquipmentInfo1(addEquipmentInfo1Qo);
    }

    /**
     * 更新设备
     *
     * @param updateEquipmentInfo1Qo
     * @return
     */
    @RequestMapping(value = "/updateEquipmentInfo1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateEquipmentInfo1(@RequestBody UpdateEquipmentInfo1Qo updateEquipmentInfo1Qo) {
        return equipmentInfoService.updateEquipmentInfo1(updateEquipmentInfo1Qo);
    }

    /**
     * 删除设备
     *
     * @param deleteEquipmentInfo1Qo
     * @return
     */
    @RequestMapping(value = "/deleteEquipmentInfo1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteEquipmentInfo1(@RequestBody DeleteEquipmentInfo1Qo deleteEquipmentInfo1Qo) {
        return equipmentInfoService.deleteEquipmentInfo1(deleteEquipmentInfo1Qo);
    }


    /**
     * 条件查询设备
     *
     * @return
     */
    @RequestMapping(value = "/showEquipmentInfoByCondition", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showEquipmentInfoByCondition(@RequestBody ShowEquipmentInfoByConditionQo showEquipmentInfoByConditionQo) {
        return equipmentInfoService.showEquipmentInfoByCondition(showEquipmentInfoByConditionQo);
    }


    /**
     * 发送实时数据
     *
     * @return
     */
    @RequestMapping(value = "/sendEquipmentRealtimeData", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse sendEquipmentRealtimeData(@RequestBody SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo) {
        return equipmentInfoService.sendEquipmentRealtimeData(sendEquipmentRealtimeDataQo);
    }

    /**
     * 发送事件
     *
     * @return
     */
    @RequestMapping(value = "/sendEventHistory", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse sendEventHistory(@RequestBody SendEventHistoryQo sendEventHistoryQo) {
        return equipmentInfoService.sendEventHistory(sendEventHistoryQo);
    }

    /**
     * 解除事件
     */
    @RequestMapping(value = "/dealEventHistory", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse dealEventHistory(@RequestBody DealEventHistoryQo dealEventHistoryQo) {
        return equipmentInfoService.dealEventHistory(dealEventHistoryQo);
    }

    /**
     * 根据设备类型和模板查找属性
     */
    @RequestMapping(value = "/getPropertyByTypeAndTemplate", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getPropertyByTypeAndTemplate(@RequestBody GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo) {

        return equipmentInfoService.getPropertyByTypeAndTemplate(getPropertyByTypeAndTemplateQo);
    }

    /**
     * 根据模板,设备类型和上层节点获取属性
     */
    @RequestMapping(value = "/getPropertyByTypeAndTemplateAndParentNode", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getPropertyByTypeAndTemplateAndParentNode(@RequestBody GetPropertyByTypeAndTemplateAndParentNodeQo getPropertyByTypeAndTemplateAndParentNodeQo) {

        return equipmentInfoService.getPropertyByTypeAndTemplateAndParentNode(getPropertyByTypeAndTemplateAndParentNodeQo);
    }

    /**
     * 生成keyword
     */
    @RequestMapping(value = "/createKeyWord", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse createKeyWord(@RequestBody CreateKeyWordQo createKeyWordQo) {
        return equipmentInfoService.createKeyWord(createKeyWordQo);
    }

    /**
     * 导出设备信息表
     *
     * @param exportExcelEquipmentInfoQo
     * @return
     */
    @RequestMapping(value = "/exportExcelEquipmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse exportExcelEquipmentInfo(@RequestBody ExportExcelEquipmentInfoQo exportExcelEquipmentInfoQo) throws Exception {
        return equipmentInfoService.exportExcelEquipmentInfo(exportExcelEquipmentInfoQo);
    }

    /**
     * 导入设备信息表
     *
     * @param importExcelEquipmentInfoQo
     * @return
     */
    @RequestMapping(value = "/importExcelEquipmentInfo", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelEquipmentInfo(@RequestBody ImportExcelEquipmentInfoQo importExcelEquipmentInfoQo) throws Exception {
        return equipmentInfoService.importExcelEquipmentInfo(importExcelEquipmentInfoQo);
    }


}
