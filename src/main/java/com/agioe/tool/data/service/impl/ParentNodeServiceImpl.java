package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddParentNode1Qo;
import com.agioe.tool.data.Qo.DeleteParentNode1Qo;
import com.agioe.tool.data.Qo.UpdateParentNode1Qo;
import com.agioe.tool.data.Vo.ShowAllParentNodeVo;
import com.agioe.tool.data.dao.ParentNodeDao;
import com.agioe.tool.data.entity.CreateTableParam;
import com.agioe.tool.data.entity.EquipmentInfo;
import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.EquipmentInfoService;
import com.agioe.tool.data.service.ParentNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentNodeServiceImpl implements ParentNodeService {
    @Autowired
    private ParentNodeDao parentNodeDao;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    @Override
    public Integer insertParentNode(ParentNode parentNode) {
        return parentNodeDao.insertParentNode(parentNode);
    }

    @Override
    public Integer deleteParentNode(String parentNodeCode) {
        return parentNodeDao.deleteParentNode(parentNodeCode);
    }

    @Override
    public List<ParentNode> selectByParentNode(ParentNode parentNode) {
        return parentNodeDao.selectByParentNode(parentNode);
    }

    @Override
    public Integer updateParentNode(ParentNode parentNode) {
        return parentNodeDao.updateParentNode(parentNode);
    }

    @Override
    public List<ParentNode> selectAll() {
        return parentNodeDao.selectAll();
    }

    @Override
    public ParentNode selectByid(Integer id) {
        return parentNodeDao.selectByid(id);
    }

    @Override
    public WebResponse showAllParentNode() {
        List<ShowAllParentNodeVo> showAllParentNodeVos = new ArrayList<>();
        List<ParentNode> parentNodes = parentNodeDao.selectAll();
        if (parentNodes.size() > 0) {
            for (ParentNode parentNode : parentNodes) {
                ShowAllParentNodeVo showAllParentNodeVo = new ShowAllParentNodeVo();
                showAllParentNodeVo.setParentNodeCode(parentNode.getParentNodeCode());
                showAllParentNodeVo.setParentNodeName(parentNode.getParentNodeName());
                showAllParentNodeVos.add(showAllParentNodeVo);
            }
        }
        return WebResponse.success(showAllParentNodeVos);
    }

    @Override
    public WebResponse addParentNode1(AddParentNode1Qo addParentNode1Qo) {
        String parentNodeCode = addParentNode1Qo.getParentNodeCode();
        String parentNodeName = addParentNode1Qo.getParentNodeName();
        //编码判重
        ParentNode parentNode = new ParentNode();
        parentNode.setParentNodeCode(parentNodeCode);
        List<ParentNode> parentNodes = parentNodeDao.selectByParentNode(parentNode);
        if (parentNodes.size() > 0) {
            return WebResponse.error(400, "节点编码已存在");
        }
        //名字判重
        ParentNode parentNode1 = new ParentNode();
        parentNode1.setParentNodeName(parentNodeName);
        List<ParentNode> parentNodes1 = parentNodeDao.selectByParentNode(parentNode1);
        if (parentNodes1.size() > 0) {
            return WebResponse.error(400, "节点名字已存在");
        }
        //添加
        ParentNode parentNode2 = new ParentNode();
        parentNode2.setParentNodeName(parentNodeName);
        parentNode2.setParentNodeCode(parentNodeCode);
        parentNodeDao.insertParentNode(parentNode2);
        //建立对应的设备表
        String tableName = "dt_" + parentNodeCode + "_equipment_info";
        String seqName = "seq_" + tableName;
        CreateTableParam createTableParam = new CreateTableParam();
        createTableParam.setSeqName(seqName);
        createTableParam.setTableName(tableName);
        createTableParam.setOriginalTableName("dt_equipment_info");
        equipmentInfoService.createTable(createTableParam);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateParentNode1(UpdateParentNode1Qo updateParentNode1Qo) {
        String parentNodeCode = updateParentNode1Qo.getParentNodeCode();
        String parentNodeName = updateParentNode1Qo.getParentNodeName();
        ParentNode parentNode = new ParentNode();
        parentNode.setParentNodeCode(parentNodeCode);
        List<ParentNode> parentNodes = parentNodeDao.selectByParentNode(parentNode);
        if (parentNodes.size() > 0) {
            ParentNode parentNode1 = parentNodes.get(0);
            //名字判断
            if (parentNode1.getParentNodeName().equals(parentNodeName)) {
                return WebResponse.success();
            } else {
                //名字判重
                ParentNode parentNode2 = new ParentNode();
                parentNode2.setParentNodeName(parentNodeName);
                List<ParentNode> parentNodes1 = parentNodeDao.selectByParentNode(parentNode2);
                if (parentNodes1.size() > 0) {
                    return WebResponse.error(400, "上层节点名称已存在");
                } else {
                    //更新
                    parentNode1.setParentNodeName(parentNodeName);
                    parentNodeDao.updateParentNode(parentNode1);
                }
            }
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteParentNode1(DeleteParentNode1Qo deleteParentNode1Qo) {

        String parentNodeCode = deleteParentNode1Qo.getParentNodeCode();
        //判断是否和设备有绑定关系
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setParentNodeCode(parentNodeCode);
        List<EquipmentInfo> equipmentInfos = equipmentInfoService.selectByEquipmentInfo(equipmentInfo);
        if (equipmentInfos.size() > 0) {
            return WebResponse.error(400, "与设备存在绑定关系,删除失败");
        }
        //删除
        parentNodeDao.deleteParentNode(parentNodeCode);
        //todo:删除数据库
        ParentNode parentNode = new ParentNode();
        parentNode.setParentNodeCode(parentNodeCode);
        parentNodeDao.deleteTable(parentNode);
        return WebResponse.success();
    }
}
