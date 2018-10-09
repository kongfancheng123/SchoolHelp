package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddMonitor_property1Qo;
import com.agioe.tool.data.Qo.DeleteMonitor_property1Qo;
import com.agioe.tool.data.Qo.UpdateMonitor_property1Qo;
import com.agioe.tool.data.entity.Monitor_property;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Monitor_propertyService {
    /**
     * 新增监控信息
     *
     * @param monitor_property
     * @return
     */
    Integer insertMonitor_property(Monitor_property monitor_property);

    /**
     * 删除监控信息
     *
     * @param id
     * @return
     */
    Integer deleteMonitor_property(Integer id);

    /**
     * 多条件查询监控信息
     *
     * @param monitor_property
     * @return
     */
    List<Monitor_property> selectByMonitor_property(Monitor_property monitor_property);

    /**
     * 更新监控信息
     *
     * @param monitor_property
     * @return
     */
    Integer updateMonitor_property(Monitor_property monitor_property);

    /**
     * 查找所有监控信息
     */
    List<Monitor_property> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    Monitor_property selectByid(Integer id);

    /**
     * 展示所有监控信息
     *
     * @return
     */
    WebResponse showAllMonitor_property();

    /**
     * 新增加监控信息
     *
     * @param addMonitor_property1Qo
     * @return
     */
    WebResponse addMonitor_property1(AddMonitor_property1Qo addMonitor_property1Qo);

    /**
     * 更新监控信息
     *
     * @param updateMonitor_property1Qo
     * @return
     */
    WebResponse updateMonitor_property1(UpdateMonitor_property1Qo updateMonitor_property1Qo);

    /**
     * 删除监控信息
     */
    WebResponse deleteMonitor_property1(DeleteMonitor_property1Qo deleteMonitor_property1Qo);
}
