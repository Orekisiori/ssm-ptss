package com.system.mapper;

import com.system.domain.AdminInfo;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo selectByAdminName(String adminName);

    List<AdminInfo> selectListByName(String searchtext);

    List<AdminInfo> selectAlladmin();
}