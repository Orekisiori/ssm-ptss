package com.system.service;

import com.system.domain.RechargeRecord;
import com.system.domain.vo.UserRechargeVo;

import java.util.List;

public interface WallentsService {
    List<RechargeRecord> getRechargeList(int passengerId);

    int deleteOne(int rechargeId);

    UserRechargeVo getsumaccount();

    List<RechargeRecord> getListByoptionName();
}
