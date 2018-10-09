package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Monitor_property;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Monitor_propertyDao {
    /**
     * 新增监控信息
     *
     * @param monitor_property
     * @return
     */
    Integer insertMonitor_property(@Param("monitor_property") Monitor_property monitor_property);

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
    List<Monitor_property> selectByMonitor_property(@Param("monitor_property") Monitor_property monitor_property);

    /**
     * 更新监控信息
     *
     * @param monitor_property
     * @return
     */
    Integer updateMonitor_property(@Param("monitor_property") Monitor_property monitor_property);

    /**
     * 查找所有监控信息
     */
    List<Monitor_property> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    Monitor_property selectByid(Integer id);
}
