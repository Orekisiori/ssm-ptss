package com.system.mapper;

import com.system.domain.OrderInfo;
import com.system.domain.vo.AirlineOrderaccount;
import com.system.domain.vo.UserRechargeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectCurrentPOrders(Integer passengerId);

    List<OrderInfo> selectAllTicketlist();

    List<OrderInfo> selectOrder(String searchtext);

    List<OrderInfo> searchListBycompanyname(String value);

    UserRechargeVo getCurrentOrderAccount(String airlineName);
}