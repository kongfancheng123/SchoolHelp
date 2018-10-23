package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.MonitorProperty;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.MonitorPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
     * 分页展示监控信息
     *
     * @return
     */
    @RequestMapping(value = "/showPageMonitorProperty", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageMonitorProperty(@RequestBody PageQo pageQo) {
        return monitorPropertyService.showPageMonitorProperty(pageQo);
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

    /**
     * 导出监控信号表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportExcelMonitorProperty", method = RequestMethod.POST)
    @ResponseBody
    public void exportExcelMonitorProperty(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelMonitorPropertyQo> exportExcelMonitorPropertyQos=new ArrayList<>();
        List<MonitorProperty> monitorProperties = monitorPropertyService.selectAll();
        if(monitorProperties.size()>0){
            int i=1;
            for(MonitorProperty monitorProperty:monitorProperties){
                ExportExcelMonitorPropertyQo exportExcelMonitorPropertyQo=new ExportExcelMonitorPropertyQo();
                exportExcelMonitorPropertyQo.setOrderNumber(String.valueOf(i));
                exportExcelMonitorPropertyQo.setMonitorPropertyCode(monitorProperty.getEquipmentPropertyCode());
                exportExcelMonitorPropertyQo.setMonitorPropertyName(monitorProperty.getEquipmentPropertyName());
                Integer equipmentPropertyType = monitorProperty.getEquipmentPropertyType();
                String monitorPropertyType="";
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
                exportExcelMonitorPropertyQo.setMonitorPropertyType(monitorPropertyType);
                i++;
                exportExcelMonitorPropertyQos.add(exportExcelMonitorPropertyQo);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("monitorPropertyCode", "属性编码");
        fieldMap.put("monitorPropertyName", "属性名称");
        fieldMap.put("monitorPropertyType", "属性类型");
        ExcelHelperWrite.listToExcel("设备监控信号表", exportExcelMonitorPropertyQos, fieldMap, "设备监控信号表", 30000, response, request);
    }

    /**
     * 导入监控信号表
     *
     * @param importExcelMonitorPropertyQo
     * @return
     */
    @RequestMapping(value = "/importExcelMonitorProperty", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelMonitorProperty(@RequestBody ImportExcelMonitorPropertyQo importExcelMonitorPropertyQo) throws Exception {
        return monitorPropertyService.importExcelMonitorProperty(importExcelMonitorPropertyQo);
    }


}
