package com.serotonin.dao;

import com.serotonin.entity.CollectorInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Repository
public interface CollectorInfoDao {
    /**
     * 多条件查询数据
     *
     * @param collectorInfo
     * @return
     */
    List<CollectorInfo> selectByCollectorInfo(@Param("collectorInfo") CollectorInfo collectorInfo);
}
