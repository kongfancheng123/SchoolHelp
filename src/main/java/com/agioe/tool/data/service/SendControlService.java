package com.agioe.tool.data.service;

import com.agioe.tool.data.entity.SendControl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendControlService {
    List<SendControl> selectOne(String ip);

    Integer updateSendControl(SendControl sendControl);

    Integer insertSendControl(@Param("sendControl") SendControl sendControl);
}
