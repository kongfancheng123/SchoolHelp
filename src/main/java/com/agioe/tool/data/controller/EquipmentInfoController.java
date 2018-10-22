package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public WebResponse sendEquipmentRealtimeData(@RequestBody SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo) throws Exception {
        String ip = "192.168.52.50";
        return equipmentInfoService.sendEquipmentRealtimeData(sendEquipmentRealtimeDataQo, ip);
    }

    /**
     * 停止发送实时数据
     *
     * @return
     */
    @RequestMapping(value = "/stopSendEquipmentRealtimeData", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse stopSendEquipmentRealtimeData() {
        String ip = "192.168.52.50";
        return equipmentInfoService.stopSendEquipmentRealtimeData(ip);
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
     * @param
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

    /**
     * 获取发送数据控制值
     */
    @RequestMapping(value = "/getSendControlVal", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getSendControlVal() {
        return equipmentInfoService.getSendControlVal();
    }

    /**
     * 获取解除事件相关值
     */
    @RequestMapping(value = "/getDealEventHistoryValue", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse getDealEventHistoryValue(@RequestBody GetDealEventHistoryValueQo getDealEventHistoryValueQo) {
        return equipmentInfoService.getDealEventHistoryValue(getDealEventHistoryValueQo);
    }

    /**
     * 获取访问ip
     */
    @RequestMapping(value = "/getIp", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getIp(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return WebResponse.success(ip);
//        return equipmentInfoService.getSendControlVal(ipAddress);
    }


}
