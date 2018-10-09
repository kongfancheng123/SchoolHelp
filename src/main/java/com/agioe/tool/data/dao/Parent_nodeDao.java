package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.Parent_node;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Parent_nodeDao {
    /**
     * 新增监控信息
     *
     * @param parent_node
     * @return
     */
    Integer insertParent_node(@Param("parent_node") Parent_node parent_node);

    /**
     * 删除监控信息
     *
     * @param id
     * @return
     */
    Integer deleteParent_node(Integer id);

    /**
     * 多条件查询监控信息
     *
     * @param parent_node
     * @return
     */
    List<Parent_node> selectByParent_node(@Param("parent_node") Parent_node parent_node);

    /**
     * 更新监控信息
     *
     * @param parent_node
     * @return
     */
    Integer updateParent_node(@Param("parent_node") Parent_node parent_node);

    /**
     * 查找所有监控信息
     */
    List<Parent_node> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    Parent_node selectByid(Integer id);
}
