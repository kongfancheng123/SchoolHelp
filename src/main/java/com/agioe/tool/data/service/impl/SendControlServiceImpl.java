package com.agioe.tool.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendControlServiceImpl implements SendControlService {
    @Autowired
    private SendControlDao sendControlDao;

    @Override
    public List<SendControl> selectOne(String ip) {
        return sendControlDao.selectOne(ip);
    }

    @Override
    public Integer updateSendControl(SendControl sendControl) {
        return sendControlDao.updateSendControl(sendControl);
    }

    @Override
    public Integer insertSendControl(SendControl sendControl) {
        return sendControlDao.insertSendControl(sendControl);
    }


}
