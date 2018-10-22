package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
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
     * 分页展示信号
     *
     * @param pageQo
     * @return
     */
    WebResponse showPageMonitorProperty(PageQo pageQo);

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

    /**
     * 导出监控属性表
     *
     * @param
     * @return
     */
    WebResponse exportExcelMonitorProperty() throws Exception;

    /**
     * 导入监控属性表
     *
     * @param importExcelMonitorPropertyQo
     * @return
     * @throws Exception
     */
    WebResponse importExcelMonitorProperty(ImportExcelMonitorPropertyQo importExcelMonitorPropertyQo) throws Exception;

}
