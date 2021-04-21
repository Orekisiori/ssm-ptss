package com.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.domain.PassengerInfo;
import com.system.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("passengerinfo")
public class PassengerInfoController {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping("info")
    public List<PassengerInfo> getList() {
        return passengerService.getList();
    }


    @RequestMapping(value = "current", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public PassengerInfo getCurrent(String username) throws Exception {
        return passengerService.getCurrentinfo(username);
    }

    @RequestMapping(value = "updateinfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public PassengerInfo updateInfo(PassengerInfo passengerInfo) throws Exception {
        return passengerService.updateCurrentinfo(passengerInfo);
    }

    //账户充值
    //需要获取当前用户信息、充值金额 、将充值金额加入到相应的用户账户中
    //生成拥有用户id、用户名、充值金额、充值时间、操作名称的充值记录表
    //根据用户id返回相应的充值记录表
    @RequestMapping(value = "pcharge", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String passengerCharge(int passengerId, float rechargedata) throws Exception {
        String result = passengerService.pCharge(passengerId, rechargedata);
        if (result.equals("success")) {
            return "充值成功";
        } else {
            return "充值失败";
        }
    }

    //删除用户
    @RequestMapping(value = "deleteOne",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public  int deletePone(int passengerId) throws Exception{
        return passengerService.deleteOne(passengerId);
    }

    //通过用户名查找
    @RequestMapping(value = "searchByName",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public List<PassengerInfo> searchByname(String searchtext) {
        return passengerService.searchListByName(searchtext);
    }


    //修改密码
    @RequestMapping(value = "updatepwd",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String updatepwd(Integer passengerId,String orignpass,String checkPass) throws Exception {
        String result = passengerService.updatepwd(passengerId,orignpass,checkPass);
        if (result.equals("修改成功")) {
            return "修改成功";
        }
        else if (result.equals("原密码错误")) {
            return "原密码错误";
        }
        else {
            return "未知错误";
        }
    }
}
