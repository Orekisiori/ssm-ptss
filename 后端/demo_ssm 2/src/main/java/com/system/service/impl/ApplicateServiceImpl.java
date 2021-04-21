package com.system.service.impl;

import com.system.domain.AirlineInfo;
import com.system.domain.ApplicateRecord;
import com.system.mapper.AirlineInfoMapper;
import com.system.mapper.ApplicateRecordMapper;
import com.system.service.ApplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicateServiceImpl implements ApplicateService {
    @Autowired
    private ApplicateRecordMapper applicateRecordMapper;

    @Autowired
    private AirlineInfoMapper airlineInfoMapper;

    @Override
    public String postapplicateaccount(ApplicateRecord applicateRecord) {
        String applicate_status = "申请中";
        applicateRecord.setApplicateStatus(applicate_status);
        applicateRecordMapper.insertSelective(applicateRecord);
        return "提现申请成功";
    }

    @Override
    public List<ApplicateRecord> getList() {
        return applicateRecordMapper.getList();
    }

    @Override
    public String rebackapplicate(ApplicateRecord applicateRecord) {
        //判断状态是否为审核通过
        //获取状态
        String applicateStatus = applicateRecord.getApplicateStatus();
        //获取申请金额
        float applicateaccount = applicateRecord.getRequestAccount();
        if (applicateStatus.equals("审核通过")) {
            return "已审核，请勿再次申请";
        }
        else {
            //可以审核，进行金额处理
            int airline_id = applicateRecord.getAirlineId();
            AirlineInfo airlineInfo = airlineInfoMapper.selectByPrimaryKey(airline_id);
            //获取航空公司的现有金额
            float account = airlineInfo.getAirlineAccount();
            float finalaccount = account+applicateaccount;
            airlineInfo.setAirlineAccount(finalaccount);
            airlineInfoMapper.updateByPrimaryKeySelective(airlineInfo);
            //将申请单状态改为审核通过
            String applicatestatus = "审核通过";
            applicateRecord.setApplicateStatus(applicatestatus);
            applicateRecordMapper.updateByPrimaryKeySelective(applicateRecord);

            return "审核成功";
        }
    }
}
