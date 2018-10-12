package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddMonitorProperty1Qo;
import com.agioe.tool.data.Qo.DeleteMonitorProperty1Qo;
import com.agioe.tool.data.Qo.GetPropertyByTypeAndTemplateQo;
import com.agioe.tool.data.Qo.UpdateMonitorProperty1Qo;
import com.agioe.tool.data.Vo.GetPropertyByTypeAndTemplateVo;
import com.agioe.tool.data.entity.MonitorProperty;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface MonitorPropertyService {
    /**
     * 新增监控信息
     *
     * @param monitorProperty
     * @return
     */
    Integer insertMonitorProperty(MonitorProperty monitorProperty);

    /**
     * 删除监控信息
     *
     * @param equipmentPropertyCode
     * @return
     */
    Integer deleteMonitorProperty(String equipmentPropertyCode);

    /**
     * 多条件查询监控信息
     *
     * @param monitorProperty
     * @return
     */
    List<MonitorProperty> selectByMonitorProperty(MonitorProperty monitorProperty);

    /**
     * 更新监控信息
     *
     * @param monitorProperty
     * @return
     */
    Integer updateMonitorProperty(MonitorProperty monitorProperty);

    /**
     * 查找所有监控信息
     */
    List<MonitorProperty> selectAll();

    /**
     * 根据模板编码和设备类型查找属性
     *
     * @param getPropertyByTypeAndTemplateQo
     * @return
     */
    List<GetPropertyByTypeAndTemplateVo> getPropertyByTypeAndTemplate(GetPropertyByTypeAndTemplateQo getPropertyByTypeAndTemplateQo);

    /**
     * 根据id进行查找监控信息
     */
    MonitorProperty selectByid(Integer id);

    /**
     * 展示所有监控信息
     *
     * @return
     */
    WebResponse showAllMonitorProperty();

    /**
     * 新增加监控信息
     *
     * @param addMonitorProperty1Qo
     * @return
     */
    WebResponse addMonitorProperty1(AddMonitorProperty1Qo addMonitorProperty1Qo);

    /**
     * 更新监控信息
     *
     * @param updateMonitorProperty1Qo
     * @return
     */
    WebResponse updateMonitorProperty1(UpdateMonitorProperty1Qo updateMonitorProperty1Qo);

    /**
     * 删除监控信息
     */
    WebResponse deleteMonitorProperty1(DeleteMonitorProperty1Qo deleteMonitorProperty1Qo);

}
