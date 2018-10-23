package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.*;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.EquipmentTypeService;
import com.agioe.tool.data.service.MonitorPropertyService;
import com.agioe.tool.data.service.MonitorPropertyTemplateBindService;
import com.agioe.tool.data.service.MonitorPropertyTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/MonitorPropertyTemplateBind")
public class MonitorPropertyTemplateBindController {
    @Autowired
    private MonitorPropertyTemplateBindService monitorPropertyTemplateBindService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;

    @Autowired
    private MonitorPropertyService monitorPropertyService;

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
     * 分页展示监控模板关联
     *
     * @return
     */
    @RequestMapping(value = "/showPageMonitorPropertyTemplateBind", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageMonitorPropertyTemplateBind(@RequestBody PageQo pageQo) {
        return monitorPropertyTemplateBindService.showPageMonitorPropertyTemplateBind(pageQo);
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


    /**
     * 导出信号模板关联表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportExcelMonitorPropertyTemplateBind", method = RequestMethod.POST)
    @ResponseBody
    public void exportExcelMonitorPropertyTemplateBind(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelMonitorPropertyTemplateBindQo> exportExcelMonitorPropertyTemplateBindQos=new ArrayList<>();
        List<MonitorPropertyTemplateBind> monitorPropertyTemplateBinds = monitorPropertyTemplateBindService.selectAll();
        if(monitorPropertyTemplateBinds.size()>0){
            int i=1;
            for(MonitorPropertyTemplateBind monitorPropertyTemplateBind:monitorPropertyTemplateBinds){
                ExportExcelMonitorPropertyTemplateBindQo exportExcelMonitorPropertyTemplateBindQo=new ExportExcelMonitorPropertyTemplateBindQo();
                exportExcelMonitorPropertyTemplateBindQo.setOrderNumber(String.valueOf(i));
                //获取设备类型名称
                String equipmentType =monitorPropertyTemplateBind.getEquipmentType();
                EquipmentType equipmentType1 = new EquipmentType();
                equipmentType1.setEquipmentTypeCode(equipmentType);
                List<EquipmentType> equipmentTypes = equipmentTypeService.selectByEquipmentType(equipmentType1);
                if (equipmentTypes.size() > 0) {
                    exportExcelMonitorPropertyTemplateBindQo.setEquipmentType(equipmentTypes.get(0).getEquipmentTypeName());
                }
                //获取模板名称
                String equipmentPropertyTemplateCode = monitorPropertyTemplateBind.getEquipmentPropertyTemplateCode();
                MonitorPropertyTemplate monitorPropertyTemplate = new MonitorPropertyTemplate();
                monitorPropertyTemplate.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
                List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectByMonitorPropertyTemplate(monitorPropertyTemplate);
                if (monitorPropertyTemplates.size() > 0) {
                    exportExcelMonitorPropertyTemplateBindQo.setPropertyTemplateName(monitorPropertyTemplates.get(0).getEquipmentPropertyTemplateName());
                }
                exportExcelMonitorPropertyTemplateBindQo.setMonitorPropertyCode(monitorPropertyTemplateBind.getEquipmentPropertyCode());
                //获取属性名称
                String equipmentPropertyCode = monitorPropertyTemplateBind.getEquipmentPropertyCode();
                MonitorProperty monitorProperty = new MonitorProperty();
                monitorProperty.setEquipmentPropertyCode(equipmentPropertyCode);
                List<MonitorProperty> monitorProperties = monitorPropertyService.selectByMonitorProperty(monitorProperty);
                String monitorPropertyType="";
                if (monitorProperties.size() > 0) {
                    exportExcelMonitorPropertyTemplateBindQo.setMonitorPropertyName(monitorProperties.get(0).getEquipmentPropertyName());
                    Integer equipmentPropertyType = monitorProperties.get(0).getEquipmentPropertyType();
                    if (equipmentPropertyType == 0) {
                        monitorPropertyType = "遥测";
                    } else if (equipmentPropertyType == 1) {
                        monitorPropertyType = "遥信";
                    } else if (equipmentPropertyType == 2) {
                        monitorPropertyType = "遥控";
                    } else if (equipmentPropertyType == 3) {
                        monitorPropertyType = "遥调";
                    } else {
                        monitorPropertyType = "说明";
                    }
                }
                exportExcelMonitorPropertyTemplateBindQo.setMonitorPropertyType(monitorPropertyType);
                i++;
                exportExcelMonitorPropertyTemplateBindQos.add(exportExcelMonitorPropertyTemplateBindQo);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("equipmentType", "设备类型");
        fieldMap.put("propertyTemplateName", "模板名称");
        fieldMap.put("monitorPropertyCode", "属性编码");
        fieldMap.put("monitorPropertyName", "属性名称");
        fieldMap.put("monitorPropertyType", "属性类型");


        ExcelHelperWrite.listToExcel("信号模板关联表", exportExcelMonitorPropertyTemplateBindQos, fieldMap, "信号模板关联表", 30000, response, request);
    }

    /**
     * 导入信号模板关联表
     *
     * @param importExcelMonitorPropertyTemplateBindQo
     * @return
     */
    @RequestMapping(value = "/importExcelMonitorPropertyTemplateBind", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelMonitorPropertyTemplateBind(@RequestBody ImportExcelMonitorPropertyTemplateBindQo importExcelMonitorPropertyTemplateBindQo) throws Exception {
        return monitorPropertyTemplateBindService.importExcelMonitorPropertyTemplateBind(importExcelMonitorPropertyTemplateBindQo);
    }

}
