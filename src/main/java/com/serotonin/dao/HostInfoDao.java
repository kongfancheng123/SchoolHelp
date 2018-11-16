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
//    /**
//     * 新增监控信息
//     *
//     * @param hostInfo
//     * @return
//     */
//    Integer insertHostInfo(@Param("hostInfo") HostInfo hostInfo);
//    /**
//     * 删除主机
//     *
//     * @param equipmentPropertyTemplateCode
//     * @return
//     */
//    Integer deleteHostInfo(String equipmentPropertyTemplateCode);
    /**
     * 多条件查询数据
     *
     * @param hostInfo
     * @return
     */
    List<HostInfo> selectByHostInfo(@Param("hostInfo") HostInfo hostInfo);

    /**
     * 更新主机信息
     *
     * @param hostInfo
     * @return
     */
    Integer updateHostInfo(@Param("hostInfo") HostInfo hostInfo);

}
