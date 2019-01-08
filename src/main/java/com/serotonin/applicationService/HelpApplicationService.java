package com.serotonin.applicationService;

import com.serotonin.entity.Help;
import com.serotonin.entity.WebResponse;

/**
 * Create by fchkong on 2019/1/8.
 */
public interface HelpApplicationService {
    /**
     * 发布帮助
     */
    WebResponse publishHelp(Help help);

    /**
     * 取消发布帮助
     */
    WebResponse cancelPublishHelp(Integer id);

    /**
     * 接受帮助
     */
    WebResponse receiveHelp(Integer id);

    /**
     * 完成帮助
     */
    WebResponse finishHelp(Integer id);
}
