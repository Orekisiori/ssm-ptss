package com.system.service.impl;

import com.system.domain.TOrderInfo;
import com.system.mapper.TOrderInfoMapper;
import com.system.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private TOrderInfoMapper tOrderInfoMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addOrderInfos(List<TOrderInfo> orderInfoList) {
        if(orderInfoList.isEmpty() || orderInfoList==null) {
            throw new NullPointerException();
        }
        tOrderInfoMapper.insertOrderInfos(orderInfoList);
    }

    @Override
    public List<TOrderInfo> getOrderInfoByOrderId(Integer orderId) {
        return tOrderInfoMapper.selectByOrderId(orderId);
    }

    @Override
    public void deleteById(Integer order_id) {
        tOrderInfoMapper.deleteByPrimaryKey(order_id);
    }
}
