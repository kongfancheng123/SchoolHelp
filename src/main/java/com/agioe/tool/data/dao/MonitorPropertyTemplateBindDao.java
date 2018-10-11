package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.MonitorPropertyTemplateBind;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorPropertyTemplateBindDao {
    /**
     * 新增信息模板关联
     *
     * @param monitorPropertyTemplateBind
     * @return
     */
    Integer insertMonitorPropertyTemplateBind(@Param("monitorPropertyTemplateBind") MonitorPropertyTemplateBind monitorPropertyTemplateBind);

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
    List<MonitorPropertyTemplateBind> selectByMonitorPropertyTemplateBind(@Param("monitorPropertyTemplateBind") MonitorPropertyTemplateBind monitorPropertyTemplateBind);

    /**
     * 更新信息模板关联
     *
     * @param monitorPropertyTemplateBind
     * @return
     */
    Integer updateMonitorPropertyTemplateBind(@Param("monitorPropertyTemplateBind") MonitorPropertyTemplateBind monitorPropertyTemplateBind);

    /**
     * 查找所有信息模板关联
     */
    List<MonitorPropertyTemplateBind> selectAll();

    /**
     * 根据id进行查找信息模板关联
     */
    MonitorPropertyTemplateBind selectByid(Integer id);
}
