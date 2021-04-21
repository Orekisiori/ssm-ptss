package com.system.domain.dto;

import java.util.Date;
import java.util.List;

/**
 * 生成订单表需要显示的数据对象
 */
public class crateOrderDto {
    private Date crateTime;
    private Integer orderId;
    private Long orderPrice;
    private String orderStatic;
    private String orderUser;
    private String orderTel;
    private String orderAddr;

    private List<orderFlowInfoDto> flowInfoList;

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<orderFlowInfoDto> getFlowInfoList() {
        return flowInfoList;
    }

    public void setFlowInfoList(List<orderFlowInfoDto> flowInfoList) {
        this.flowInfoList = flowInfoList;
    }

    public String getOrderStatic() {
        return orderStatic;
    }

    public void setOrderStatic(String orderStatic) {
        this.orderStatic = orderStatic;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }
}
