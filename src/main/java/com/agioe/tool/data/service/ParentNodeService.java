package com.agioe.tool.data.service;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.WebResponse;

import java.util.List;

public interface ParentNodeService {
    /**
     * 新增监控信息
     *
     * @param parentNode
     * @return
     */
    Integer insertParentNode(ParentNode parentNode);

    /**
     * 删除监控信息
     *
     * @param ParentNodeCode
     * @return
     */
    Integer deleteParentNode(String ParentNodeCode);

    /**
     * 多条件查询监控信息
     *
     * @param parentNode
     * @return
     */
    List<ParentNode> selectByParentNode(ParentNode parentNode);

    /**
     * 更新监控信息
     *
     * @param parentNode
     * @return
     */
    Integer updateParentNode(ParentNode parentNode);

    /**
     * 查找所有监控信息
     */
    List<ParentNode> selectAll();

    /**
     * 根据id进行查找监控信息
     */
    ParentNode selectByid(Integer id);

    /**
     * 展示所有上层节点
     *
     * @return
     */
    WebResponse showAllParentNode();

    /**
     * 分页展示上层节点
     *
     * @param pageQo
     * @return
     */
    WebResponse showPageParentNode(PageQo pageQo);

    /**
     * 新增加上层节点
     *
     * @param addParentNode1Qo
     * @return
     */
    WebResponse addParentNode1(AddParentNode1Qo addParentNode1Qo);

    /**
     * 更新上层节点
     *
     * @param updateParentNode1Qo
     * @return
     */
    WebResponse updateParentNode1(UpdateParentNode1Qo updateParentNode1Qo);

    /**
     * 删除上层节点
     */
    WebResponse deleteParentNode1(DeleteParentNode1Qo deleteParentNode1Qo);

    /**
     * 导出上层节点表
     *
     * @param
     * @return
     * @throws Exception
     */
    WebResponse exportExcelParentNode() throws Exception;

    /**
     * 导入上层节点表
     *
     * @param importExcelParentNodeQo
     * @return
     * @throws Exception
     */
    WebResponse importExcelParentNode(ImportExcelParentNodeQo importExcelParentNodeQo) throws Exception;
}
