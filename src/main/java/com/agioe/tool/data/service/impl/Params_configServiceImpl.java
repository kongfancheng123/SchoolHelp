package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.UpdateOrAddParams_configQo;
import com.agioe.tool.data.dao.Params_configDao;
import com.agioe.tool.data.entity.Params_config;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Params_configService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Params_configServiceImpl implements Params_configService {
    @Autowired
    private Params_configDao params_configDao;

    @Override
    public Integer insertParams_config(Params_config params_config) {
        return params_configDao.insertParams_config(params_config);
    }

    @Override
    public Integer updateParams_config(Params_config params_config) {
        return params_configDao.updateParams_config(params_config);
    }

    @Override
    public List<Params_config> selectAll() {
        return params_configDao.selectAll();
    }

    @Override
    public WebResponse updateOrAddParams_config(UpdateOrAddParams_configQo updateOrAddParams_configQo) {
        Integer alarm_enable = updateOrAddParams_configQo.getAlarm_enable();
        Integer data_enable = updateOrAddParams_configQo.getData_enable();
        Integer feed_cycle = updateOrAddParams_configQo.getFeed_cycle();
        String ip = updateOrAddParams_configQo.getIp();
        Integer log_level = updateOrAddParams_configQo.getLog_level();
        String password = updateOrAddParams_configQo.getPassword();
        String port = updateOrAddParams_configQo.getPort();
        String version = updateOrAddParams_configQo.getVersion();
        Params_config params_config = new Params_config();
        params_config.setVersion(version);
        params_config.setPort(port);
        params_config.setPassword(password);
        params_config.setLog_level(log_level);
        params_config.setIp(ip);
        params_config.setFeed_cycle(feed_cycle);
        params_config.setData_enable(data_enable);
        params_config.setCreate_date(new Date());
        params_config.setAlarm_enable(alarm_enable);
        //判断数据库是否有数据
        List<Params_config> params_configs = params_configDao.selectAll();
        if (params_configs.size() > 0) {//更新
            Params_config params_config1 = params_configs.get(0);
            params_config1.setAlarm_enable(alarm_enable);
            params_config1.setCreate_date(new Date());
            params_config1.setData_enable(data_enable);
            params_config1.setFeed_cycle(feed_cycle);
            params_config1.setIp(ip);
            params_config1.setLog_level(log_level);
            params_config1.setPassword(password);
            params_config1.setPort(port);
            params_config1.setVersion(version);
            params_configDao.updateParams_config(params_config1);
        } else {//新增
            params_configDao.insertParams_config(params_config);
        }
        return WebResponse.success();
    }
}
