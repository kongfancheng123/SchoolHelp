package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.dao.SendDataControlDao;
import com.agioe.tool.data.entity.ParentNode;
import com.agioe.tool.data.entity.SendDataControl;
import com.agioe.tool.data.service.SendDataControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by fchkong on 2018/10/31.
 */
@Service
public class SendDataControlServiceImpl implements SendDataControlService {
    @Autowired
    private SendDataControlDao sendDataControlDao;
    @Override
    public Integer insertSendDataControl(SendDataControl sendDataControl) {
        return sendDataControlDao.insertSendDataControl(sendDataControl);
    }

    @Override
    public List<SendDataControl> selectBySendDataControl(SendDataControl sendDataControl) {
        return sendDataControlDao.selectBySendDataControl(sendDataControl);
    }

    @Override
    public Integer updateSendDataControl(SendDataControl sendDataControl) {
        return sendDataControlDao.updateSendDataControl(sendDataControl);
    }
}
