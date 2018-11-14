package com.agioe.tool.data.Test;

import com.agioe.tool.data.entity.TestInfo;
import com.agioe.tool.data.entity.WebResponse;
import com.agioe.tool.data.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yshen
 * @since 2018/9/27
 */
@RestController
@RequestMapping(value = "/test")
public class DemoHelloApi {

    private final static Logger logger = LoggerFactory.getLogger(DemoHelloApi.class);

    @Autowired
    private BaseService baseService;

    @Autowired
    private ExcelService excelService;

    //    @RequestMapping("/showAll", method = RequestMethod.POST)
    @RequestMapping(value = "/showAll", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse test() {
        List<TestInfo> testInfos = baseService.selectAll();
        return WebResponse.success(testInfos);
    }

    @GetMapping("/kkk")
    public WebResponse kkk() {
        return WebResponse.success("kkkk");
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse exportExcel() throws Exception {
//        excelService.exportExcel();
        return WebResponse.success();
    }

    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse importExcel() throws Exception {
        String[][] strings = excelService.importExcel("D://excel//EquipmentType//temp.xls");
        return WebResponse.success(strings);
    }
}
