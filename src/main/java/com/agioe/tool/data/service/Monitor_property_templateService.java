package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property_template1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property_template1Qo;
import com.agioe.tool.data.entity.Monitor_property_template;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Monitor_property_templateService {
    /**
     * 新增监控信息模板
     *
     * @param monitor_property_template
     * @return
     */
    Integer insertMonitor_property_template(Monitor_property_template monitor_property_template);

    /**
     * 删除监控信息模板
     *
     * @param id
     * @return
     */
    Integer deleteMonitor_property_template(Integer id);

    /**
     * 多条件查询监控信息模板
     *
     * @param monitor_property_template
     * @return
     */
    List<Monitor_property_template> selectByMonitor_property_template(Monitor_property_template monitor_property_template);

    /**
     * 更新监控信息模板
     *
     * @param monitor_property_template
     * @return
     */
    Integer updateMonitor_property_template(Monitor_property_template monitor_property_template);

    /**
     * 查找所有监控信息模板
     */
    List<Monitor_property_template> selectAll();

    /**
     * 根据id进行查找监控信息模板
     */
    Monitor_property_template selectByid(Integer id);

    /**
     * 展示所有监控模板
     *
     * @return
     */
    WebResponse showAllMonitor_property_template();

    /**
     * 新增加监控模板
     *
     * @param addMonitor_property_template1Qo
     * @return
     */
    WebResponse addMonitor_property_template1(AddMonitor_property_template1Qo addMonitor_property_template1Qo);

    /**
     * 更新监控模板
     *
     * @param updateMonitor_property_template1Qo
     * @return
     */
    WebResponse updateMonitor_property_template1(UpdateMonitor_property_template1Qo updateMonitor_property_template1Qo);

    /**
     * 删除监控模板
     *
     * @param deleteMonitor_property_template1Qo
     * @return
     */
    WebResponse deleteMonitor_property_template1(DeleteMonitor_property_template1Qo deleteMonitor_property_template1Qo);
}
