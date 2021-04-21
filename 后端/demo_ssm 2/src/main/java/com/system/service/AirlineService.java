package com.system.service;

import com.system.domain.AirlineInfo;

import java.util.List;

public interface AirlineService {
    String Cregister(AirlineInfo airlineInfo);

    List<AirlineInfo> getList();

    String airlinelogin(String username,String password);

    String vaildpass(AirlineInfo airlineInfo);

    String deleteOne(AirlineInfo airlineInfo);

    List<AirlineInfo> searchListByName(String searchtext);

    List<AirlineInfo> getallAirName();
}
