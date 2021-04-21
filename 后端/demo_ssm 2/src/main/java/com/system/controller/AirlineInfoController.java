package com.system.controller;


import com.system.domain.AirlineInfo;
import com.system.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("airlineInfo")
public class AirlineInfoController {
    @Autowired
    private AirlineService airlineService;

    @RequestMapping("getList")
    public List<AirlineInfo> getList() {
        return airlineService.getList();
    }

    //通过用户名查找
    @RequestMapping(value = "searchByName",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public List<AirlineInfo> searchByname(String searchtext) {
        return airlineService.searchListByName(searchtext);
    }

    //获取所有公司名字
    @RequestMapping("getAllairlineName")
    public List<AirlineInfo> getAllairlineName() {
        return airlineService.getallAirName();
    }




}
