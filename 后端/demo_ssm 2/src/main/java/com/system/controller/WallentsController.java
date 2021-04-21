package com.system.controller;

import com.system.domain.RechargeRecord;
import com.system.domain.vo.UserRechargeVo;
import com.system.service.WallentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wallentinfo")
public class WallentsController {
    @Autowired
    private WallentsService wallentService;

    @RequestMapping(value = "getlist",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<RechargeRecord> rechargeRecord(int passengerId) throws Exception {
        return wallentService.getRechargeList(passengerId);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public int deleteRecord(int rechargeId) throws Exception {
        return wallentService.deleteOne(rechargeId);
    }

    //计算总金额
    @RequestMapping("getsumaccount")
    public UserRechargeVo getsumaccount() {
        return wallentService.getsumaccount();
    }


    //根据操作名获取
    @RequestMapping("getListByoptionName")
    public List<RechargeRecord> getListByoptionName() {
        return wallentService.getListByoptionName();
    }

}
