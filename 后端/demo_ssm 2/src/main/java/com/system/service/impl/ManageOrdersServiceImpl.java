package com.system.service.impl;

import com.system.domain.TOrder;
import com.system.mapper.TOrderMapper;
import com.system.service.ManageOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageOrdersServiceImpl implements ManageOrdersService {
    @Autowired
    private TOrderMapper tOrderMapper;


    @Override
    public List<TOrder> getOrderList() {
        return tOrderMapper.getOrderList();
    }

    @Override
    public TOrder selectByPrimaryKey(int orderId) {
        return tOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void updateByPrimaryKeySelective(TOrder tOrder) {
        tOrderMapper.updateByPrimaryKeySelective(tOrder);
    }

    @Override
    public void deleteByID(Integer order_id) {
        tOrderMapper.deleteByPrimaryKey(order_id);
    }

    @Override
    public void insertOrder(TOrder tOrder) {
        tOrderMapper.insert(tOrder);
    }

    @Override
    public List<TOrder> selectKey(String query) {
        return tOrderMapper.selectByKey(query);
    }
}
