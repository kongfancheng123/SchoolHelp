package com.serotonin.applicationController;

import com.serotonin.entity.WebResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by fchkong on 2018/12/27.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    /**
     * 展示所有设备
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse test() {
        return WebResponse.success("hello world  111");
    }
}
