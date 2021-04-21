package com.system.controller;

import com.system.domain.ApplicateRecord;
import com.system.service.ApplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("applicate")
public class ApplicateController {
    @Autowired
    private ApplicateService applicateService;

    @RequestMapping(value = "postapplicateaccount",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String postapplicateaccount(ApplicateRecord applicateRecord) {
        String result = applicateService.postapplicateaccount(applicateRecord);
        if (result.equals("提现申请成功")) {
            return "提现申请成功";
        }
        else {
            return "提现申请失败";
        }
    }

    @RequestMapping(value = "getApplicateList")
    public List<ApplicateRecord> getList() {
        return applicateService.getList();
    }

    @RequestMapping(value = "rebackapplicate",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String rebackapplicate(ApplicateRecord applicateRecord) {
        String result = applicateService.rebackapplicate(applicateRecord);
        if (result.equals("已审核，请勿再次申请")) {
            return "已审核，请勿再次申请";
        }
        else if (result.equals("审核成功")){
            return "审核成功";
        }
        else {
            return "未知错误";
        }
    }
}
