package com.serotonin.managementController;

import com.serotonin.entity.*;
import com.serotonin.managementService.AreaManagementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by fchkong on 2019/1/3.
 */
@RestController
@RequestMapping(value = "/web/management/area")
public class AreaController {
    @Resource
    private AreaManagementService areaManagementService;

    /**
     * 获取省份列表
     */
    @RequestMapping(value = "/getProvince", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getProvince() {
        return areaManagementService.getProvince();
    }

    /**
     * 添加省份
     */
    @RequestMapping(value = "/addProvince", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addProvince(@RequestBody Province province) {
        return areaManagementService.addProvince(province);
    }

    /**
     * 删除省份
     */
    @RequestMapping(value = "/deleteProvince", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteProvince(@RequestParam Integer id) {
        return areaManagementService.deleteProvince(id);
    }

    /**
     * 更新省份
     */
    @RequestMapping(value = "/updateProvince", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateProvince(@RequestBody Province province) {
        return areaManagementService.updateProvince(province);
    }

    /**
     * 获取城市列表
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getCity() {
        return areaManagementService.getCity();
    }

    /**
     * 添加城市
     */
    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addCity(@RequestBody City city) {
        return areaManagementService.addCity(city);
    }

    /**
     * 删除城市
     */
    @RequestMapping(value = "/deleteCity", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteCity(@RequestParam Integer id) {
        return areaManagementService.deleteCity(id);
    }

    /**
     * 更新城市
     */
    @RequestMapping(value = "/updateCity", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateCity(@RequestBody City city) {
        return areaManagementService.updateCity(city);
    }

    /**
     * 获取学校列表
     */
    @RequestMapping(value = "/getSchool", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getSchool() {
        return areaManagementService.getSchool();
    }

    /**
     * 添加学校
     */
    @RequestMapping(value = "/addSchool", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addSchool(@RequestBody School school) {
        return areaManagementService.addSchool(school);
    }

    /**
     * 删除学校
     */
    @RequestMapping(value = "/deleteSchool", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteSchool(@RequestParam Integer id) {
        return areaManagementService.deleteSchool(id);
    }

    /**
     * 更新学校
     */
    @RequestMapping(value = "/updateSchool", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateSchool(@RequestBody School school) {
        return areaManagementService.updateSchool(school);
    }

    /**
     * 获取学院列表
     */
    @RequestMapping(value = "/getCollege", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getCollege() {
        return areaManagementService.getCollege();
    }

    /**
     * 添加学院
     */
    @RequestMapping(value = "/addCollege", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addCollege(@RequestBody College college) {
        return areaManagementService.addCollege(college);
    }

    /**
     * 删除学院
     */
    @RequestMapping(value = "/deleteCollege", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteCollege(@RequestParam Integer id) {
        return areaManagementService.deleteCollege(id);
    }

    /**
     * 更新学院
     */
    @RequestMapping(value = "/updateCollege", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateCollege(@RequestBody College college) {
        return areaManagementService.updateCollege(college);
    }

    /**
     * 获取校区列表
     */
    @RequestMapping(value = "/getSchoolDistrict", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse getSchoolDistrict() {
        return areaManagementService.getSchoolDistrict();
    }

    /**
     * 添加校区
     */
    @RequestMapping(value = "/addSchoolDistrict", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse addSchoolDistrict(@RequestBody SchoolDistrict schoolDistrict) {
        return areaManagementService.addSchoolDistrict(schoolDistrict);
    }

    /**
     * 删除校区
     */
    @RequestMapping(value = "/deleteSchoolDistrict", method = RequestMethod.GET)
    @ResponseBody
    public WebResponse deleteSchoolDistrict(@RequestParam Integer id) {
        return areaManagementService.deleteSchoolDistrict(id);
    }

    /**
     * 更新校区
     */
    @RequestMapping(value = "/updateSchoolDistrict", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateSchoolDistrict(@RequestBody SchoolDistrict schoolDistrict) {
        return areaManagementService.updateSchoolDistrict(schoolDistrict);
    }


}
