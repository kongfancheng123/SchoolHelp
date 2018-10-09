package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Monitor_property_template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Monitor_property_templateDao {
    /**
     * 新增监控信息模板
     *
     * @param monitor_property_template
     * @return
     */
    Integer insertMonitor_property_template(@Param("monitor_property_template") Monitor_property_template monitor_property_template);

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
    List<Monitor_property_template> selectByMonitor_property_template(@Param("monitor_property_template") Monitor_property_template monitor_property_template);

    /**
     * 更新监控信息模板
     *
     * @param monitor_property_template
     * @return
     */
    Integer updateMonitor_property_template(@Param("monitor_property_template") Monitor_property_template monitor_property_template);

    /**
     * 查找所有监控信息模板
     */
    List<Monitor_property_template> selectAll();

    /**
     * 根据id进行查找监控信息模板
     */
    Monitor_property_template selectByid(Integer id);
}
