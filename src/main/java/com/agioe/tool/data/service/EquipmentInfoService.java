package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.CreateTableParam;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.WebResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EquipmentInfoService {
    /**
     * 新增设备信息
     *
     * @param equipmentInfo
     * @return
     */
    Integer insertEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 删除设备信息
     *
     * @param equipmentInfo
     * @return
     */
    Integer deleteEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 多条件查询设备信息
     *
     * @param equipmentInfo
     * @return
     */
    List<EquipmentInfo> selectByEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 更新设备信息
     *
     * @param equipmentInfo
     * @return
     */
    Integer updateEquipmentInfo(EquipmentInfo equipmentInfo);

    /**
     * 查找所有设备信息
     */
    List<EquipmentInfo> selectAll(String parentNodeCode);

    /**
     * 根据id进行查找设备信息
     */
    EquipmentInfo selectByid(Integer id);

    /**
     * 建表
     */
    Integer createTable(CreateTableParam createTableParam);
    /**
     * 展示设备
     *
     * @return
     */
    WebResponse showAllEquipmentInfo();

    /**
     * 新增设备
     *
     * @param addEquipmentInfo1Qo
     * @return
     */
    WebResponse addEquipmentInfo1(AddEquipmentInfo1Qo addEquipmentInfo1Qo);

    /**
     * 更新设备
     *
     * @param updateEquipmentInfo1Qo
     * @return
     */
    WebResponse updateEquipmentInfo1(UpdateEquipmentInfo1Qo updateEquipmentInfo1Qo);

    /**
     * 删除设备
     */
    WebResponse deleteEquipmentInfo1(DeleteEquipmentInfo1Qo deleteEquipmentInfo1Qo);

    /**
     * 条件查询设备
     *
     * @param showEquipmentInfoByConditionQo
     * @return
     */
    WebResponse showEquipmentInfoByCondition(ShowEquipmentInfoByConditionQo showEquipmentInfoByConditionQo);

    /**
     * 发送实时数据
     *
     * @param sendEquipmentRealtimeDataQo
     * @return
     */
    WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo);

    /**
     * 发送历史事件
     *
     * @param sendEventHistoryQo
     * @return
     */
    WebResponse sendEventHistory(SendEventHistoryQo sendEventHistoryQo);

    /**
     * 解除事件
     *
     * @param dealEventHistoryQo
     * @return
     */
    WebResponse dealEventHistory(DealEventHistoryQo dealEventHistoryQo);

    /**
     * 根据模板和设备类型获取属性
     *
     * @param getPropertyByTypeAndTemplateQo
     * @return
     */
    WebResponse getPropertyByTypeAndTemplate(@RequestBody GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo);

    /**
     * 根据模板,设备类型和上层节点获取属性
     *
     * @param getPropertyByTypeAndTemplateAndParentNodeQo
     * @return
     */
    WebResponse getPropertyByTypeAndTemplateAndParentNode(GetPropertyByTypeAndTemplateAndParentNodeQo getPropertyByTypeAndTemplateAndParentNodeQo);

}
