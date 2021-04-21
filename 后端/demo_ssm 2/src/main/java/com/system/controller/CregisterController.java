package com.system.controller;


import com.system.domain.AirlineInfo;
import com.system.domain.PassengerInfo;
import com.system.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("register")
public class CregisterController {
    @Autowired
    private AirlineService airlineService;

    @RequestMapping(value = "airlineregister",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String insertAirline(AirlineInfo airlineInfo) throws Exception{
        String a = airlineService.Cregister(airlineInfo);
        if (a == "ok") {
            return "注册成功";
        }
        else if (a == "fail"){
            return "用户名已存在";
        }
        else {
            return "注册失败";
        }
    }

    @RequestMapping(value = "airlinrlogin",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String Airlinelogin(String username,String password) throws Exception{
        String logininfo = airlineService.airlinelogin(username,password);
        if (logininfo.equals("success")) {
            return "登录成功";
        }
        else if (logininfo.equals("loginfail")) {
            return "密码错误";
        }
        else if (logininfo.equals("noexit")){
            return "用户名不存在";
        }
        else if (logininfo.equals("未审核")) {
            return "未审核";
        }
        else {
            return "登录失败";
        }
    }

}
