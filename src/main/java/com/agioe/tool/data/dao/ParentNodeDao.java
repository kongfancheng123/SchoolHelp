package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.ParentNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentNodeDao {
    /**
     * 新增监控信息
     *
     * @param parentNode
     * @return
     */
    Integer insertParentNode(@Param("parentNode") ParentNode parentNode);

    /**
     * 删除监控信息
     *
     * @param parentNodeCode
     * @return
     */
    Integer deleteParentNode(String parentNodeCode);

    /**
     * 多条件查询监控信息
     *
     * @param parentNode
     * @return
     */
    List<ParentNode> selectByParentNode(@Param("parentNode") ParentNode parentNode);

    /**
     * 更新监控信息
     *
     * @param parentNode
     * @return
     */
    Integer updateParentNode(@Param("parentNode") ParentNode parentNode);

    /**
     * 查找所有监控信息
     */
    List<ParentNode> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    ParentNode selectByid(Integer id);
}
