package com.system.mapper;

import com.system.domain.TFlowInfo;

import java.util.List;

public interface TFlowInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFlowInfo record);

    int insertSelective(TFlowInfo record);

    TFlowInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFlowInfo record);

    int updateByPrimaryKey(TFlowInfo record);

    List<TFlowInfo> getFlowInfoList();

    List<TFlowInfo> selectByfIds(List<Integer> fIds);

    void updateStock(TFlowInfo tFlowInfo);

    TFlowInfo getStock(String fName);

    List<TFlowInfo> selectKey(String query);
}
