package com.serotonin.managementController;

import com.serotonin.entity.User;
import com.serotonin.entity.WebResponse;
import com.serotonin.managementService.UserManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/4.
 */
@RestController
@RequestMapping(value = "/web/management/user")
public class UserController {
    @Resource
    private UserManagementService userManagementService;

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getUsers() {
        return userManagementService.getUsers();
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addUser(@RequestBody User user) {
        return userManagementService.addUser(user);
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteUser(@RequestParam Integer id) {
        return userManagementService.deleteUser(id);
    }

    /**
     * 更新用户
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateUser(@RequestBody User user) {
        return userManagementService.updateUser(user);
    }
}
