package com.system.mapper;

import com.system.domain.TFlowSold;

import java.util.List;

public interface TFlowSoldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFlowSold record);

    int insertSelective(TFlowSold record);

    TFlowSold selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFlowSold record);

    int updateByPrimaryKey(TFlowSold record);

    void insertFlowSolds(List<TFlowSold> tFlowSoldList);

    List<TFlowSold> getStatisticsList();
}
