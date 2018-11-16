package com.serotonin.dao;

import com.serotonin.entity.ContainerInfo;
import com.serotonin.entity.HostInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by fchkong on 2018/11/16.
 */
@Repository
public interface ContainerInfoDao {
    /**
     * 多条件查询数据
     *
     * @param containerInfo
     * @return
     */
    List<HostInfo> selectByContainerInfo(@Param("containerInfo") ContainerInfo containerInfo);
}
