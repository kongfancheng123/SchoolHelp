package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.MonitorPropertyTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorPropertyTemplateDao {
    /**
     * 新增监控信息模板
     *
     * @param monitorPropertyTemplate
     * @return
     */
    Integer insertMonitorPropertyTemplate(@Param("monitorPropertyTemplate") MonitorPropertyTemplate monitorPropertyTemplate);

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
    List<MonitorPropertyTemplate> selectByMonitorPropertyTemplate(@Param("monitorPropertyTemplate") MonitorPropertyTemplate monitorPropertyTemplate);

    /**
     * 更新监控信息模板
     *
     * @param monitorPropertyTemplate
     * @return
     */
    Integer updateMonitorPropertyTemplate(@Param("monitorPropertyTemplate") MonitorPropertyTemplate monitorPropertyTemplate);

    /**
     * 查找所有监控信息模板
     */
    List<MonitorPropertyTemplate> selectAll();

    /**
     * 根据id进行查找监控信息模板
     */
    MonitorPropertyTemplate selectByid(Integer id);
}
