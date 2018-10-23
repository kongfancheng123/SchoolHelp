package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.*;
import com.agioe.tool.data.entity.EquipmentType;
import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.excel.ExcelHelperWrite;
import com.agioe.tool.data.service.ParentNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ParentNode")
public class ParentNodeController {
    @Autowired
    private ParentNodeService parentNodeService;

    /**
     * 展示所有上层节点
     *
     * @return
     */
    @RequestMapping(value = "/showAllParentNode", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showAllParentNode() {
        return parentNodeService.showAllParentNode();
    }

    /**
     * 分页展示上层节点
     *
     * @return
     */
    @RequestMapping(value = "/showPageParentNode", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse showPageParentNode(@RequestBody PageQo pageQo) {
        return parentNodeService.showPageParentNode(pageQo);
    }

    /**
     * 添加上层节点
     *
     * @param addParentNode1Qo
     * @return
     */
    @RequestMapping(value = "/addParentNode1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addParentNode1(@RequestBody AddParentNode1Qo addParentNode1Qo) {
        return parentNodeService.addParentNode1(addParentNode1Qo);
    }

    /**
     * 更新上层节点
     *
     * @param updateParentNode1Qo
     * @return
     */
    @RequestMapping(value = "/updateParentNode1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateParentNode1(@RequestBody UpdateParentNode1Qo updateParentNode1Qo) {
        return parentNodeService.updateParentNode1(updateParentNode1Qo);
    }

    /**
     * 删除上层节点
     *
     * @param deleteParentNode1Qo
     * @return
     */
    @RequestMapping(value = "/deleteParentNode1", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse deleteParentNode1(@RequestBody DeleteParentNode1Qo deleteParentNode1Qo) {
        return parentNodeService.deleteParentNode1(deleteParentNode1Qo);
    }

    /**
     * 导出上层节点表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportExcelParentNode", method = RequestMethod.POST)
    @ResponseBody
    public void exportExcelParentNode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ExportExcelParentNodeQo> exportExcelParentNodeQos=new ArrayList<>();
        List<ParentNode> parentNodes = parentNodeService.selectAll();
        if(parentNodes.size()>0){
            int i=1;
            for(ParentNode parentNode:parentNodes){
                ExportExcelParentNodeQo exportExcelParentNodeQo=new ExportExcelParentNodeQo();
                exportExcelParentNodeQo.setOrderNumber(String.valueOf(i));
                exportExcelParentNodeQo.setParentNodeCode(parentNode.getParentNodeCode());
                exportExcelParentNodeQo.setParentNodeName(parentNode.getParentNodeName());
                i++;
                exportExcelParentNodeQos.add(exportExcelParentNodeQo);
            }
        }
        //定义一个 fieldMap ，里面存放的key值要和查询出来的数据字段相对应，里面放的数据也是excel展示的数据
        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderNumber", "序号");
        fieldMap.put("parentNodeCode", "节点编码");
        fieldMap.put("parentNodeName", "节点名称");


        ExcelHelperWrite.listToExcel("上层节点表", exportExcelParentNodeQos, fieldMap, "上层节点表", 30000, response, request);
    }

    /**
     * 导入上层节点表
     *
     * @param importExcelParentNodeQo
     * @return
     */
    @RequestMapping(value = "/importExcelParentNode", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcelParentNode(@RequestBody ImportExcelParentNodeQo importExcelParentNodeQo) throws Exception {
        return parentNodeService.importExcelParentNode(importExcelParentNodeQo);
    }


}
