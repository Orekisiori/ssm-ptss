package com.system.domain;

public class TWallet {
    private Integer id;

    private Long balance;

    private Integer userId;

    private Integer orderId;

    private Integer numberList[];

    private String fNameList[];

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer[] getNumberList() {
        return numberList;
    }

    public void setNumberList(Integer[] numberList) {
        this.numberList = numberList;
    }

    public String[] getfNameList() {
        return fNameList;
    }

    public void setfNameList(String[] fNameList) {
        this.fNameList = fNameList;
    }
}
