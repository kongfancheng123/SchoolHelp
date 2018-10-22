package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.SendControl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SendControlDao {
    List<SendControl> selectOne(String ip);

    Integer updateSendControl(@Param("sendControl") SendControl sendControl);

    Integer insertSendControl(@Param("sendControl") SendControl sendControl);
}
