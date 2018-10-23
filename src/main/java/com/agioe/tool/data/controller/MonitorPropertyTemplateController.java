package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.MonitorPropertyTemplate;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.EquipmentTypeService;
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
@RequestMapping(value = "/MonitorPropertyTemplate")
public class MonitorPropertyTemplateController {
    @Autowired
    private MonitorPropertyTemplateService monitorPropertyTemplateService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    /**
     * 展示所有监控模板
     *
     * @return
     */
    @RequestMapping(value = "/showAllMonitorPropertyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllMonitorPropertyTemplate() {
        return monitorPropertyTemplateService.showAllMonitorPropertyTemplate();
    }

    /**
     * 分页展示监控模板
     *
     * @return
     */
    @RequestMapping(value = "/showPageMonitorPropertyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageMonitorPropertyTemplate(@RequestBody PageQo pageQo) {
        return monitorPropertyTemplateService.showPageMonitorPropertyTemplate(pageQo);
    }

    /**
     * 添加监控模板
     *
     * @param addMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/addMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addMonitorPropertyTemplate1(@RequestBody AddMonitorPropertyTemplate1Qo addMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.addMonitorPropertyTemplate1(addMonitorPropertyTemplate1Qo);
    }

    /**
     * 更新监控模板
     *
     * @param updateMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/updateMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateMonitorPropertyTemplate1(@RequestBody UpdateMonitorPropertyTemplate1Qo updateMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.updateMonitorPropertyTemplate1(updateMonitorPropertyTemplate1Qo);
    }

    /**
     * 删除监控模板
     *
     * @param deleteMonitorPropertyTemplate1Qo
     * @return
     */
    @RequestMapping(value = "/deleteMonitorPropertyTemplate1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteMonitorPropertyTemplate1(@RequestBody DeleteMonitorPropertyTemplate1Qo deleteMonitorPropertyTemplate1Qo) {
        return monitorPropertyTemplateService.deleteMonitorPropertyTemplate1(deleteMonitorPropertyTemplate1Qo);
    }

    /**
     * 导出监控信号模板表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportExcelMonitorPropertyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public void exportExcelMonitorPropertyTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelMonitorPropertyTemplateQo> exportExcelMonitorPropertyTemplateQos=new ArrayList<>();
        List<MonitorPropertyTemplate> monitorPropertyTemplates = monitorPropertyTemplateService.selectAll();
        if(monitorPropertyTemplates.size()>0){
            int i=1;
            for(MonitorPropertyTemplate monitorPropertyTemplate:monitorPropertyTemplates){
                ExportExcelMonitorPropertyTemplateQo exportExcelMonitorPropertyTemplateQo=new ExportExcelMonitorPropertyTemplateQo();
                exportExcelMonitorPropertyTemplateQo.setOrderNumber(String.valueOf(i));
                String equipmentTypeCode = monitorPropertyTemplate.getEquipmentType();
                EquipmentType equipmentType1111 = new EquipmentType();
                equipmentType1111.setEquipmentTypeCode(equipmentTypeCode);
                List<EquipmentType> equipmentTypess = equipmentTypeService.selectByEquipmentType(equipmentType1111);
                if (equipmentTypess.size() > 0) {
                    exportExcelMonitorPropertyTemplateQo.setEquipmentType(equipmentTypess.get(0).getEquipmentTypeName());
                }
                exportExcelMonitorPropertyTemplateQo.setEquipmentPropertyTemplateCode(monitorPropertyTemplate.getEquipmentPropertyTemplateCode());
                exportExcelMonitorPropertyTemplateQo.setEquipmentPropertyTemplateName(monitorPropertyTemplate.getEquipmentPropertyTemplateName());
                exportExcelMonitorPropertyTemplateQo.setEquipmentPropertyTemplateDesc(monitorPropertyTemplate.getEquipmentPropertyTemplateDescription());
                i++;
                exportExcelMonitorPropertyTemplateQos.add(exportExcelMonitorPropertyTemplateQo);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("equipmentType", "设备类型");
        fieldMap.put("equipmentPropertyTemplateCode", "模板编码");
        fieldMap.put("equipmentPropertyTemplateName", "模板名称");
        fieldMap.put("equipmentPropertyTemplateDesc", "模板描述");


        ExcelHelperWrite.listToExcel("设备监控信号模板表", exportExcelMonitorPropertyTemplateQos, fieldMap, "设备监控信号模板表", 30000, response, request);
    }

    /**
     * 导入监控信号模板表
     *
     * @param importExcelMonitorPropertyTemplateQo
     * @return
     */
    @RequestMapping(value = "/importExcelMonitorPropertyTemplate", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelMonitorPropertyTemplate(@RequestBody ImportExcelMonitorPropertyTemplateQo importExcelMonitorPropertyTemplateQo) throws Exception {
        return monitorPropertyTemplateService.importExcelMonitorPropertyTemplate(importExcelMonitorPropertyTemplateQo);
    }


}
