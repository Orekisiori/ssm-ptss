package com.system.controller;

import com.system.domain.OrderInfo;
import com.system.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrderServices orderServices;

    //订票
    @RequestMapping(value = "createorders", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String createOrder(Integer ticketId, Integer passengerId, Integer purchase_number) throws Exception {
        String message = orderServices.createmyOrder(ticketId, passengerId, purchase_number);
        if (message.equals("机票不足")) {
            return "机票不足";
        } else if (message.equals("机票已售空")) {
            return "机票已售空";
        } else if (message.equals("余额充足，支付成功")) {
            return "余额充足，支付成功";
        } else if (message.equals("余额不足，请充值")) {
            return "余额不足，请充值";
        } else {
            return "未知错误";
        }
    }

    //退票
    @RequestMapping(value = "refund", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String refundOrder(OrderInfo orderInfo) throws Exception {
        String refundmessage = orderServices.refundmyOrder(orderInfo);
        if (refundmessage.equals("已退票，请勿重复操作")) {
            return "已退票，请勿重复操作";
        } else if (refundmessage.equals("退票成功")) {
            return "退票成功";
        } else {
            return "未知错误";
        }
    }

    //改签
    //传入该笔订单的id，当前乘客id，改签时需要改签的机票id，需要购买的机票数量
    @RequestMapping(value = "changeOrder", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String changeOrder(Integer orderId, Integer passengerId, Integer purchase_number, Integer ticketId) {
        String result = orderServices.changeOrderquest(orderId, passengerId, purchase_number, ticketId);
        if (result.equals("改签成功，价格相同，无需再次付款")) {
            return "改签成功，价格相同，无需再次付款";
        }
        else if (result.equals("改签成功，多余金额已退还至账户")) {
            return "改签成功，多余金额已退还至账户";
        }
        else if (result.equals("改签失败，钱包余额不足，请充值")) {
            return "改签失败，钱包余额不足，请充值";
        }
        else if (result.equals("改签成功，钱包余额充足")) {
            return "改签成功，钱包余额充足";
        }
        else {
            return "未知错误";
        }
    }
}
