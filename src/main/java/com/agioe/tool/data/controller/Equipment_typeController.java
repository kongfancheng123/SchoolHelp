package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddEquipment_typeQo;
import com.agioe.tool.data.Qo.DeleteEquipment_type1Qo;
import com.agioe.tool.data.Qo.UpdateEquipment_type1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/Equipment_type")
public class Equipment_typeController {
    @Autowired
    private Equipment_typeService equipment_typeService;

    /**
     * 展示所有设备类型
     *
     * @return
     */
    @RequestMapping(value = "/showAllEquipment_type", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllEquipment_type() {
        return equipment_typeService.showAllEquipment_type();
    }

    /**
     * 添加设备类型
     *
     * @param addEquipment_typeQo
     * @return
     */
    @RequestMapping(value = "/addEquipment_type1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addEquipment_type1(@RequestBody AddEquipment_typeQo addEquipment_typeQo) {
        return equipment_typeService.addEquipment_type1(addEquipment_typeQo);
    }

    /**
     * 更新设备类型
     *
     * @param updateEquipment_type1Qo
     * @return
     */
    @RequestMapping(value = "/updateEquipment_type1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateEquipment_type1(@RequestBody UpdateEquipment_type1Qo updateEquipment_type1Qo) {
        return equipment_typeService.updateEquipment_type1(updateEquipment_type1Qo);
    }

    /**
     * 删除设备类型
     *
     * @param deleteEquipment_type1Qo
     * @return
     */
    @RequestMapping(value = "/deleteEquipment_type1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteEquipment_type1(@RequestBody DeleteEquipment_type1Qo deleteEquipment_type1Qo) {
        return equipment_typeService.deleteEquipment_type1(deleteEquipment_type1Qo);
    }


}
