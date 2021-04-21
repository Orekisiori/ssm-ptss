package com.system.controller;

import com.system.domain.AdminInfo;
import com.system.domain.AirlineInfo;
import com.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("register")
public class AregisterController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "adminregister",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String insertAdmin(AdminInfo adminInfo) throws Exception{
        String a = adminService.Aregister(adminInfo);
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

    @RequestMapping(value = "adminlogin",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String Adminlogin(String username,String password) throws Exception{
        String logininfo = adminService.adminlogin(username,password);
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

    //通过用户名查找
    @RequestMapping(value = "searchByName",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<AdminInfo> searchByname(String searchtext) {
        return adminService.searchListByName(searchtext);
    }



}
