package com.serotonin.managementController;

import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.LoginManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2018/12/27.
 */
@RestController
@RequestMapping(value = "/web/management/login")
public class LoginManagementController {
    @Resource
    private LoginManagementService loginManagementService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse login(@RequestBody User user) {
        return loginManagementService.login(user);
    }
}
