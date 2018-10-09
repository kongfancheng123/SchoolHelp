package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Monitor_property_template_bind;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Monitor_property_template_bindDao {
    /**
     * 新增信息模板关联
     *
     * @param monitor_property_template_bind
     * @return
     */
    Integer insertMonitor_property_template_bind(@Param("monitor_property_template_bind") Monitor_property_template_bind monitor_property_template_bind);

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
    List<Monitor_property_template_bind> selectByMonitor_property_template_bind(@Param("monitor_property_template_bind") Monitor_property_template_bind monitor_property_template_bind);

    /**
     * 更新信息模板关联
     *
     * @param monitor_property_template_bind
     * @return
     */
    Integer updateMonitor_property_template_bind(@Param("monitor_property_template_bind") Monitor_property_template_bind monitor_property_template_bind);

    /**
     * 查找所有信息模板关联
     */
    List<Monitor_property_template_bind> selectAll();

    /**
     * 根据id进行查找信息模板关联
     */
    Monitor_property_template_bind selectByid(Integer id);
}
