package com.system.mapper;

import com.system.domain.RechargeRecord;
import com.system.domain.vo.UserRechargeVo;

import java.util.List;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer rechargeId);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer rechargeId);

    float selectWallentByPassengerId(Integer passengerId);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);

    List<RechargeRecord> selectBypassengerId(Integer passengerId);

    UserRechargeVo getsumaccount();

    List<RechargeRecord> selectByoptionName();
}