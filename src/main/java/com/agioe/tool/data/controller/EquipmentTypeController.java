package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/EquipmentType")
public class EquipmentTypeController {
    @Autowired
    private EquipmentTypeService equipmentTypeService;


    /**
     * 展示所有设备类型
     *
     * @return
     */
    @RequestMapping(value = "/showAllEquipmentType", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllEquipmentType() {
        return equipmentTypeService.showAllEquipmentType();
    }

    /**
     * 分页展示设备类型
     *
     * @return
     */
    @RequestMapping(value = "/showPageEquipmentType", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageEquipmentType(@RequestBody PageQo pageQo) {
        return equipmentTypeService.showPageEquipmentType(pageQo);
    }

    /**
     * 添加设备类型
     *
     * @param addEquipmentTypeQo
     * @return
     */
    @RequestMapping(value = "/addEquipmentType1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEquipmentType1(@RequestBody AddEquipmentTypeQo addEquipmentTypeQo) {
        return equipmentTypeService.addEquipmentType1(addEquipmentTypeQo);
    }

    /**
     * 更新设备类型
     *
     * @param updateEquipmentType1Qo
     * @return
     */
    @RequestMapping(value = "/updateEquipmentType1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateEquipmentType1(@RequestBody UpdateEquipmentType1Qo updateEquipmentType1Qo) {
        return equipmentTypeService.updateEquipmentType1(updateEquipmentType1Qo);
    }

    /**
     * 删除设备类型
     *
     * @param deleteEquipmentType1Qo
     * @return
     */
    @RequestMapping(value = "/deleteEquipmentType1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteEquipmentType1(@RequestBody DeleteEquipmentType1Qo deleteEquipmentType1Qo) {
        return equipmentTypeService.deleteEquipmentType1(deleteEquipmentType1Qo);
    }

    /**
     * 导出设备类型
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportExcelEquipmentType", method = RequestMethod.POST)
    @ResponseBody
    public void exportExcelEquipmentType(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelEquipmentTypeQo> exportExcelEquipmentTypeQoList=new ArrayList<>();
        List<EquipmentType> equipmentTypes = equipmentTypeService.selectAll();
        if(equipmentTypes.size()>0){
            int i=1;
            for(EquipmentType equipmentType:equipmentTypes){
                ExportExcelEquipmentTypeQo exportExcelEquipmentTypeQo=new ExportExcelEquipmentTypeQo();
                exportExcelEquipmentTypeQo.setOrderNumber(String.valueOf(i));
                exportExcelEquipmentTypeQo.setEquipmentTypeCode(equipmentType.getEquipmentTypeCode());
                exportExcelEquipmentTypeQo.setEquipmentTypeName(equipmentType.getEquipmentTypeName());
                i++;
                exportExcelEquipmentTypeQoList.add(exportExcelEquipmentTypeQo);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("equipmentTypeCode", "类型编码");
        fieldMap.put("equipmentTypeName", "类型名称");


        ExcelHelperWrite.listToExcel("设备类型表", exportExcelEquipmentTypeQoList, fieldMap, "设备类型表", 30000, response, request);
    }

    /**
     * 导入设备类型
     *
     * @param importExcelEquipmentTypeQo
     * @return
     */
    @RequestMapping(value = "/importExcelEquipmentType", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelEquipmentType(@RequestBody ImportExcelEquipmentTypeQo importExcelEquipmentTypeQo) throws Exception {
        return equipmentTypeService.importExcelEquipmentType(importExcelEquipmentTypeQo);
    }


}
