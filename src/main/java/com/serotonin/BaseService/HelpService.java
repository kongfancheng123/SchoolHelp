package com.serotonin.BaseService;

import com.serotonin.entity.Help;

import java.util.List;

/**
 * Create by fchkong on 2018/12/29.
 */
public interface HelpService {
    /**
     * 新增帮助
     *
     * @param query
     * @return
     */
    Integer insertHelp(Help query);

    /**
     * 删除帮助
     *
     * @param id
     * @return
     */
    Integer deleteHelp(Integer id);

    /**
     * 多条件查询帮助
     *
     * @param query
     * @return
     */
    List<Help> selectByHelp(Help query);

    /**
     * 更新帮助
     *
     * @param query
     * @return
     */
    Integer updateHelp(Help query);

    /**
     * 查询所有帮助
     */
    List<Help> selectAll();
}
