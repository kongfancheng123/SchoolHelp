package com.serotonin.applicationController;

import com.serotonin.applicationService.LoginApplicationService;
import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2018/12/27.
 */
@RestController
@RequestMapping(value = "/web/application/login")
public class LoginApplicationController {
    @Resource
    private LoginApplicationService loginApplicationService;

    /**
     * 普通用户注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse register(@RequestBody User user) {
        return loginApplicationService.register(user);
    }

    /**
     * 普通用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse login(@RequestBody User user) {
        return loginApplicationService.login(user);
    }
}
