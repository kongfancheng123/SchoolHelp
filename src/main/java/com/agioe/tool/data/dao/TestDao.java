package com.agioe.tool.data.dao;

import com.agioe.tool.data.entity.TestInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    List<TestInfo> selectAll();
}
