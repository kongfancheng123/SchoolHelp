package com.serotonin.managementService;

import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/4.
 */
public interface HelpManagementService {
    /**
     * 获取帮帮忙列表
     */
    WebResponse getHelps();

    /**
     * 添加帮帮忙
     */
    WebResponse addHelp(Help help);

    /**
     * 删除帮帮忙
     */
    WebResponse deleteHelp(Integer id);

    /**
     * 更新帮帮忙
     */
    WebResponse updateHelp(Help help);
}
