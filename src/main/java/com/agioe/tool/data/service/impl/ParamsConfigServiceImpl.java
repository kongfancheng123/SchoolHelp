package com.agioe.tool.data.service.impl;

import com.agioe.tool.data.Qo.UpdateOrAddParamsConfigQo;
import com.agioe.tool.data.Vo.GetParamsConfigVo;
import com.agioe.tool.data.dao.ParamsConfigDao;
import com.agioe.tool.data.entity.ParamsConfig;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.ParamsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParamsConfigServiceImpl implements ParamsConfigService {
    @Autowired
    private ParamsConfigDao paramsConfigDao;

    @Override
    public Integer insertParamsConfig(ParamsConfig paramsConfig) {
        return paramsConfigDao.insertParamsConfig(paramsConfig);
    }

    @Override
    public Integer updateParamsConfig(ParamsConfig paramsConfig) {
        return paramsConfigDao.updateParamsConfig(paramsConfig);
    }

    @Override
    public List<ParamsConfig> selectAll() {
        return paramsConfigDao.selectAll();
    }

    @Override
    public WebResponse updateOrAddParamsConfig(UpdateOrAddParamsConfigQo updateOrAddParamsConfigQo) {
        Integer alarmEnable = updateOrAddParamsConfigQo.getAlarmEnable();
        Integer dataEnable = updateOrAddParamsConfigQo.getDataEnable();
        Integer feedCycle = updateOrAddParamsConfigQo.getFeedCycle();
        String ip = updateOrAddParamsConfigQo.getIp();
        Integer logLevel = updateOrAddParamsConfigQo.getLogLevel();
        String password = updateOrAddParamsConfigQo.getPassword();
        String port = updateOrAddParamsConfigQo.getPort();
        String version = updateOrAddParamsConfigQo.getVersion();
        ParamsConfig paramsConfig = new ParamsConfig();
        paramsConfig.setVersion(version);
        paramsConfig.setPort(port);
        paramsConfig.setPassword(password);
        paramsConfig.setLogLevel(logLevel);
        paramsConfig.setIp(ip);
        paramsConfig.setFeedCycle(feedCycle);
        paramsConfig.setDataEnable(dataEnable);
        paramsConfig.setCreateDate(new Date());
        paramsConfig.setAlarmEnable(alarmEnable);
        //判断数据库是否有数据
        List<ParamsConfig> paramsConfigs = paramsConfigDao.selectAll();
        if (paramsConfigs.size() > 0) {//更新
            ParamsConfig paramsConfig1 = paramsConfigs.get(0);
            paramsConfig1.setAlarmEnable(alarmEnable);
            paramsConfig1.setCreateDate(new Date());
            paramsConfig1.setDataEnable(dataEnable);
            paramsConfig1.setFeedCycle(feedCycle);
            paramsConfig1.setIp(ip);
            paramsConfig1.setLogLevel(logLevel);
            paramsConfig1.setPassword(password);
            paramsConfig1.setPort(port);
            paramsConfig1.setVersion(version);
            paramsConfigDao.updateParamsConfig(paramsConfig1);
        } else {//新增
            paramsConfigDao.insertParamsConfig(paramsConfig);
        }
        return WebResponse.success();
    }

    @Override
    public WebResponse getParamsConfig() {
        GetParamsConfigVo getParamsConfigVo = new GetParamsConfigVo();
        List<ParamsConfig> paramsConfigs = paramsConfigDao.selectAll();
        if (paramsConfigs.size() > 0) {
            ParamsConfig paramsConfig = paramsConfigs.get(0);
            getParamsConfigVo.setAlarmEnable(paramsConfig.getAlarmEnable() == null ? 1 : paramsConfig.getAlarmEnable());
            getParamsConfigVo.setDataEnable(paramsConfig.getDataEnable() == null ? 1 : paramsConfig.getDataEnable());
        }
        return WebResponse.success(getParamsConfigVo);
    }
}
