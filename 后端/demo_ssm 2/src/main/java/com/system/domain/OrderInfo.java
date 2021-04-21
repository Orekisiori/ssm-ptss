package com.system.domain;

import java.util.Date;

public class OrderInfo {
    private Integer orderId;

    private Date purchaseDate;

    private Integer ticketId;

    private Float ticketPrice;

    private String startplace;

    private String endplace;

    private Date starttime;

    private Date endtime;

    private Integer passengerId;

    private String passengerName;

    private String passengerIdentity;

    private String passengerPhone;

    private String orderStatus;

    private Integer purchaseNumber;

    private Float orderPrice;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getStartplace() {
        return startplace;
    }

    public void setStartplace(String startplace) {
        this.startplace = startplace == null ? null : startplace.trim();
    }

    public String getEndplace() {
        return endplace;
    }

    public void setEndplace(String endplace) {
        this.endplace = endplace == null ? null : endplace.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    public String getPassengerIdentity() {
        return passengerIdentity;
    }

    public void setPassengerIdentity(String passengerIdentity) {
        this.passengerIdentity = passengerIdentity == null ? null : passengerIdentity.trim();
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone == null ? null : passengerPhone.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }
}