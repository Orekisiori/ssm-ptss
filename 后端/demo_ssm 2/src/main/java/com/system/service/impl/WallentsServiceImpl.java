package com.system.service.impl;

import com.system.domain.RechargeRecord;
import com.system.domain.vo.UserRechargeVo;
import com.system.mapper.RechargeRecordMapper;
import com.system.service.WallentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallentsServiceImpl implements WallentsService {
    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public List<RechargeRecord> getRechargeList(int passengerId) {
        return rechargeRecordMapper.selectBypassengerId(passengerId);
    }

    @Override
    public int deleteOne(int rechargeId) {
        return rechargeRecordMapper.deleteByPrimaryKey(rechargeId);
    }

    @Override
    public UserRechargeVo getsumaccount() {
        return rechargeRecordMapper.getsumaccount();
    }

    @Override
    public List<RechargeRecord> getListByoptionName() {
        return rechargeRecordMapper.selectByoptionName();
    }

}
