package com.system.domain.dto;


import java.util.List;

/**
 * orderDto用于存储前端传过来的数据
 */

public class orderDto {
    private Integer userId;
    private Long orderPrice;
    private String orderUser;
    private String orderTel;
    private String orderAddr;
    private List<Integer> fIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
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

    public List<Integer> getfIds() {
        return fIds;
    }

    public void setfIds(List<Integer> fIds) {
        this.fIds = fIds;
    }
}
