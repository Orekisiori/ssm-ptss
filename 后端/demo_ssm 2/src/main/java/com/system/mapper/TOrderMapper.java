package com.system.mapper;

import com.system.domain.TOrder;

import java.util.List;

public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    List<TOrder> selectByUserId(Integer userId);

    //管理员模块的订单管理
    List<TOrder> getOrderList();

    List<TOrder> selectByKey(String query);

    void updateStatic(TOrder tOrder);
}
