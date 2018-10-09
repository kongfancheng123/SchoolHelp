package com.agioe.tool.data.controller;

import com.agioe.tool.data.Qo.SendEvent_historyQo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.Event_historyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Event_history")
public class Event_historyController {
    @Autowired
    private Event_historyService event_historyService;

    /**
     * 发送事件
     *
     * @return
     */
    @RequestMapping(value = "/sendEvent_history", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse sendEvent_history(@RequestBody SendEvent_historyQo sendEvent_historyQo) {
        return event_historyService.sendEvent_history(sendEvent_historyQo);
    }
}

