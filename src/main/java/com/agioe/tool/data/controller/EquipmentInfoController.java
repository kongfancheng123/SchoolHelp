package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/EquipmentInfo")
public class EquipmentInfoController {
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Autowired
    private ParentNodeService parentNodeService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;
    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;
    @Autowired
    private MonitorPropertyService monitorPropertyService;


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
    public WebResponse stopSendEquipmentRealtimeData(@RequestBody GetSendControlValQo getSendControlValQo) {
        return equipmentInfoService.stopSendEquipmentRealtimeData(getSendControlValQo);
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
    public void exportExcelEquipmentInfo(@RequestBody ExportExcelEquipmentInfoQo exportExcelEquipmentInfoQo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelEquipmentInfoQo1> exportExcelEquipmentInfoQo1s=new ArrayList<>();
        ParentNode parentNode=new ParentNode();
        parentNode.setParentNodeCode(exportExcelEquipmentInfoQo.getParentNodeCode());
        List<EquipmentInfo> equipmentInfos = equipmentInfoService.selectAll(parentNode);
        if(equipmentInfos.size()>0){
            int i=1;
            for(EquipmentInfo equipmentInfo:equipmentInfos){
                ExportExcelEquipmentInfoQo1 exportExcelEquipmentInfoQo1=new ExportExcelEquipmentInfoQo1();
                exportExcelEquipmentInfoQo1.setOrderNumber(String.valueOf(i));
                //获取上层节点名称
                ParentNode parentNode1 = new ParentNode();
                parentNode1.setParentNodeCode(exportExcelEquipmentInfoQo.getParentNodeCode());
                List<ParentNode> parentNodes1 = parentNodeService.selectByParentNode(parentNode);
                if (parentNodes1.size() > 0) {
                    exportExcelEquipmentInfoQo1.setParentNodeName(parentNodes1.get(0).getParentNodeName());
                }
                exportExcelEquipmentInfoQo1.setEquipmentCode(equipmentInfo.getEquipmentCode());
                exportExcelEquipmentInfoQo1.setEquipmentName(equipmentInfo.getEquipmentName());
                //获取设备类型
                String equipmentType = equipmentInfo.getEquipmentType();
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
                if (equipmentTypes.size() > 0) {
                    exportExcelEquipmentInfoQo1.setEquipmentType(equipmentTypes.get(0).getEquipmentTypeName());
                }
                //获取模板
                String equipmentPropertyTemplateCode = equipmentInfo.getEquipmentPropertyTemplateCode();
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    exportExcelEquipmentInfoQo1.setEquipmentPropertyTemplate(monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateName());
                }
                //获取信号
                String equipmentPropertyCode = equipmentInfo.getEquipmentPropertyCode();
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                String equipmentPropertyTypeName="";
                if (monitorProperties.size() > 0) {
                    Integer equipmentPropertyType = monitorProperties.get(0).getEquipmentPropertyType();
                    if (equipmentPropertyType == 0) {
                        equipmentPropertyTypeName = "遥测";
                    } else if (equipmentPropertyType == 1) {
                        equipmentPropertyTypeName = "遥信";
                    } else if (equipmentPropertyType == 2) {
                        equipmentPropertyTypeName = "遥控";
                    } else if (equipmentPropertyType == 3) {
                        equipmentPropertyTypeName = "遥调";
                    } else {
                        equipmentPropertyTypeName = "说明";
                    }
                    exportExcelEquipmentInfoQo1.setEquipmentPropertyCode(monitorProperties.get(0).getEquipmentPropertyName());
                }
                exportExcelEquipmentInfoQo1.setEquipmentPropertyType(equipmentPropertyTypeName);
                exportExcelEquipmentInfoQo1.setKeyword(equipmentInfo.getKeyword());
                i++;
                exportExcelEquipmentInfoQo1s.add(exportExcelEquipmentInfoQo1);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("parentNodeName", "上层节点");
        fieldMap.put("equipmentCode", "设备编码");
        fieldMap.put("equipmentName", "设备名称");
        fieldMap.put("equipmentType", "设备类型");
        fieldMap.put("equipmentPropertyTemplate", "设备信号模板");
        fieldMap.put("equipmentPropertyType", "监控信号类型");
        fieldMap.put("equipmentPropertyCode", "设备信号");
        fieldMap.put("keyword", "设备点号");


        ExcelHelperWrite.listToExcel("设备信息表", exportExcelEquipmentInfoQo1s, fieldMap, "设备信息表", 30000, response, request);
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
    public WebResponse getSendControlVal(@RequestBody GetSendControlValQo getSendControlValQo) {
        return equipmentInfoService.getSendControlVal(getSendControlValQo);
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
