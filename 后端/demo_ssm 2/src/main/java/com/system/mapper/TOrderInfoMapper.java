package com.system.mapper;

import com.system.domain.TOrderInfo;

import java.util.List;

public interface TOrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrderInfo record);

    int insertSelective(TOrderInfo record);

    TOrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrderInfo record);

    int updateByPrimaryKey(TOrderInfo record);

    void insertOrderInfos(List<TOrderInfo> orderInfoList);

    List<TOrderInfo> selectByOrderId(Integer orderId);
}
