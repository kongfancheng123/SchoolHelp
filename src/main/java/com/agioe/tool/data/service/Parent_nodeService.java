package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.AddParent_node1Qo;
import com.agioe.tool.data.Qo.DeleteParent_node1Qo;
import com.agioe.tool.data.Qo.UpdateParent_node1Qo;
import com.agioe.tool.data.entity.Parent_node;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface Parent_nodeService {
    /**
     * 新增监控信息
     *
     * @param parent_node
     * @return
     */
    Integer insertParent_node(Parent_node parent_node);

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
    List<Parent_node> selectByParent_node(Parent_node parent_node);

    /**
     * 更新监控信息
     *
     * @param parent_node
     * @return
     */
    Integer updateParent_node(Parent_node parent_node);

    /**
     * 查找所有监控信息
     */
    List<Parent_node> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    Parent_node selectByid(Integer id);

    /**
     * 展示所有上层节点
     *
     * @return
     */
    WebResponse showAllParent_node();

    /**
     * 新增加上层节点
     *
     * @param addParent_node1Qo
     * @return
     */
    WebResponse addParent_node1(AddParent_node1Qo addParent_node1Qo);

    /**
     * 更新上层节点
     *
     * @param updateParent_node1Qo
     * @return
     */
    WebResponse updateParent_node1(UpdateParent_node1Qo updateParent_node1Qo);

    /**
     * 删除上层节点
     */
    WebResponse deleteParent_node1(DeleteParent_node1Qo deleteParent_node1Qo);
}
