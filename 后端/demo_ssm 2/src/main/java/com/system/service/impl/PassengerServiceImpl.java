package com.system.service.impl;

import com.system.domain.PassengerInfo;
import com.system.domain.RechargeRecord;
import com.system.mapper.PassengerInfoMapper;
import com.system.mapper.RechargeRecordMapper;
import com.system.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public List<PassengerInfo> getList() {
        return passengerInfoMapper.getList();
    }

    @Override
    public PassengerInfo getCurrentinfo(String username) {
        return passengerInfoMapper.selectByPassengerName(username);
    }

    @Override
    public String Pregister(PassengerInfo passengerInfo) {
        PassengerInfo isregister = passengerInfoMapper.selectByPassengerName(passengerInfo.getPassengerName());
        if (isregister == null) {
            Date registerTime = new Date();
            passengerInfo.setRegisterTime(registerTime);
            passengerInfoMapper.insertSelective(passengerInfo);
            return "ok";
        }
        else {
            return "fail";
        }
    }

    @Override
    public  String Plogin(String passengerName,String passengerPassword) {
        //先判断用户是否存在
        PassengerInfo islogin = passengerInfoMapper.selectByPassengerName(passengerName);
        if (islogin != null) {
            //获取密码
            String getpassword = islogin.getPassengerPassword();
            if (passengerPassword.equals(getpassword)) {
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
    public PassengerInfo updateCurrentinfo(PassengerInfo passengerInfo) {
        passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);
        return passengerInfoMapper.selectByPrimaryKey(passengerInfo.getPassengerId());
    }

    @Override
    public String pCharge(int passengerId,float rechargedata) {
        PassengerInfo passengerInfo = passengerInfoMapper.selectByPrimaryKey(passengerId);
        float current = passengerInfo.getPassengerAccount();
        float passengerAccount = current+rechargedata;
        passengerInfoMapper.updateByPrimaryKeyCharge(passengerId,passengerAccount);

        Integer passenger_id = passengerInfo.getPassengerId();
        String passager_name = passengerInfo.getPassengerName();
        String passenger_identity = passengerInfo.getPassengerIdentity();
        String passenger_phone = passengerInfo.getPassengerPhone();
        Float recharge_account = rechargedata;
        String option_name = "账户充值";
        Date recharge_time = new Date();
        RechargeRecord record = new RechargeRecord ();
        record.setPassengerId(passenger_id);
        record.setPassengerName(passager_name);
        record.setPassengerIdentity(passenger_identity);
        record.setPassengerPhone(passenger_phone);
        record.setRechargeAccount(recharge_account);
        record.setOptionName(option_name);
        record.setRechargeTime(recharge_time);
        rechargeRecordMapper.insertSelective(record);

        return "success";
    }


    @Override
    public List<PassengerInfo> getPassengerList() {
        return passengerInfoMapper.getList();
    }


    @Override
    public int deleteOne(int passengerId) {
        return passengerInfoMapper.deleteByPrimaryKey(passengerId);
    }

    @Override
    public List<PassengerInfo> searchListByName(String searchtext) {
        return passengerInfoMapper.selectListByName(searchtext);
    }

    @Override
    public String updatepwd(Integer passengerId,String orignpass,String checkPass) {
        //由id获取用户密码
        PassengerInfo passengerInfo = passengerInfoMapper.selectByPrimaryKey(passengerId);
        String result = passengerInfo.getPassengerPassword();
        if (result.equals(orignpass)) {
            //与原始密码匹配,修改
            passengerInfo.setPassengerPassword(checkPass);
            passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);
            return "修改成功";
        }
        else {
            return "原密码错误";
        }

    }
}
