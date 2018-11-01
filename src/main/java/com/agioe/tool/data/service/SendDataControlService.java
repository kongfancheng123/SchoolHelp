package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.SendDataControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create by fchkong on 2018/10/31.
 */
public interface SendDataControlService {
    /**
     * 新增控制信息
     *
     * @param sendDataControl
     * @return
     */
    Integer insertSendDataControl(SendDataControl sendDataControl);

    /**
     * 多条件查询监控信息
     *
     * @param sendDataControl
     * @return
     */
    List<SendDataControl> selectBySendDataControl(SendDataControl sendDataControl);

    /**
     * 跟新监控信息
     * @param sendDataControl
     * @return
     */
    Integer updateSendDataControl(SendDataControl sendDataControl);
}
