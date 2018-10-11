package com.agioe.tool.data.Test;

import com.agioe.tool.data.dao.TestDao;
import com.agioe.tool.data.entity.TestInfo;
import com.agioe.tool.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITestDaoImpl implements BaseService {
    @Autowired
    private TestDao testDao;

    @Override
    public List<TestInfo> selectAll() {
        int k = 2;
        return testDao.selectAll();
    }
}
