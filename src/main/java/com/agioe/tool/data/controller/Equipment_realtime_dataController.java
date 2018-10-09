package com.agioe.tool.data.controller;

import com.agioe.tool.data.service.Equipment_realtime_dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Equipment_realtime_data")
public class Equipment_realtime_dataController {
    @Autowired
    private Equipment_realtime_dataService equipment_realtime_dataService;

}
