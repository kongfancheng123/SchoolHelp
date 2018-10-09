package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.AddParent_node1Qo;
import com.agioe.tool.data.Qo.DeleteParent_node1Qo;
import com.agioe.tool.data.Qo.UpdateParent_node1Qo;
import com.agioe.tool.data.Vo.ShowAllParent_nodeVo;
import com.agioe.tool.data.dao.Parent_nodeDao;
import com.agioe.tool.data.entity.Equipment_info;
import com.agioe.tool.data.entity.Parent_node;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Equipment_infoService;
import com.agioe.tool.data.service.Parent_nodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Parent_nodeServiceImpl implements Parent_nodeService {
    @Autowired
    private Parent_nodeDao parent_nodeDao;

    @Autowired
    private Equipment_infoService equipment_infoService;

    @Override
    public Integer insertParent_node(Parent_node parent_node) {
        return parent_nodeDao.insertParent_node(parent_node);
    }

    @Override
    public Integer deleteParent_node(Integer id) {
        return parent_nodeDao.deleteParent_node(id);
    }

    @Override
    public List<Parent_node> selectByParent_node(Parent_node parent_node) {
        return parent_nodeDao.selectByParent_node(parent_node);
    }

    @Override
    public Integer updateParent_node(Parent_node parent_node) {
        return parent_nodeDao.updateParent_node(parent_node);
    }

    @Override
    public List<Parent_node> selectAll() {
        return parent_nodeDao.selectAll();
    }

    @Override
    public Parent_node selectByid(Integer id) {
        return parent_nodeDao.selectByid(id);
    }

    @Override
    public WebResponse showAllParent_node() {
        List<ShowAllParent_nodeVo> showAllParent_nodeVos = new ArrayList<>();
        List<Parent_node> parent_nodes = parent_nodeDao.selectAll();
        if (parent_nodes.size() > 0) {
            for (Parent_node parent_node : parent_nodes) {
                ShowAllParent_nodeVo showAllParent_nodeVo = new ShowAllParent_nodeVo();
                showAllParent_nodeVo.setParent_node_code(parent_node.getParent_node_code());
                showAllParent_nodeVo.setParent_node_name(parent_node.getParent_node_name());
                showAllParent_nodeVos.add(showAllParent_nodeVo);
            }
        }
        return WebResponse.success(showAllParent_nodeVos);
    }

    @Override
    public WebResponse addParent_node1(AddParent_node1Qo addParent_node1Qo) {
        String parent_node_code = addParent_node1Qo.getParent_node_code();
        String parent_node_name = addParent_node1Qo.getParent_node_name();
        //编码判重
        Parent_node parent_node = new Parent_node();
        parent_node.setParent_node_code(parent_node_code);
        List<Parent_node> parent_nodes = parent_nodeDao.selectByParent_node(parent_node);
        if (parent_nodes.size() > 0) {
            return WebResponse.error(400, "节点编码已存在");
        }
        //名字判重
        Parent_node parent_node1 = new Parent_node();
        parent_node1.setParent_node_name(parent_node_name);
        List<Parent_node> parent_nodes1 = parent_nodeDao.selectByParent_node(parent_node1);
        if (parent_nodes1.size() > 0) {
            return WebResponse.error(400, "节点名字已存在");
        }
        //添加
        Parent_node parent_node2 = new Parent_node();
        parent_node2.setParent_node_name(parent_node_name);
        parent_node2.setParent_node_code(parent_node_code);
        parent_nodeDao.insertParent_node(parent_node2);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateParent_node1(UpdateParent_node1Qo updateParent_node1Qo) {
        Integer id = updateParent_node1Qo.getId();
        String parent_node_code = updateParent_node1Qo.getParent_node_code();
        String parent_node_name = updateParent_node1Qo.getParent_node_name();
        Parent_node parent_node = parent_nodeDao.selectByid(id);
        parent_node.setParent_node_code(parent_node_code);
        parent_node.setParent_node_name(parent_node_name);
        parent_nodeDao.updateParent_node(parent_node);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteParent_node1(DeleteParent_node1Qo deleteParent_node1Qo) {
        Integer id = deleteParent_node1Qo.getId();
        Parent_node parent_node = parent_nodeDao.selectByid(id);
        String parent_node_code = parent_node.getParent_node_code();
        //判断是否和设备有绑定关系
        Equipment_info equipment_info = new Equipment_info();
        equipment_info.setParent_node_code(parent_node_code);
        List<Equipment_info> equipment_infos = equipment_infoService.selectByEquipment_info(equipment_info);
        if (equipment_infos.size() > 0) {
            return WebResponse.error(400, "与设备存在绑定关系,删除失败");
        }
        //删除
        parent_nodeDao.deleteParent_node(id);
        return WebResponse.success();
    }
}
