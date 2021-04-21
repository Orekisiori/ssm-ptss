package com.system.service;

import com.system.domain.OrderInfo;
import com.system.domain.vo.AirlineOrderaccount;
import com.system.domain.vo.UserRechargeVo;

import java.util.List;

public interface OrderServices {
    String createmyOrder(Integer ticketId,Integer passengerId,Integer purchase_number);

    List<OrderInfo> getList(Integer passengerId);

    String refundmyOrder(OrderInfo orderInfo);

    String changeOrderquest(Integer orderId, Integer passengerId, Integer purchase_number, Integer ticketId);

    List<OrderInfo> getAllTicketList();

    List<OrderInfo> selectOrder(String searchtext);

    List<OrderInfo> searchListBycompanyname(String value);

    UserRechargeVo getCurrentOrderAccount(String airlineName);
}
