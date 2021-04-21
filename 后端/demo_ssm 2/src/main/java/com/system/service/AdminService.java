package com.system.service;

import com.system.domain.AdminInfo;

import java.util.List;

public interface AdminService {
    String Aregister(AdminInfo adminInfo);

    String adminlogin(String username,String password);

    List<AdminInfo> searchListByName(String searchtext);

    List<AdminInfo> getadminList();
}
