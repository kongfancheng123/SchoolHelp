package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.tcp.api.ControlListener;
import com.agioe.tool.data.tcp.payload.ControlParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ControlListenerImpl implements ControlListener {
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Override
    public void onControlArrive(List<ControlParameter> parameterList) {
        //todo:操作数据库
        if (parameterList.size() > 0) {
            for (ControlParameter controlParameter : parameterList) {
                String key = controlParameter.getKey();
                Short controlTypeVal = controlParameter.getControlTypeVal();
                EquipmentInfo equipmentInfo = new EquipmentInfo();
                equipmentInfo.setControlVal(String.valueOf(controlTypeVal));
                equipmentInfo.setControllerUpdate(new Date());
                equipmentInfo.setKeyword(key);
                String[] split = key.split("_");
                String parentNodeCode = split[0];
                equipmentInfo.setParentNodeCode(parentNodeCode);
                equipmentInfoService.updateEquipmentInfo(equipmentInfo);
            }
        }
    }
}
