package com.system.domain;

import java.util.Date;

public class TOrder {
    private Integer id;

    private Integer userId;

    private Long orderPrice;

    private Integer orderId;

    private String orderStatic;

    private String orderUser;

    private String orderTel;

    private String orderAddr;

    private Date createTime;

    private Date updateTime;

    private String querykey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatic() {
        return orderStatic;
    }

    public void setOrderStatic(String orderStatic) {
        this.orderStatic = orderStatic == null ? null : orderStatic.trim();
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser == null ? null : orderUser.trim();
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel == null ? null : orderTel.trim();
    }

    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr == null ? null : orderAddr.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQuerykey() {
        return querykey;
    }

    public void setQuerykey(String querykey) {
        this.querykey = querykey;
    }
}
