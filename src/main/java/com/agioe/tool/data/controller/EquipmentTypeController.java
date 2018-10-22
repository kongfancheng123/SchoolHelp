package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public WebResponse exportExcelEquipmentType() throws Exception {
        return equipmentTypeService.exportExcelEquipmentType();
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
