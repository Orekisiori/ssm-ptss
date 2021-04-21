package com.system.controller;

import com.system.domain.PassengerInfo;
import com.system.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class PregisterController {

    @Autowired
    private PassengerService passengerService;


    @RequestMapping(value = "pregister",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String insertPassenger(PassengerInfo passengerInfo) throws Exception{
            String a = passengerService.Pregister(passengerInfo);
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

    @RequestMapping(value = "plogin",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String Passengerlogin(String username,String password) throws Exception{
        String logininfo = passengerService.Plogin(username,password);
        if (logininfo.equals("success")) {
            return "登录成功";
        }
        else if (logininfo.equals("loginfail")) {
            return "密码错误";
        }
        else if (logininfo.equals("noexit")){
            return "用户名不存在";
        }
        else {
            return "登录失败";
        }
    }

}
