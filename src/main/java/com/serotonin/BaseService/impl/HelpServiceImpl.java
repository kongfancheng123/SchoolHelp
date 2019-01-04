package com.serotonin.BaseService.impl;

import com.serotonin.BaseService.HelpService;
import com.serotonin.dao.HelpDao;
import com.serotonin.entity.Help;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
@Service
public class HelpServiceImpl implements HelpService {
    @Resource
    private HelpDao helpDao;

    @Override
    public Integer insertHelp(Help query) {
        return helpDao.insertHelp(query);
    }

    @Override
    public Integer deleteHelp(Integer id) {
        return helpDao.deleteHelp(id);
    }

    @Override
    public List<Help> selectByHelp(Help query) {
        return helpDao.selectByHelp(query);
    }

    @Override
    public Integer updateHelp(Help query) {
        return helpDao.updateHelp(query);
    }

    @Override
    public List<Help> selectAll() {
        return helpDao.selectAll();
    }
}
