package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template_bind1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template_bind1Qo;
import com.agioe.tool.data.entity.Monitor_property_template_bind;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Monitor_property_template_bindService {
    /**
     * 新增信息模板关联
     *
     * @param monitor_property_template_bind
     * @return
     */
    Integer insertMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind);

    /**
     * 删除信息模板关联
     *
     * @param id
     * @return
     */
    Integer deleteMonitor_property_template_bind(Integer id);

    /**
     * 多条件查询信息模板关联
     *
     * @param monitor_property_template_bind
     * @return
     */
    List<Monitor_property_template_bind> selectByMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind);

    /**
     * 更新信息模板关联
     *
     * @param monitor_property_template_bind
     * @return
     */
    Integer updateMonitor_property_template_bind(Monitor_property_template_bind monitor_property_template_bind);

    /**
     * 查找所有信息模板关联
     */
    List<Monitor_property_template_bind> selectAll();

    /**
     * 根据id进行查找信息模板关联
     */
    Monitor_property_template_bind selectByid(Integer id);

    /**
     * 展示所有信息模板关联
     *
     * @return
     */
    WebResponse showAllMonitor_property_template_bind();

    /**
     * 新增加信息模板关联
     *
     * @param addMonitor_property_template_bind1Qo
     * @return
     */
    WebResponse addMonitor_property_template_bind1(AddMonitor_property_template_bind1Qo addMonitor_property_template_bind1Qo);

    /**
     * 更新信息模板关联
     *
     * @param updateMonitor_property_template_bind1Qo
     * @return
     */
    WebResponse updateMonitor_property_template_bind1(UpdateMonitor_property_template_bind1Qo updateMonitor_property_template_bind1Qo);

    /**
     * 删除信息模板关联
     *
     * @param deleteMonitor_property_template_bind1Qo
     * @return
     */
    WebResponse deleteMonitor_property_template_bind1(DeleteMonitor_property_template_bind1Qo deleteMonitor_property_template_bind1Qo);

    /**
     * 获取设备类型属性模板树
     */
    WebResponse getEquipment_type_template_link();
}
