package com.system.mapper;

import com.system.domain.AirlineInfo;

import java.util.List;

public interface AirlineInfoMapper {
    int deleteByPrimaryKey(Integer airlineId);

    int insert(AirlineInfo record);

    int insertSelective(AirlineInfo record);

    AirlineInfo selectByPrimaryKey(Integer airlineId);

    int updateByPrimaryKeySelective(AirlineInfo record);

    int updateByPrimaryKey(AirlineInfo record);

    AirlineInfo selectByAirlineName(String airlineName);

    List<AirlineInfo> selectAll();

    List<AirlineInfo> selectListByName(String airlineName);

    List<AirlineInfo> selectAllairlineName();
}