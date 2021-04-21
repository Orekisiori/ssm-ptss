package com.system.service;

import com.system.domain.TOrderInfo;

import java.util.List;

public interface OrderInfoService {
    /**
     * 多条鲜花记录添加到订单详情表
     * @param orderInfoList
     */
    void addOrderInfos(List<TOrderInfo> orderInfoList);

    List<TOrderInfo> getOrderInfoByOrderId(Integer orderId);

    void deleteById(Integer order_id);
}
