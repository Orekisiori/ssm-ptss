package com.system.mapper;

import com.system.domain.Userlogin;
import com.system.domain.UserloginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserloginMapper {
    int countByExample(UserloginExample example);

    int deleteByExample(UserloginExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Userlogin record);

    int insertSelective(Userlogin record);

    List<Userlogin> selectByExample(UserloginExample example);

    Userlogin selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByExample(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);

    List<Userlogin> getListUser();

    List<Userlogin> selectKey(String query);

    Userlogin selectByUserAccount(String userAccount);
}
