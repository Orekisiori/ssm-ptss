package com.system.service;

import com.system.domain.TOrder;

import java.util.List;

public interface ManageOrdersService {
    List<TOrder> getOrderList();

    TOrder selectByPrimaryKey(int orderId);

    void updateByPrimaryKeySelective(TOrder tOrder);

    void deleteByID(Integer order_id);

    void insertOrder(TOrder tOrder);

    List<TOrder> selectKey(String query);
}
