package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.Vo.ShowAllEquipmentInfoVo;
import com.agioe.tool.data.dao.EquipmentInfoDao;
import com.agioe.tool.data.entity.CreateTableParam;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.EquipmentRealtimeData;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.EquipmentRealtimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EquipmentInfoServiceImpl implements EquipmentInfoService {
    @Autowired
    private EquipmentInfoDao equipmentInfoDao;
    @Autowired
    private EquipmentRealtimeDataService equipmentRealtimeDataService;

    @Override
    public Integer insertEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.insertEquipmentInfo(equipmentInfo);
    }

    @Override
    public Integer deleteEquipmentInfo(Integer id) {
        return equipmentInfoDao.deleteEquipmentInfo(id);
    }

    @Override
    public List<EquipmentInfo> selectByEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.selectByEquipmentInfo(equipmentInfo);
    }

    @Override
    public Integer updateEquipmentInfo(EquipmentInfo equipmentInfo) {
        return equipmentInfoDao.updateEquipmentInfo(equipmentInfo);
    }

    @Override
    public List<EquipmentInfo> selectAll() {
        return equipmentInfoDao.selectAll();
    }

    @Override
    public EquipmentInfo selectByid(Integer id) {
        return equipmentInfoDao.selectByid(id);
    }

    @Override
    public Integer createTable(CreateTableParam createTableParam) {
        return equipmentInfoDao.createTable(createTableParam);
    }

    @Override
    public WebResponse showAllEquipmentInfo() {
        List<ShowAllEquipmentInfoVo> showAllEquipmentInfoVos = new ArrayList<>();
        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectAll();
        if (EquipmentInfos.size() > 0) {
            for (EquipmentInfo EquipmentInfo : EquipmentInfos) {
                ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();
                showAllEquipmentInfoVo.setEquipmentCode(EquipmentInfo.getEquipmentCode());
                showAllEquipmentInfoVo.setEquipmentName(EquipmentInfo.getEquipmentName());
                showAllEquipmentInfoVo.setEquipmentPropertyCode(EquipmentInfo.getEquipmentPropertyCode());
                showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(EquipmentInfo.getEquipmentPropertyTemplateCode());
                showAllEquipmentInfoVo.setEquipmentType(EquipmentInfo.getEquipmentType());
                showAllEquipmentInfoVo.setKeyword(EquipmentInfo.getKeyword());
                showAllEquipmentInfoVo.setParentNodeCode(EquipmentInfo.getParentNodeCode());
                showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
            }
        }
        return WebResponse.success(showAllEquipmentInfoVos);
    }

//    @Override
//    public WebResponse addEquipmentInfo1(AddEquipmentInfo1Qo addEquipmentInfo1Qo) {
//        String equipment_code = addEquipmentInfo1Qo.get();
//        String equipment_name = addEquipmentInfo1Qo.getEquipment_name();
//        String equipment_property_code = addEquipmentInfo1Qo.getEquipment_property_code();
//        String equipment_property_template_code = addEquipmentInfo1Qo.getEquipment_property_template_code();
//        String equipment_type = addEquipmentInfo1Qo.getEquipment_type();
//        String keyword = addEquipmentInfo1Qo.getKeyword();
//        String parent_node_code = addEquipmentInfo1Qo.getParent_node_code();
////        //编码判重
////        EquipmentInfo EquipmentInfo=new EquipmentInfo();
////        EquipmentInfo.setEquipment_code(equipment_code);
////        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
////        if (EquipmentInfos.size()>0){
////            return WebResponse.error(400,"设备编码已存在");
////        }
////        //名字判重
////        EquipmentInfo EquipmentInfo1=new EquipmentInfo();
////        EquipmentInfo1.setEquipment_name(equipment_name);
////        List<EquipmentInfo> EquipmentInfos1 = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo1);
////        if(EquipmentInfos1.size()>0){
////            return WebResponse.error(400,"设备名已存在");
////        }
//        //key值判断重复
//        EquipmentInfo EquipmentInfo = new EquipmentInfo();
//        EquipmentInfo.setKeyword(keyword);
//        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
//        if (EquipmentInfos.size() > 0) {
//            return WebResponse.error(400, "key值已存在");
//        }
//        //添加
//        EquipmentInfo EquipmentInfo2 = new EquipmentInfo();
//        EquipmentInfo2.setEquipment_name(equipment_name);
//        EquipmentInfo2.setEquipment_code(equipment_code);
//        EquipmentInfo2.setEquipment_property_template_code(equipment_property_template_code);
//        EquipmentInfo2.setEquipment_property_code(equipment_property_code);
//        EquipmentInfo2.setEquipment_type(equipment_type);
//        EquipmentInfo2.setKeyword(keyword);
//        EquipmentInfo2.setParent_node_code(parent_node_code);
//        //设置其余值为空
//        EquipmentInfo2.setAlarm_val("");
//        EquipmentInfo2.setControl_val("");
//        EquipmentInfo2.setData_val("");
//        equipmentInfoDao.insertEquipmentInfo(EquipmentInfo2);
//        return WebResponse.success();
//    }

    @Override
    public WebResponse updateEquipmentInfo1(UpdateEquipmentInfo1Qo updateEquipmentInfo1Qo) {
        Integer id = updateEquipmentInfo1Qo.getId();
        String equipmentCode = updateEquipmentInfo1Qo.getEquipmentCode();
        String equipmentName = updateEquipmentInfo1Qo.getEquipmentName();
        String equipmentPropertyCode = updateEquipmentInfo1Qo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = updateEquipmentInfo1Qo.getEquipmentPropertyTemplateCode();
        String equipmentType = updateEquipmentInfo1Qo.getEquipmentType();
        String keyword = updateEquipmentInfo1Qo.getKeyword();
        String parentNodeCode = updateEquipmentInfo1Qo.getParentNodeCode();
        EquipmentInfo EquipmentInfo = equipmentInfoDao.selectByid(id);
        EquipmentInfo.setParentNodeCode(parentNodeCode);
        EquipmentInfo.setKeyword(keyword);
        EquipmentInfo.setEquipmentType(equipmentType);
        EquipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode);
        EquipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode);
        EquipmentInfo.setEquipmentCode(equipmentCode);
        EquipmentInfo.setEquipmentName(equipmentName);
        equipmentInfoDao.updateEquipmentInfo(EquipmentInfo);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteEquipmentInfo1(DeleteEquipmentInfo1Qo deleteEquipmentInfo1Qo) {
        Integer id = deleteEquipmentInfo1Qo.getId();
        EquipmentInfo EquipmentInfo = equipmentInfoDao.selectByid(id);
        String equipment_code = EquipmentInfo.getEquipmentCode();
        //判断设备是否有实时值
        EquipmentRealtimeData equipmentRealtimeData = new EquipmentRealtimeData();
        equipmentRealtimeData.setEquipmentCode(equipment_code);
        List<EquipmentRealtimeData> equipmentRealtimeData1 = equipmentRealtimeDataService.selectByEquipmentRealtimeData(equipmentRealtimeData);
        if (equipmentRealtimeData1.size() > 0) {
            return WebResponse.error(400, "设备存在实时值,删除失败");
        }
        //删除
        equipmentInfoDao.deleteEquipmentInfo(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse showEquipmentInfoByCondition(ShowEquipmentInfoByConditionQo showEquipmentInfoByConditionQo) {
        String equipmentPropertyCode = showEquipmentInfoByConditionQo.getEquipmentPropertyCode();
        String equipmentPropertyTemplateCode = showEquipmentInfoByConditionQo.getEquipmentPropertyTemplateCode();
        String equipmentType = showEquipmentInfoByConditionQo.getEquipmentType();
        String parentNodeCode = showEquipmentInfoByConditionQo.getParentNodeCode();
        EquipmentInfo EquipmentInfo = new EquipmentInfo();
        EquipmentInfo.setEquipmentPropertyTemplateCode(equipmentPropertyTemplateCode == "" ? null : equipmentPropertyTemplateCode);
        EquipmentInfo.setEquipmentPropertyCode(equipmentPropertyCode == "" ? null : equipmentPropertyCode);
        EquipmentInfo.setEquipmentType(equipmentType == "" ? null : equipmentType);
        EquipmentInfo.setParentNodeCode(parentNodeCode == "" ? null : parentNodeCode);
        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
        List<ShowAllEquipmentInfoVo> showAllEquipmentInfoVos = new ArrayList<>();
        if (EquipmentInfos.size() > 0) {
            for (EquipmentInfo EquipmentInfo2 : EquipmentInfos) {
                ShowAllEquipmentInfoVo showAllEquipmentInfoVo = new ShowAllEquipmentInfoVo();
                showAllEquipmentInfoVo.setEquipmentCode(EquipmentInfo2.getEquipmentCode());
                showAllEquipmentInfoVo.setEquipmentName(EquipmentInfo2.getEquipmentName());
                showAllEquipmentInfoVo.setEquipmentPropertyCode(EquipmentInfo2.getEquipmentPropertyCode());
                showAllEquipmentInfoVo.setEquipmentPropertyTemplateCode(EquipmentInfo2.getEquipmentPropertyTemplateCode());
                showAllEquipmentInfoVo.setEquipmentType(EquipmentInfo2.getEquipmentType());
                showAllEquipmentInfoVo.setKeyword(EquipmentInfo2.getKeyword());
                showAllEquipmentInfoVo.setParentNodeCode(EquipmentInfo2.getParentNodeCode());
                showAllEquipmentInfoVos.add(showAllEquipmentInfoVo);
            }
        }
        return WebResponse.success(showAllEquipmentInfoVos);
    }

    @Override
    public WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo) {
        String dataValue = sendEquipmentRealtimeDataQo.getDataValue();
        String keyword = sendEquipmentRealtimeDataQo.getKeyword();
        //对key值对应的设备表进行更新
        EquipmentInfo EquipmentInfo = new EquipmentInfo();
        EquipmentInfo.setKeyword(keyword);
        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
        if (EquipmentInfos.size() > 0) {
            EquipmentInfo EquipmentInfo1 = EquipmentInfos.get(0);
            EquipmentInfo1.setDataVal(dataValue);
            EquipmentInfo1.setDataUpdate(new Date());
            equipmentInfoDao.updateEquipmentInfo(EquipmentInfo1);
        }
        //todo:发送实时数据
        return WebResponse.success();
    }

    @Override
    public WebResponse sendEventHistory(SendEventHistoryQo sendEventHistoryQo) {
        String eventCode = sendEventHistoryQo.getEventCode();
        String eventType = sendEventHistoryQo.getEventType();
        String eventValue = sendEventHistoryQo.getEventValue();
        String parentNodeCode = sendEventHistoryQo.getParentNodeCode();

        String keyword = sendEventHistoryQo.getKeyword();
        EquipmentInfo EquipmentInfo = new EquipmentInfo();
        String alarmVal = "事件类型:" + eventType + ",事件码:" + eventCode + ",事件值:" + eventValue;
        EquipmentInfo.setKeyword(keyword);
        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
        if (EquipmentInfos.size() > 0) {
            EquipmentInfo EquipmentInfo1 = EquipmentInfos.get(0);
            EquipmentInfo1.setAlarmVal(alarmVal);
            EquipmentInfo1.setAlarmUpdate(new Date());
            equipmentInfoDao.updateEquipmentInfo(EquipmentInfo1);
        }

        //event_history==>Message==>ByteBuf==>发送
        //todo:发送事件
//        ByteBuf msgBuf = Unpooled.buffer();
//        Worker.send(msgBuf);
        return WebResponse.success();
    }

    @Override
    public WebResponse dealEventHistory(DealEventHistoryQo dealEvent_historyQo) {
        String keyword = dealEvent_historyQo.getKeyword();
        EquipmentInfo EquipmentInfo = new EquipmentInfo();
        EquipmentInfo.setKeyword(keyword);
        List<EquipmentInfo> EquipmentInfos = equipmentInfoDao.selectByEquipmentInfo(EquipmentInfo);
        if (EquipmentInfos.size() > 0) {
            EquipmentInfo EquipmentInfo1 = EquipmentInfos.get(0);
            EquipmentInfo1.setAlarmVal("");
            EquipmentInfo1.setAlarmUpdate(new Date());
            equipmentInfoDao.updateEquipmentInfo(EquipmentInfo1);
            //todo:发送消息
        }
        return WebResponse.success();
    }
}
