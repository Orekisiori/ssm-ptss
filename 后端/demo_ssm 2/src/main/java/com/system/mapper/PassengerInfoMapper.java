package com.system.mapper;

import com.system.domain.PassengerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PassengerInfoMapper {
    int deleteByPrimaryKey(Integer passengerId);

    int insert(PassengerInfo record);

    int insertSelective(PassengerInfo record);

    PassengerInfo selectByPrimaryKey(Integer passengerId);

    int updateByPrimaryKeySelective(PassengerInfo record);

    int updateByPrimaryKey(PassengerInfo record);

    int updateByPrimaryKeyCharge(@Param(value = "passengerId") Integer passengerId, @Param(value = "passengerAccount") Float passengerAccount);

    List<PassengerInfo> getList();

    PassengerInfo selectByPassengerName(String passengerName);

    List<PassengerInfo> selectListByName(String searchtext);


}