package com.system.controller;

import com.system.domain.AdminInfo;
import com.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admininfo")
public class AdminInfoController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("getadminList")
    public List<AdminInfo> getadminlist() {
        return adminService.getadminList();
    }
}
