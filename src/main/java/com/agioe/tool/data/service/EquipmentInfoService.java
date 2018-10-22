package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.CreateTableParam;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.ParentNode;
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
    List<EquipmentInfo> selectAll(ParentNode parentNode);

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
     * 分页展示设备
     *
     * @param pageQo
     * @return
     */
    WebResponse showPageEquipmentInfo(PageQo pageQo);

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
    WebResponse sendEquipmentRealtimeData(SendEquipmentRealtimeDataQo sendEquipmentRealtimeDataQo, String ip) throws Exception;

    /**
     * 停止发送数据
     *
     * @param
     * @return
     */
    WebResponse stopSendEquipmentRealtimeData(String ip);

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

    /**
     * 创建keyword
     *
     * @param createKeyWordQo
     * @return
     */
    WebResponse createKeyWord(@RequestBody CreateKeyWordQo createKeyWordQo);

    /**
     * 导出设备信息表
     *
     * @param
     * @return
     * @throws Exception
     */
    WebResponse exportExcelEquipmentInfo(ExportExcelEquipmentInfoQo exportExcelEquipmentInfoQo) throws Exception;

    /**
     * 导入设备信息表
     *
     * @param importExcelEquipmentInfoQo
     * @return
     * @throws Exception
     */
    WebResponse importExcelEquipmentInfo(ImportExcelEquipmentInfoQo importExcelEquipmentInfoQo) throws Exception;

    /**
     * 获取发送数据控制值
     */
    WebResponse getSendControlVal();

    /**
     * 获取解除事件相关值
     *
     * @param getDealEventHistoryValueQo
     * @return
     */
    WebResponse getDealEventHistoryValue(GetDealEventHistoryValueQo getDealEventHistoryValueQo);

}
