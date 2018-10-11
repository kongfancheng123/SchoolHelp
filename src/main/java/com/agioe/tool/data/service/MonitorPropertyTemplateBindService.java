package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitorPropertyTemplateBind1Qo;
import com.agioe.tool.data.entity.MonitorPropertyTemplateBind;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface MonitorPropertyTemplateBindService {
    /**
     * 新增信息模板关联
     *
     * @param monitorPropertyTemplateBind
     * @return
     */
    Integer insertMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind);

    /**
     * 删除信息模板关联
     *
     * @param id
     * @return
     */
    Integer deleteMonitorPropertyTemplateBind(Integer id);

    /**
     * 多条件查询信息模板关联
     *
     * @param monitorPropertyTemplateBind
     * @return
     */
    List<MonitorPropertyTemplateBind> selectByMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind);

    /**
     * 更新信息模板关联
     *
     * @param monitorPropertyTemplateBind
     * @return
     */
    Integer updateMonitorPropertyTemplateBind(MonitorPropertyTemplateBind monitorPropertyTemplateBind);

    /**
     * 查找所有信息模板关联
     */
    List<MonitorPropertyTemplateBind> selectAll();

    /**
     * 根据id进行查找信息模板关联
     */
    MonitorPropertyTemplateBind selectByid(Integer id);

    /**
     * 展示所有信息模板关联
     *
     * @return
     */
    WebResponse showAllMonitorPropertyTemplateBind();

    /**
     * 新增加信息模板关联
     *
     * @param addMonitorPropertyTemplateBind1Qo
     * @return
     */
    WebResponse addMonitorPropertyTemplateBind1(AddMonitorPropertyTemplateBind1Qo addMonitorPropertyTemplateBind1Qo);

    /**
     * 更新信息模板关联
     *
     * @param updateMonitorPropertyTemplateBind1Qo
     * @return
     */
    WebResponse updateMonitorPropertyTemplateBind1(UpdateMonitorPropertyTemplateBind1Qo updateMonitorPropertyTemplateBind1Qo);

    /**
     * 删除信息模板关联
     *
     * @param deleteMonitorPropertyTemplateBind1Qo
     * @return
     */
    WebResponse deleteMonitorPropertyTemplateBind1(DeleteMonitorPropertyTemplateBind1Qo deleteMonitorPropertyTemplateBind1Qo);

    /**
     * 获取设备类型属性模板树
     */
    WebResponse getEquipmentTypeTemplateLink();
}
