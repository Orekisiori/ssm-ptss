package com.system.controller;


import com.system.domain.OrderInfo;
import com.system.domain.vo.UserRechargeVo;
import com.system.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersinfoController {
    @Autowired
    private OrderServices orderServices;

    //获取当前用户的订单信息列表
    @RequestMapping(value = "getList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<OrderInfo> getList(Integer passengerId){
        return orderServices.getList(passengerId);
    }

    //获取所有订单信息
    @RequestMapping("getAllOrders")
    public List<OrderInfo> getAllOrders() {
        return orderServices.getAllTicketList();
    }

    //查询机票
    @RequestMapping(value = "selectOrder",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<OrderInfo> selectOrder(String searchtext) {
        return orderServices.selectOrder(searchtext);
    }

    @RequestMapping(value = "searchListBycompanyname",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public List<OrderInfo> searchListBycompanyname(String value) {
        return orderServices.searchListBycompanyname(value);
    }

    //计算当前航空公司账单中为支付成功的订单总金额
    @RequestMapping(value = "getCurrentOrderAccount",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public UserRechargeVo getCurrentOrderAccount(String airlineName) {
        return orderServices.getCurrentOrderAccount(airlineName);
    }
}
