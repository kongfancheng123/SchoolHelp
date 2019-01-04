package com.serotonin.managementService.impl;

import com.serotonin.BaseService.*;
import com.serotonin.entity.*;
import com.serotonin.managementQoAndVo.vo.GetCityVo;
import com.serotonin.managementQoAndVo.vo.GetCollegeVo;
import com.serotonin.managementQoAndVo.vo.GetSchoolDistrictVo;
import com.serotonin.managementQoAndVo.vo.GetSchoolVo;
import com.serotonin.managementService.AreaManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by fchkong on 2019/1/3.
 */
@Service
public class AreaManagementServiceImpl implements AreaManagementService {
    @Resource
    private ProvinceService provinceService;
    @Resource
    private CityService cityService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private SchoolDistrictService schoolDistrictService;


    @Override
    public WebResponse getProvince() {
        List<Province> provinces = provinceService.selectAll();
        return WebResponse.success(provinces);
    }

    @Override
    public WebResponse addProvince(Province province) {
        //todo:判断重复
        provinceService.insertProvince(province);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteProvince(Integer id) {
        //todo:判断绑定关系
        provinceService.deleteProvince(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateProvince(Province province) {
        provinceService.updateProvince(province);
        return WebResponse.success();
    }

    @Override
    public WebResponse getCity() {
        List<GetCityVo> getCityVos = new ArrayList<>();
        List<City> cities = cityService.selectAll();
        if (cities.size() > 0) {
            for (City city : cities) {
                GetCityVo getCityVo = new GetCityVo();
                getCityVo.setId(city.getId());
                getCityVo.setCityName(city.getCityName());
                getCityVo.setProvinceId(city.getProvinceId());
                Province province = new Province();
                province.setId(city.getProvinceId());
                List<Province> provinces = provinceService.selectByProvince(province);
                if (provinces.size() > 0) {
                    getCityVo.setProvinceName(provinces.get(0).getProvinceName());
                }
                getCityVos.add(getCityVo);
            }
        }
        return WebResponse.success(getCityVos);
    }

    @Override
    public WebResponse addCity(City city) {
        //todo:判断重复
        cityService.insertCity(city);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteCity(Integer id) {
        //todo:判断绑定关系
        cityService.deleteCity(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateCity(City city) {
        cityService.updateCity(city);
        return WebResponse.success();
    }

    @Override
    public WebResponse getSchool() {
        List<GetSchoolVo> getSchoolVos = new ArrayList<>();
        List<School> schools = schoolService.selectAll();
        if (schools.size() > 0) {
            for (School school : schools) {
                GetSchoolVo getSchoolVo = new GetSchoolVo();
                getSchoolVo.setId(school.getId());
                getSchoolVo.setSchoolName(school.getSchoolName());
                getSchoolVo.setCityId(school.getCityId());
                City city = new City();
                city.setId(school.getCityId());
                List<City> cities = cityService.selectByCity(city);
                if (cities.size() > 0) {
                    City city1 = cities.get(0);
                    getSchoolVo.setCityName(city1.getCityName());
                }
                getSchoolVos.add(getSchoolVo);
            }
        }
        return WebResponse.success(getSchoolVos);
    }

    @Override
    public WebResponse addSchool(School school) {
        //todo:判重
        schoolService.insertSchool(school);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteSchool(Integer id) {
        //todo:判断有无绑定关系
        schoolService.deleteSchool(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateSchool(School school) {
        schoolService.updateSchool(school);
        return WebResponse.success();
    }

    @Override
    public WebResponse getCollege() {
        List<GetCollegeVo> getCollegeVos = new ArrayList<>();
        List<College> colleges = collegeService.selectAll();
        if (colleges.size() > 0) {
            for (College college : colleges) {
                GetCollegeVo getCollegeVo = new GetCollegeVo();
                getCollegeVo.setId(college.getId());
                getCollegeVo.setCollegeName(college.getCollegeName());
                getCollegeVo.setSchoolId(college.getSchoolId());
                School school = new School();
                school.setId(college.getSchoolId());
                List<School> schools = schoolService.selectBySchool(school);
                if (schools.size() > 0) {
                    School school1 = schools.get(0);
                    getCollegeVo.setSchoolName(school1.getSchoolName());
                }
                getCollegeVos.add(getCollegeVo);
            }
        }
        return WebResponse.success(getCollegeVos);
    }

    @Override
    public WebResponse addCollege(College college) {
        //todo:判断重复
        collegeService.insertCollege(college);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteCollege(Integer id) {
        //todo:判断是否有绑定关系
        collegeService.deleteCollege(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateCollege(College college) {
        collegeService.updateCollege(college);
        return WebResponse.success();
    }

    @Override
    public WebResponse getSchoolDistrict() {
        List<GetSchoolDistrictVo> getSchoolDistrictVos = new ArrayList<>();
        List<SchoolDistrict> schoolDistricts = schoolDistrictService.selectAll();
        if (schoolDistricts.size() > 0) {
            for (SchoolDistrict schoolDistrict : schoolDistricts) {
                GetSchoolDistrictVo getSchoolDistrictVo = new GetSchoolDistrictVo();
                getSchoolDistrictVo.setId(schoolDistrict.getId());
                getSchoolDistrictVo.setSchoolDistrictName(schoolDistrict.getSchoolDistrictName());
                getSchoolDistrictVo.setSchoolId(schoolDistrict.getSchoolId());
                School school = new School();
                school.setId(schoolDistrict.getSchoolId());
                List<School> schools = schoolService.selectBySchool(school);
                if (schools.size() > 0) {
                    School school1 = schools.get(0);
                    getSchoolDistrictVo.setSchoolName(school1.getSchoolName());
                }
                getSchoolDistrictVos.add(getSchoolDistrictVo);
            }
        }
        return WebResponse.success(getSchoolDistrictVos);
    }

    @Override
    public WebResponse addSchoolDistrict(SchoolDistrict schoolDistrict) {
        //todo:判重
        schoolDistrictService.insertSchoolDistrict(schoolDistrict);
        return WebResponse.success();
    }

    @Override
    public WebResponse deleteSchoolDistrict(Integer id) {
        //todo:判断绑定关系
        schoolDistrictService.deleteSchoolDistrict(id);
        return WebResponse.success();
    }

    @Override
    public WebResponse updateSchoolDistrict(SchoolDistrict schoolDistrict) {
        schoolDistrictService.updateSchoolDistrict(schoolDistrict);
        return WebResponse.success();
    }
}
