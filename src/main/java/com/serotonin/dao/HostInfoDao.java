package com.serotonin.dao;

import com.serotonin.entity.HostInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/11/15.
 */
@Repository
public interface HostInfoDao {
    /**
     * 多条件查询数据
     *
     * @param hostInfo
     * @return
     */
    List<HostInfo> selectByHostInfo(@Param("hostInfo") HostInfo hostInfo);
}
