package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.SendControl;
import com.agioe.tool.data.entity.SendDataControl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/10/31.
 */
@Repository
public interface SendDataControlDao {
    /**
     * 新增控制信息
     *
     * @param sendDataControl
     * @return
     */
    Integer insertSendDataControl(@Param("sendDataControl") SendDataControl sendDataControl);

    /**
     * 多条件查询监控信息
     *
     * @param sendDataControl
     * @return
     */
    List<SendDataControl> selectBySendDataControl(@Param("sendDataControl") SendDataControl sendDataControl);

    /**
     * 更新控制信息
     * @param sendDataControl
     * @return
     */
    Integer updateSendDataControl(@Param("sendDataControl") SendDataControl sendDataControl);
}
