package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.MonitorProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorPropertyDao {
    /**
     * 新增监控信息
     *
     * @param monitorProperty
     * @return
     */
    Integer insertMonitorProperty(@Param("monitorProperty") MonitorProperty monitorProperty);

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
    List<MonitorProperty> selectByMonitorProperty(@Param("monitorProperty") MonitorProperty monitorProperty);

    /**
     * 更新监控信息
     *
     * @param monitorProperty
     * @return
     */
    Integer updateMonitorProperty(@Param("monitorProperty") MonitorProperty monitorProperty);

    /**
     * 查找所有监控信息
     */
    List<MonitorProperty> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    MonitorProperty selectByid(Integer id);
}
