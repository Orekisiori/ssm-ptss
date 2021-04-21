package com.system.service;

import com.system.domain.PassengerInfo;

import java.util.List;

public interface PassengerService {

    List<PassengerInfo> getList();

    PassengerInfo getCurrentinfo(String username);

    String Pregister(PassengerInfo passengerInfo);

    String Plogin(String passengerName,String passengerPassword);

    PassengerInfo updateCurrentinfo(PassengerInfo passengerInfo);

    String pCharge(int passengerId,float rechargedata);

    List<PassengerInfo> getPassengerList();

    int deleteOne(int passengerId);

    List<PassengerInfo> searchListByName(String searchtext);

    String updatepwd(Integer passengerId,String orignpass,String checkPass);

}
