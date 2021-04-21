package com.system.service;

import com.system.domain.TOrder;
import com.system.domain.Userlogin;
import com.system.domain.dto.crateOrderDto;
import com.system.domain.dto.orderDto;
import com.system.domain.dto.resultDto;
import com.system.domain.vo.orderVo;

import java.util.List;

public interface OrderService {
    void addOrder(orderVo orderVo);
    resultDto addOrder(orderDto orderDto);

    List<crateOrderDto> getList(Integer userId);

    void updateStatic(TOrder tOrder);
}
