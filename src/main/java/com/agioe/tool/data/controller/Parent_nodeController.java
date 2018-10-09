package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.AddParent_node1Qo;
import com.agioe.tool.data.Qo.DeleteParent_node1Qo;
import com.agioe.tool.data.Qo.UpdateParent_node1Qo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Parent_nodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Parent_node")
public class Parent_nodeController {
    @Autowired
    private Parent_nodeService parent_nodeService;

    /**
     * 展示所有上层节点
     *
     * @return
     */
    @RequestMapping(value = "/showAllParent_node", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllParent_node() {
        return parent_nodeService.showAllParent_node();
    }

    /**
     * 添加上层节点
     *
     * @param addParent_node1Qo
     * @return
     */
    @RequestMapping(value = "/addParent_node1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addParent_node1(@RequestBody AddParent_node1Qo addParent_node1Qo) {
        return parent_nodeService.addParent_node1(addParent_node1Qo);
    }

    /**
     * 更新上层节点
     *
     * @param updateParent_node1Qo
     * @return
     */
    @RequestMapping(value = "/updateParent_node1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateParent_node1(@RequestBody UpdateParent_node1Qo updateParent_node1Qo) {
        return parent_nodeService.updateParent_node1(updateParent_node1Qo);
    }

    /**
     * 删除上层节点
     *
     * @param deleteParent_node1Qo
     * @return
     */
    @RequestMapping(value = "/deleteParent_node1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteParent_node1(@RequestBody DeleteParent_node1Qo deleteParent_node1Qo) {
        return parent_nodeService.deleteParent_node1(deleteParent_node1Qo);
    }


}
