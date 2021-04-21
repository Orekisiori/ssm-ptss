package com.system.service.impl;


import com.system.domain.AdminInfo;
import com.system.domain.AirlineInfo;
import com.system.domain.LogRecord;
import com.system.mapper.AdminInfoMapper;
import com.system.mapper.LogRecordMapper;
import com.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public String Aregister(AdminInfo adminInfo) {
        AdminInfo isregister = adminInfoMapper.selectByAdminName(adminInfo.getAdminName());
        if (isregister == null) {
            Date register_time = new Date();
            adminInfoMapper.insertSelective(adminInfo);
            return "ok";
        }
        else {
            return "fail";
        }
    }

    @Override
    public  String adminlogin(String username,String password) {
        //先判断用户是否存在
        AdminInfo islogin = adminInfoMapper.selectByAdminName(username);
        if (islogin != null) {
            //获取密码
            String getpassword = islogin.getAdminPassword();
            if (password.equals(getpassword)) {

                //插入一条登录记录
                LogRecord logRecord = new LogRecord();
                int admin_id = islogin.getAdminId();
                String admin_name = islogin.getAdminName();
                String admin_phone = islogin.getAdminPhone();
                Date log_time = new Date();
                logRecord.setAdminId(admin_id);
                logRecord.setAdminName(admin_name);
                logRecord.setAdminPhone(admin_phone);
                logRecord.setLogTime(log_time);
                logRecordMapper.insertSelective(logRecord);

                return "success";
            }
            else {
                return "loginfail";
            }
        }
        else {
            return "noexit";
        }
    }

    @Override
    public List<AdminInfo> searchListByName(String searchtext) {
        return adminInfoMapper.selectListByName(searchtext);
    }

    @Override
    public List<AdminInfo> getadminList() {
        return adminInfoMapper.selectAlladmin();
    }
}
