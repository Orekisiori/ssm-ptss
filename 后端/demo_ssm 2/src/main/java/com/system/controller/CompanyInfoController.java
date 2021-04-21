package com.system.controller;

import com.system.domain.AirlineInfo;
import com.system.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyInfoController {
    @Autowired
    private AirlineService airlineService;

    @RequestMapping(value = "validpass",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String validpass(AirlineInfo airlineInfo) {
        String result = airlineService.vaildpass(airlineInfo);
            if (result.equals("success")) {
                return "审核通过";
            }
            else {
                return "未知错误";
            }
    }

    @RequestMapping(value = "deleteOne",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String deleteOne(AirlineInfo airlineInfo) {
        String delateresult = airlineService.deleteOne(airlineInfo);
        if (delateresult.equals("success")) {
            return "删除成功";
        }
        else {
            return "未知错误";
        }
    }


}
