package com.system.service.impl;

import com.system.domain.AirlineInfo;
import com.system.mapper.AirlineInfoMapper;
import com.system.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineInfoMapper airlineInfoMapper;

    @Override
    public String Cregister(AirlineInfo airlineInfo) {
        AirlineInfo isregister = airlineInfoMapper.selectByAirlineName(airlineInfo.getAirlineName());
        if (isregister == null) {
            Date register_time = new Date();
            String isvalid = "未审核";
            airlineInfo.setRegisterdate(register_time);
            airlineInfo.setIsvalid(isvalid);
            airlineInfoMapper.insertSelective(airlineInfo);
            return "ok";
        }
        else {
            return "fail";
        }
    }

    @Override
    public List<AirlineInfo> getList() {
        return airlineInfoMapper.selectAll();
    }

    @Override
    public  String airlinelogin(String username,String password) {
        //先判断用户是否存在
        AirlineInfo islogin = airlineInfoMapper.selectByAirlineName(username);
        //获取是否通过通过
        if (islogin != null) {
            //获取是否审核通过
            String isvaild = islogin.getIsvalid();
            if (isvaild.equals("未审核")) {
                return "未审核";
            } else {
                //获取密码
                String getpassword = islogin.getAirlinePassword();
                if (password.equals(getpassword)) {
                    return "success";
                } else {
                    return "loginfail";
                }
            }
        }
        else {
            return "noexit";
        }
    }

    @Override
    public String vaildpass(AirlineInfo airlineInfo) {
        airlineInfoMapper.updateByPrimaryKeySelective(airlineInfo);
        return "success";
    }

    @Override
    public String deleteOne(AirlineInfo airlineInfo) {
        int airlineid = airlineInfo.getAirlineId();
        airlineInfoMapper.deleteByPrimaryKey(airlineid);
        return "success";
    }

    @Override
    public List<AirlineInfo> searchListByName(String searchtext) {
        return airlineInfoMapper.selectListByName(searchtext);
    }

    @Override
    public List<AirlineInfo> getallAirName() {
        return airlineInfoMapper.selectAllairlineName();
    }
}
