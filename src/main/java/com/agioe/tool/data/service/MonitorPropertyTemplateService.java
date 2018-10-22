package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.MonitorPropertyTemplate;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface MonitorPropertyTemplateService {
    /**
     * 新增监控信息模板
     *
     * @param monitorPropertyTemplate
     * @return
     */
    Integer insertMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate);

    /**
     * 删除监控信息模板
     *
     * @param equipmentPropertyTemplateCode
     * @return
     */
    Integer deleteMonitorPropertyTemplate(String equipmentPropertyTemplateCode);

    /**
     * 多条件查询监控信息模板
     *
     * @param monitorPropertyTemplate
     * @return
     */
    List<MonitorPropertyTemplate> selectByMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate);

    /**
     * 更新监控信息模板
     *
     * @param monitorPropertyTemplate
     * @return
     */
    Integer updateMonitorPropertyTemplate(MonitorPropertyTemplate monitorPropertyTemplate);

    /**
     * 查找所有监控信息模板
     */
    List<MonitorPropertyTemplate> selectAll();

    /**
     * 根据id进行查找监控信息模板
     */
    MonitorPropertyTemplate selectByid(Integer id);

    /**
     * 展示所有监控模板
     *
     * @return
     */
    WebResponse showAllMonitorPropertyTemplate();

    /**
     * 分页展示监控模板
     *
     * @param pageQo
     * @return
     */
    WebResponse showPageMonitorPropertyTemplate(PageQo pageQo);

    /**
     * 新增加监控模板
     *
     * @param addMonitorPropertyTemplate1Qo
     * @return
     */
    WebResponse addMonitorPropertyTemplate1(AddMonitorPropertyTemplate1Qo addMonitorPropertyTemplate1Qo);

    /**
     * 更新监控模板
     *
     * @param updateMonitorPropertyTemplate1Qo
     * @return
     */
    WebResponse updateMonitorPropertyTemplate1(UpdateMonitorPropertyTemplate1Qo updateMonitorPropertyTemplate1Qo);

    /**
     * 删除监控模板
     *
     * @param deleteMonitorPropertyTemplate1Qo
     * @return
     */
    WebResponse deleteMonitorPropertyTemplate1(DeleteMonitorPropertyTemplate1Qo deleteMonitorPropertyTemplate1Qo);

    /**
     * 导出监控信号模板表
     *
     * @param
     * @return
     * @throws Exception
     */
    WebResponse exportExcelMonitorPropertyTemplate() throws Exception;

    /**
     * 导入监控信号模板表
     *
     * @param importExcelMonitorPropertyTemplateQo
     * @return
     * @throws Exception
     */
    WebResponse importExcelMonitorPropertyTemplate(ImportExcelMonitorPropertyTemplateQo importExcelMonitorPropertyTemplateQo) throws Exception;
}
